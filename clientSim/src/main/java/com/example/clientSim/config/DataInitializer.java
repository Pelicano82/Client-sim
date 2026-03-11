package com.example.clientSim.config;

import com.example.clientSim.Repos.Cardrepo;
import com.example.clientSim.Repos.FineRepo;
import com.example.clientSim.Repos.LicenceRepo;
import com.example.clientSim.Repos.Personrepo;
import com.example.clientSim.entity.Card;
import com.example.clientSim.entity.Fine;
import com.example.clientSim.entity.Licence;
import com.example.clientSim.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private Personrepo personRepository;

    @Autowired
    private Cardrepo cardRepository;

    @Autowired
    private LicenceRepo licenceRepository;

    @Autowired
    private FineRepo fineRepository;

    @Override
    public void run(String... args) throws Exception {
        // Only initialize if no data exists
        if (personRepository.count() == 0) {
            // Create test Person
            Person person = new Person();
            person.setFirstName("John");
            person.setLastName("Smith");
            person.setFullName("John Smith");
            person.setAddressLine1("123 Main Street");
            person.setCity("London");
            person.setPostcode("SW1A 1AA");
            person.setCountry("United Kingdom");
            person.setEmail("john.smith@email.com");
            person.setPhone("02012345678");
            Person savedPerson = personRepository.save(person);

            // Create test Card
            Card card = new Card();
            card.setCardNumber("4532123456789010");
            card.setCardHolder("JOHN SMITH");
            card.setExpiryDate(LocalDate.of(2026, 12, 31));
            card.setSecurityCode("123");
            card.setCardType("CREDIT");
            Card savedCard = cardRepository.save(card);

            // Update Person with Card
            savedPerson.setCard(savedCard);
            personRepository.save(savedPerson);

            // Create test Licence
            Licence licence = new Licence();
            licence.setLicenceNumber("LTV-2024-001");
            licence.setFee(new BigDecimal("159.00"));
            licence.setValidUntil(LocalDate.of(2025, 12, 31));
            licence.setStatus("ACTIVE");
            licence.setRenewed(false);
            licence.setPerson(savedPerson);
            Licence savedLicence = licenceRepository.save(licence);

            // Create test Fines
            Fine fine1 = new Fine();
            fine1.setAmount(new BigDecimal("85.50"));
            fine1.setReason("Late payment penalty");
            fine1.setStatus("PENDING");
            fine1.setLicence(savedLicence);
            fineRepository.save(fine1);

            Fine fine2 = new Fine();
            fine2.setAmount(new BigDecimal("45.00"));
            fine2.setReason("Non-compliance fee");
            fine2.setStatus("PENDING");
            fine2.setLicence(savedLicence);
            fineRepository.save(fine2);

            System.out.println("\n========================================");
            System.out.println("TEST DATA INITIALIZED SUCCESSFULLY");
            System.out.println("========================================");
            System.out.println("Login Credentials:");
            System.out.println("  TV Licence Number: LTV-2024-001");
            System.out.println("  Last Name: Smith");
            System.out.println("========================================\n");
        }
    }
}
