package com.example.clientSim;

import jakarta.persistence.*;

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
        if(!PersonController.checkEmptyArray(add)){
            check = false;
        }
        if (!PersonController.checkLetter(town) && check){
            check = false;
        }
        if (!PersonController.checkLetter(co) && check){
            check = false;
        }
        if (!PersonController.checkLength(post, 7) && check){
            check = false;
        }
        if (!PersonController.checkLimit(town, 100) && check){
            check = false;
        }
        if (!PersonController.checkLimit(co, 100) && check){
            check = false;
        }
        if (!PersonController.checkLimit(em, 100) && check){
            check = false;
        }
        if(!PersonController.checkLimitArray(add, 100) && check){
            check = false;
        }
        if(!PersonController.checkEmail(em) && check){
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

