package com.project.shopapp.services;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}
