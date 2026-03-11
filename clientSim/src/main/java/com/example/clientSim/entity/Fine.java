package com.example.clientSim.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Fine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private String reason;
    private String status; // PENDING, PAID, CANCELLED

    @ManyToOne
    @JoinColumn(name = "licence_id")
    private Licence licence;

    public Fine() {
    }

    public Fine(BigDecimal amount, String reason, Licence licence) {
        this.amount = amount;
        this.reason = reason;
        this.licence = licence;
        this.status = "PENDING";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Licence getLicence() {
        return licence;
    }

    public void setLicence(Licence licence) {
        this.licence = licence;
    }
}
