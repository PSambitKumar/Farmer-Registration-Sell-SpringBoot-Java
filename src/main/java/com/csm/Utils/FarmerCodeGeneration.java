package com.csm.Utils;

public class FarmerCodeGeneration {

    public static String FarmerCodeGenreationMethod(int maxUserSlNo){
        String userCode = "FR" + String.format("%03d", maxUserSlNo + 1);
        return userCode;
    }
}
