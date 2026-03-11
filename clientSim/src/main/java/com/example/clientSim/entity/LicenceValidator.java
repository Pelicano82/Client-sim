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

    public static boolean checkDecimal(BigDecimal value) {
        boolean check = true;
        if (value.stripTrailingZeros().scale() == 2){
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

    public static boolean checkAmount (BigDecimal value){
        boolean check = true;
        BigDecimal bd1 = new BigDecimal("0");
        BigDecimal bd2 = new BigDecimal("100000");
        if (value.doubleValue() < bd1.doubleValue() || value.doubleValue() > bd1.doubleValue()){
            check = false;
        }
        return check;
    }
}
