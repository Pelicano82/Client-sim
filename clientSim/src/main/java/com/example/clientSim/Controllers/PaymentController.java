package com.example.clientSim.Controllers;

import com.example.clientSim.Service.PaymentService;
import com.example.clientSim.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        Payment savedPayment = paymentService.savePayment(payment);
        return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long id) {
        Optional<Payment> payment = paymentService.getPaymentById(id);
        return payment.map(ResponseEntity::ok)
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/reference/{referenceNumber}")
    public ResponseEntity<Payment> getPaymentByReference(@PathVariable String referenceNumber) {
        Optional<Payment> payment = paymentService.getPaymentByReferenceNumber(referenceNumber);
        return payment.map(ResponseEntity::ok)
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/person/{personId}")
    public ResponseEntity<List<Payment>> getPaymentsByPerson(@PathVariable Long personId) {
        List<Payment> payments = paymentService.getPaymentsByPersonId(personId);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/fine/{fineId}")
    public ResponseEntity<List<Payment>> getPaymentsByFine(@PathVariable Long fineId) {
        List<Payment> payments = paymentService.getPaymentsByFineId(fineId);
        return ResponseEntity.ok(payments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody Payment paymentDetails) {
        Optional<Payment> payment = paymentService.getPaymentById(id);
        if (payment.isPresent()) {
            Payment existingPayment = payment.get();
            existingPayment.setAmount(paymentDetails.getAmount());
            existingPayment.setPaymentMethod(paymentDetails.getPaymentMethod());
            existingPayment.setStatus(paymentDetails.getStatus());
            existingPayment.setTransactionId(paymentDetails.getTransactionId());
            existingPayment.setCard(paymentDetails.getCard());
            Payment updatedPayment = paymentService.savePayment(existingPayment);
            return ResponseEntity.ok(updatedPayment);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        if (paymentService.paymentExists(id)) {
            paymentService.deletePayment(id);
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
