package com.example.clientSim.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FineValidator {
    public static boolean checkNull(String value){
        boolean check = true;
        if(value == null){
            return false;
        }
        return check;
    }

    public static boolean checkNull(BigDecimal value){
        boolean check = true;
        if(value == null){
            return false;
        }
        return check;
    }

    public static boolean checkStatus(String value){
        boolean check = true;
        if(value.toUpperCase() != "PENDING" || value.toUpperCase() != "PAID" || value.toUpperCase() != "CANCELLED"){
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

    public static boolean checkLimit (String value, int limit) {
        boolean check = true;
        if (value.strip().length() >= limit){
            check = false;
        }
        return check;
    }

    public static boolean checkAmount (BigDecimal value){
        boolean check = true;
        BigDecimal bd1 = new BigDecimal("0");
        if (value.doubleValue() <= bd1.doubleValue()){
            check = false;
        }
        return check;
    }
}
