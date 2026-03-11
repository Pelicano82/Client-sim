package com.example.clientSim.entity;

import jakarta.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String fullName;
    private String addressLine1;
    private String city;
    private String postcode;
    private String country;
    private String email;
    private String phone;

    @OneToOne
    @JoinColumn(name = "card_card_number")
    private Card card;

    public Person() {
    }

    public Person(String firstName, String lastName, String addressLine1, String city, String postcode, String country, String email, String phone) {
        boolean check = true;
        if(!PersonValidator.checkNull(firstName)){
            check = false;
        }
        if (!PersonValidator.checkNull(lastName) && check){
            check = false;
        }
        if (!PersonValidator.checkNull(addressLine1) && check){
            check = false;
        }
        if (!PersonValidator.checkNull(city) && check){
            check = false;
        }
        if(!PersonValidator.checkNull(postcode) && check){
            check = false;
        }
        if (!PersonValidator.checkNull(country) && check){
            check = false;
        }
        if (!PersonValidator.checkNull(email) && check){
            check = false;
        }
        if (!PersonValidator.checkNull(phone) && check){
            check = false;
        }
        if(!PersonValidator.checkLetter(firstName) && check){
            check = false;
        }
        if (!PersonValidator.checkLetter(lastName) && check){
            check = false;
        }
        if (!PersonValidator.checkLetter(city) && check){
            check = false;
        }
        if (!PersonValidator.checkLetter(country) && check){
            check = false;
        }
        if (!PersonValidator.checkNumber(phone) && check){
            check = false;
        }
        if (!PersonValidator.checkLength(postcode, 7) && check){
            check = false;
        }
        if (!PersonValidator.checkLimit(firstName, 50) && check){
            check = false;
        }
        if (!PersonValidator.checkLimit(lastName, 50) && check){
            check = false;
        }
        if (!PersonValidator.checkLimit(addressLine1, 200) && check){
            check = false;
        }
        if (!PersonValidator.checkLimit(city, 50) && check){
            check = false;
        }
        if (!PersonValidator.checkLimit(country, 50) && check){
            check = false;
        }
        if (!PersonValidator.checkLimit(email, 100) && check){
            check = false;
        }
        if (!PersonValidator.checkEmail(email) && check){
            check = false;
        }

        if (check) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.fullName = firstName + " " + lastName;
            this.addressLine1 = addressLine1;
            this.city = city;
            this.postcode = postcode;
            this.country = country;
            this.email = email;
            this.phone = phone;
        }
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}


