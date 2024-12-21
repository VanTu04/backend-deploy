package com.project.shopapp.DTO;

import lombok.*;

@Data//toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordDTO {
    private String email;
    private String password;
    private String phoneNumber;
    private String otp;
}
