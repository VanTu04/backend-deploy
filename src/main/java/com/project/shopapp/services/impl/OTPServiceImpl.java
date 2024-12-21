package com.project.shopapp.services.impl;

import com.project.shopapp.components.OTPUtil;
import com.project.shopapp.customexceptions.InvalidParamException;
import com.project.shopapp.models.OTP;
import com.project.shopapp.models.User;
import com.project.shopapp.repositories.OTPRepository;
import com.project.shopapp.repositories.UserRepository;
import com.project.shopapp.services.EmailService;
import com.project.shopapp.services.OTPService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OTPServiceImpl implements OTPService {

    private final UserRepository userRepository;
    private final OTPRepository otpRepository;
    private final EmailService emailService;

    @Override
    public String sendOTP(String phoneNumber, String email) throws InvalidParamException {
        Optional<User> existingUser = userRepository.findByPhoneNumberAndEmail(phoneNumber, email);
        if(existingUser.isEmpty()) {
            throw new InvalidParamException("Phone number and email do not match any account!");
        }

        String otpCode = OTPUtil.generateOTP(6);
        // xoa bo otp cu
        otpRepository.deleteByEmail(email);

        OTP otp = OTP.builder()
                .email(email)
                .otp(otpCode)
                .expirationTime(LocalDateTime.now().plusMinutes(3)) //het han sau 3 phut
                .build();

        String subject = "Your Password reset OTP";
        String body = "Your OTP is " + otpCode + " This code will expire in 3 minutes";
        emailService.sendEmail(email, subject, body);
        otpRepository.save(otp);
        return "OTP has been sent to your email";
    }

    @Override
    public boolean verifyOTP(String email, String otpCode) throws InvalidParamException {
        return otpRepository.findByEmail(email)
                .filter(otp -> otp.getOtp().equals(otpCode))
                .filter(otp -> otp.getExpirationTime().isAfter(LocalDateTime.now()))
                .map(otp -> {
                    otpRepository.delete(otp);
                    return true;
                }).orElse(false);
    }
}
