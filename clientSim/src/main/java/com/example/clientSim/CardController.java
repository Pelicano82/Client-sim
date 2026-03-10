package com.example.clientSim;

import java.time.LocalDate;

public class CardController {

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

    public static boolean checkLength (String value, int length) {
        if(value == null){
            return false;
        }
        boolean check = true;
        if (value.length() != length){
            check = false;
        }
        return check;
    }

    public static boolean checkLimit (String value, int limit) {
        if(value == null){
            return false;
        }
        boolean check = true;
        if (value.length() >= limit){
            check = false;
        }
        return check;
    }

    public static boolean checkDate (LocalDate date){
        if(date == null){
            return false;
        }
        boolean check = true;
        if (!date.isBefore(LocalDate.now())){
            check = false;
        }
        return check;
    }
}

