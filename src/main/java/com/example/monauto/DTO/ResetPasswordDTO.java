package com.example.monauto.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordDTO {
    @NotBlank
    private String token;
    @NotBlank
    @Size(min = 6, max = 40)
    private String newPassword;
}
