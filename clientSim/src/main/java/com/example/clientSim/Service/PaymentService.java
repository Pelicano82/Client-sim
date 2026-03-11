package com.example.clientSim.Service;

import com.example.clientSim.Repos.PaymentRepo;
import com.example.clientSim.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    public Payment savePayment(Payment payment) {
        if (payment.getReferenceNumber() == null) {
            payment.setReferenceNumber(generateReferenceNumber());
        }
        if (payment.getPaymentDate() == null) {
            payment.setPaymentDate(LocalDateTime.now());
        }
        return paymentRepo.save(payment);
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepo.findById(id);
    }

    public Optional<Payment> getPaymentByReferenceNumber(String referenceNumber) {
        return paymentRepo.findByReferenceNumber(referenceNumber);
    }

    public List<Payment> getPaymentsByPersonId(Long personId) {
        return paymentRepo.findByPersonId(personId);
    }

    public List<Payment> getPaymentsByFineId(Long fineId) {
        return paymentRepo.findByFineId(fineId);
    }

    public List<Payment> getAllPayments() {
        return paymentRepo.findAll();
    }

    public void deletePayment(Long id) {
        paymentRepo.deleteById(id);
    }

    public boolean paymentExists(Long id) {
        return paymentRepo.existsById(id);
    }

    private String generateReferenceNumber() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 9).toUpperCase();
        return uuid.substring(0, 3) + " " + uuid.substring(3, 5) + uuid.substring(5);
    }
}
