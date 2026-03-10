package com.example.clientSim;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String[] addresses = new String[2];
    private String townCity;
    private String postcode;
    private String country;
    private String email;

    @OneToOne
    @JoinColumn(name = "card_card_number")
    private Card card;

    public Person(String[] add, String town, String post, String co, String em){
        boolean check = true;
        if(!PersonValidator.checkEmptyArray(add)){
            check = false;
        }
        if (!PersonValidator.checkLetter(town) && check){
            check = false;
        }
        if (!PersonValidator.checkLetter(co) && check){
            check = false;
        }
        if (!PersonValidator.checkLength(post, 7) && check){
            check = false;
        }
        if (!PersonValidator.checkLimit(town, 100) && check){
            check = false;
        }
        if (!PersonValidator.checkLimit(co, 100) && check){
            check = false;
        }
        if (!PersonValidator.checkLimit(em, 100) && check){
            check = false;
        }
        if(!PersonValidator.checkLimitArray(add, 100) && check){
            check = false;
        }
        if(!PersonValidator.checkEmail(em) && check){
            check = false;
        }

        if(check) {
            this.addresses = add;
            this.townCity = town;
            this.postcode = post;
            this.country = co;
            this.email = em;
        }
    }


    public String[] getAddresses() {
        return addresses;
    }

    public void setAddresses(String[] addresses) {
        this.addresses = addresses;
    }

    public String getTownCity() {
        return townCity;
    }

    public void setTownCity(String townCity) {
        this.townCity = townCity;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String em) {
        this.email = em;
    }
}


