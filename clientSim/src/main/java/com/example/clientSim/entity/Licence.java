package com.example.clientSim.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
public class Licence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licenceNumber;
    private BigDecimal fee;
    private LocalDate validUntil;
    private String status; // ACTIVE, EXPIRED, CANCELLED
    private Boolean renewed;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Licence() {
    }

    public Licence(String licenceNumber, BigDecimal fee, LocalDate validUntil, Person person) {
        boolean check = true;
        if(!LicenceValidator.checkNumber(licenceNumber)){
            check = false;
        }
        if (!LicenceValidator.checkLength(licenceNumber, 10) && check){
            check = false;
        }
        if (!LicenceValidator.checkDecimal(fee) && check){
            check = false;
        }
        if (!LicenceValidator.checkDate(validUntil) && check){
            check = false;
        }
        if (!LicenceValidator.checkAmount(fee) && check){
            check = false;
        }

        if (check) {
            this.licenceNumber = licenceNumber;
            this.fee = fee;
            this.validUntil = validUntil;
            this.person = person;
            this.status = "ACTIVE";
            this.renewed = false;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getRenewed() {
        return renewed;
    }

    public void setRenewed(Boolean renewed) {
        this.renewed = renewed;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
