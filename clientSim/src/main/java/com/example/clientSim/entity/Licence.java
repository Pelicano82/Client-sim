package com.example.clientSim.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Licence {
    @Id
    private String licenceNumber;
    private double licenceFee;
    private LocalDate expiryDate;

    public Licence(String liNo, double liFee, LocalDate exDate){
        boolean check = true;

        if(!LicenceValidator.checkNumber(liNo)){
            check = false;
        }
        if (!LicenceValidator.checkLength(liNo, 10) && check){
            check = false;
        }
        if (!LicenceValidator.checkDecimal(liFee) && check){
            check = false;
        }
        if (!LicenceValidator.checkAmount(liFee) && check){
            check = false;
        }
        if (!LicenceValidator.checkDate(exDate) && check){
            check = false;
        }


        this.licenceNumber = liNo;
        this.licenceFee = liFee;
        this.expiryDate = exDate;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public double getLicenceFee() {
        return licenceFee;
    }

    public void setLicenceFee(double licenceFee) {
        this.licenceFee = licenceFee;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
