package com.example.clientSim;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Card {

    @Id
    private String cardNumber;

    private String cardHolder;
    private String securityCode;
    private LocalDate expiryDate;

    public Card(String number, String holder, String security, LocalDate expiry){

        boolean check = true;
        if(!CardController.checkNumber(number)){
            check = false;
        }
        if (!CardController.checkNumber(security) && check){
            check = false;
        }
        if (!CardController.checkLetter(holder) && check){
            check = false;
        }
        if (!CardController.checkLength(number, 16) && check){
            check = false;
        }
        if (!CardController.checkLength(security, 3) && check){
            check = false;
        }
        if (!CardController.checkLimit(holder, 100) && check){
            check = false;
        }
        if (!CardController.checkDate(expiry) && check){
            check = false;
        }

        this.cardNumber = number;
        this.cardHolder = holder;
        this.securityCode = security;
        this.expiryDate = expiry;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
