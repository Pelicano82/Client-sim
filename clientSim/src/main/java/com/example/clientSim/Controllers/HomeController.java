package com.example.clientSim.Controllers;

import com.example.clientSim.Service.Cardservice;
import com.example.clientSim.Service.FineService;
import com.example.clientSim.Service.LicenceService;
import com.example.clientSim.Service.PaymentService;
import com.example.clientSim.Service.Personservice;
import com.example.clientSim.entity.*;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private Personservice personService;

    @Autowired
    private LicenceService licenceService;

    @Autowired
    private FineService fineService;

    @Autowired
    private Cardservice cardService;

    @Autowired
    private PaymentService paymentService;

    // Landing Page
    @GetMapping("/")
    public String home() {
        return "landing";
    }

    // Login/View Licence Page - Shows form to find user
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Process login - find user by licence number
    @PostMapping("/login")
    @SuppressWarnings("unchecked")
    public String processLogin(
            @RequestParam(name = "licence-number") String licenceNumber,
            @RequestParam(name = "last-name") String lastName,
            HttpSession session,
            Model model) {
        
        // Find licence by number
        Optional<Licence> licenceOpt = licenceService.getLicenceByNumber(licenceNumber);
        
        if (licenceOpt.isPresent()) {
            Licence licence = licenceOpt.get();
            Person person = licence.getPerson();
            
            // Check if last name matches
            if (person != null && person.getLastName().equalsIgnoreCase(lastName)) {
                // Store in session
                session.setAttribute("person", person);
                session.setAttribute("licence", licence);
                
                // Get fines for this licence
                List<Fine> fines = fineService.getFinesByLicenceId(licence.getId());
                session.setAttribute("fines", fines);
                
                return "redirect:/finepay";
            }
        }
        
        model.addAttribute("error", "Licence number or last name not found");
        return "login";
    }

    // Dashboard - Fine Pay Page
    @GetMapping("/finepay")
    public String finepay(HttpSession session, Model model) {
        Person person = (Person) session.getAttribute("person");
        Licence licence = (Licence) session.getAttribute("licence");
        List<Fine> fines = (List<Fine>) session.getAttribute("fines");
        
        if (person == null || licence == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("user", person);
        model.addAttribute("licence", licence);
        model.addAttribute("fines", fines);
        
        // Calculate total fine amount
        BigDecimal totalFineAmount = BigDecimal.ZERO;
        if (fines != null) {
            for (Fine fine : fines) {
                if (fine.getStatus().equals("PENDING")) {
                    totalFineAmount = totalFineAmount.add(fine.getAmount());
                }
            }
        }
        model.addAttribute("totalFineAmount", totalFineAmount);
        
        return "finepay";
    }

    // Submit fine amount from finepay page
    @PostMapping("/finepay-submit")
    public String submitFineAmount(
            @RequestParam(name = "fine-amount") String fineAmountStr,
            @RequestParam(name = "licence-option") String licenceOption,
            HttpSession session,
            Model model) {
        
        Person person = (Person) session.getAttribute("person");
        Licence licence = (Licence) session.getAttribute("licence");
        List<Fine> fines = (List<Fine>) session.getAttribute("fines");
        
        if (person == null || licence == null || fines == null) {
            return "redirect:/login";
        }
        
        try {
            // Parse the fine amount entered by user
            BigDecimal userFineAmount = new BigDecimal(fineAmountStr.replace("£", "").trim());
            
            // Update the fine amount in the database
            if (!fines.isEmpty()) {
                for (Fine fine : fines) {
                    if (fine.getStatus().equals("PENDING")) {
                        fine.setAmount(userFineAmount);
                        fineService.saveFine(fine);
                    }
                }
            }
            
            // Calculate total: fine + renewal cost if renewing
            BigDecimal totalToPay = userFineAmount;
            if ("renew".equals(licenceOption)) {
                totalToPay = totalToPay.add(new BigDecimal("159.00"));
            }
            
            // Store licence option and total in session for payment processing
            session.setAttribute("licenceOption", licenceOption);
            session.setAttribute("totalToPay", totalToPay);
            
            return "redirect:/payment";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid fine amount: " + e.getMessage());
            return "finepay";
        }
    }

    // Payment Page
    @GetMapping("/payment")
    public String payment(HttpSession session, Model model) {
        Person person = (Person) session.getAttribute("person");
        Licence licence = (Licence) session.getAttribute("licence");
        BigDecimal totalToPay = (BigDecimal) session.getAttribute("totalToPay");
        
        if (person == null || licence == null) {
            return "redirect:/login";
        }
        
        // Use the pre-calculated total from finepay submission
        if (totalToPay == null) {
            totalToPay = BigDecimal.ZERO;
        }
        
        model.addAttribute("user", person);
        model.addAttribute("licence", licence);
        model.addAttribute("totalAmount", totalToPay);
        
        return "payment";
    }

    // Process Payment
    @PostMapping("/payment-process")
    public String processPayment(
            @RequestParam(name = "card-number") String cardNumber,
            @RequestParam(name = "name-on-card") String cardHolder,
            @RequestParam(name = "exp-month") String expMonth,
            @RequestParam(name = "exp-year") String expYear,
            @RequestParam(name = "cvv") String cvv,
            @RequestParam(name = "address1") String address1,
            HttpSession session,
            Model model) {
        
        Person person = (Person) session.getAttribute("person");
        Licence licence = (Licence) session.getAttribute("licence");
        List<Fine> fines = (List<Fine>) session.getAttribute("fines");
        BigDecimal totalToPay = (BigDecimal) session.getAttribute("totalToPay");
        String licenceOption = (String) session.getAttribute("licenceOption");
        
        if (person == null || licence == null) {
            return "redirect:/login";
        }
        
        try {
            // Create or update card
            Card card = new Card();
            card.setCardNumber(cardNumber);
            card.setCardHolder(cardHolder);
            card.setSecurityCode(cvv);
            card.setCardType("CREDIT");
            Card savedCard = cardService.saveCard(card);
            
            // Create payment record for each fine
            if (fines != null && !fines.isEmpty()) {
                for (Fine fine : fines) {
                    if (fine.getStatus().equals("PENDING")) {
                        // Create payment record
                        Payment payment = new Payment();
                        payment.setAmount(fine.getAmount());
                        payment.setPerson(person);
                        payment.setFine(fine);
                        payment.setCard(savedCard);
                        payment.setStatus("SUCCESS");
                        payment.setPaymentMethod("CARD");
                        Payment savedPayment = paymentService.savePayment(payment);
                        
                        // Update fine status to PAID
                        fine.setStatus("PAID");
                        fineService.saveFine(fine);
                    }
                }
            }
            
            // Mark licence as renewed if applicable
            if ("renew".equals(licenceOption)) {
                licence.setRenewed(true);
            }
            licence.setStatus("ACTIVE");
            licenceService.saveLicence(licence);
            
            // Update person with card
            person.setCard(savedCard);
            personService.savePerson(person);
            
            // Store data in session for confirmation page (BEFORE clearing)
            session.setAttribute("paymentPerson", person);
            session.setAttribute("paymentLicence", licence);
            session.setAttribute("paymentTotalAmount", totalToPay);
            session.setAttribute("paymentLicenceOption", licenceOption);
            
            // Clear old session attributes
            session.removeAttribute("person");
            session.removeAttribute("licence");
            session.removeAttribute("fines");
            session.removeAttribute("licenceOption");
            session.removeAttribute("totalToPay");
            
            return "redirect:/paymentconfirmation";
            
        } catch (Exception e) {
            model.addAttribute("error", "Payment processing failed: " + e.getMessage());
            return "payment";
        }
    }

    // Payment Confirmation Page
    @GetMapping("/paymentconfirmation")
    public String paymentconfirmation(HttpSession session, Model model) {
        // Retrieve persisted data from session
        Person person = (Person) session.getAttribute("paymentPerson");
        Licence licence = (Licence) session.getAttribute("paymentLicence");
        BigDecimal totalAmount = (BigDecimal) session.getAttribute("paymentTotalAmount");
        String licenceOption = (String) session.getAttribute("paymentLicenceOption");
        
        if (person == null || licence == null) {
            return "redirect:/login";
        }
        
        // Generate a reference number for display
        String referenceNumber = generateReferenceNumber();
        
        // Add to model
        model.addAttribute("person", person);
        model.addAttribute("licence", licence);
        model.addAttribute("referenceNumber", referenceNumber);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("licenceOption", licenceOption);
        
        return "paymentconfirmation";
    }

    private String generateReferenceNumber() {
        java.util.UUID uuid = java.util.UUID.randomUUID();
        String uuidStr = uuid.toString().replace("-", "").substring(0, 9).toUpperCase();
        return uuidStr.substring(0, 3) + " " + uuidStr.substring(3, 5) + uuidStr.substring(5);
    }
}
