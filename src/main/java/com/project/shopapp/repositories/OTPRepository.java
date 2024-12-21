package com.project.shopapp.repositories;

import com.project.shopapp.models.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OTPRepository extends JpaRepository<OTP, Long> {
    Optional<OTP> findByEmail(String email);
    void deleteByEmail(String email);
}
