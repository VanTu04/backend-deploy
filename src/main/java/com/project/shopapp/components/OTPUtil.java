package com.project.shopapp.components;

import java.util.Random;

public class OTPUtil {
    public static String generateOTP(int length) {
        String number = "0123456789";
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for(int i = 0; i < length; i++) {
            otp.append(number.charAt(random.nextInt(number.length())));
        }
        return otp.toString();
    }
}
