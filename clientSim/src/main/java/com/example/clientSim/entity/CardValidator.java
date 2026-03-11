package com.example.clientSim.entity;

import java.time.LocalDate;

public class CardValidator {

    public static boolean checkNull(String value) {
        boolean check = true;
        if (value == null) {
            return false;
        }
        return check;
    }

    public static boolean checkNull(LocalDate value) {
        boolean check = true;
        if (value == null) {
            return false;
        }
        return check;
    }

    public static boolean checkNumber(String value){
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

    public static boolean checkLetter(String value){
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

    public static boolean checkLength (String value, int length) {
        boolean check = true;
        if (value.strip().length() != length){
            check = false;
        }
        return check;
    }

    public static boolean checkLimit (String value, int limit) {
        boolean check = true;
        if (value.strip().length() >= limit){
            check = false;
        }
        return check;
    }

    public static boolean checkDate (LocalDate date){
        boolean check = true;
        if (!date.isBefore(LocalDate.now())){
            check = false;
        }
        return check;
    }
}
