package com.example.clientSim;

import java.time.LocalDate;

public class PersonController {

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

    public static boolean checkEmptyArray (String[] value) {
        boolean check = true;
        if (value == null || value[0] == null){
            check = false;
        }
        return check;
    }

    public static boolean checkLimitArray (String[] value, int limit) {
        if(value == null){
            return false;
        }
        boolean check = true;
        for (String s : value){
            if(s.length() >= limit){
                check = false;
                break;
            }
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
