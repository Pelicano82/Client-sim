package com.example.clientSim.Repos;

import com.example.clientSim.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {
    Optional<Payment> findByReferenceNumber(String referenceNumber);
    List<Payment> findByPersonId(Long personId);
    List<Payment> findByFineId(Long fineId);
}
