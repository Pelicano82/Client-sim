package com.example.clientSim.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Card {

    @Id
    private String cardNumber;

    private String cardHolder;
    private String securityCode;
    private LocalDate expiryDate;
    private String cardType;

    public Card() {
    }

    public Card(String number, String holder, String security, LocalDate expiry){

        boolean check = true;
        if(!CardValidator.checkNull(number)){
            check = false;
        }
        if (!CardValidator.checkNull(security) && check){
            check = false;
        }
        if (!CardValidator.checkNull(holder) && check){
            check = false;
        }
        if (!CardValidator.checkNull(expiry) && check){
            check = false;
        }
        if(!CardValidator.checkNumber(number) && check){
            check = false;
        }
        if (!CardValidator.checkNumber(security) && check){
            check = false;
        }
        if (!CardValidator.checkLetter(holder) && check){
            check = false;
        }
        if (!CardValidator.checkLength(number, 16) && check){
            check = false;
        }
        if (!CardValidator.checkLength(security, 3) && check){
            check = false;
        }
        if (!CardValidator.checkLimit(holder, 100) && check){
            check = false;
        }
        if (!CardValidator.checkDate(expiry) && check){
            check = false;
        }

        if (check) {
            this.cardNumber = number;
            this.cardHolder = holder;
            this.securityCode = security;
            this.expiryDate = expiry;
        }
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

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
