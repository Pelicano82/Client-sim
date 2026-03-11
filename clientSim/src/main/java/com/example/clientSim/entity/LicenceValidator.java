package com.example.clientSim.entity;

import java.time.LocalDate;
import java.math.BigDecimal;

public class LicenceValidator {
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

    public static boolean checkLength(String value, int length) {
        if(value == null){
            return false;
        }
        boolean check = true;
        if (value.length() != length){
            check = false;
        }
        return check;
    }

    public static boolean checkDecimal(double value) {
        boolean check = true;
        String st = String.valueOf(value);
        String[] arr = st.split(".");
        if(arr.length == 2 && arr[1].length() <= 2){
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

    public static boolean checkAmount (double value){
        boolean check = true;
        if (value < 0 || value > 100000){
            check = false;
        }
        return check;
    }
}
