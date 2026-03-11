package com.example.clientSim.entity;

import java.time.LocalDate;

public class PersonValidator {
    public static boolean checkLetter(String value){
        if(value == null){
            return false;
        }
        boolean check = true;
        for (char i : value.toCharArray()){
            if (Character.isLetter(i) || i == ' '){
                continue;
            } else {
                check = false;
                break;
            }
        }
        return check;
    }

    public static boolean checkNumber(String value){
        if(value == null){
            return false;
        }
        boolean check = true;
        for (char i : value.toCharArray()){
            if (i >= '0' && i <= '9'){
                continue;
            } else {
                check = false;
                break;
            }
        }
        return check;
    }

    public static boolean checkLength (String value, int length) {
        if(value == null){
            return false;
        }
        boolean check = true;
        if (value.strip().length() != length){
            check = false;
        }
        return check;
    }

    public static boolean checkLimit (String value, int limit) {
        if(value == null){
            return false;
        }
        boolean check = true;
        if (value.strip().length() >= limit){
            check = false;
        }
        return check;
    }


    public static boolean checkEmail(String em){
        if(em == null){
            return false;
        }
        boolean check = false;
        for (char i : em.toCharArray()){
            if (i == '@'){
                check = true;
            }
        }
        return check;
    }
}
