package com.project.shopapp.services;

import com.project.shopapp.customexceptions.InvalidParamException;

public interface OTPService {
    String sendOTP(String phoneNumber, String email) throws InvalidParamException;
    boolean verifyOTP(String email, String otp) throws InvalidParamException;

}
