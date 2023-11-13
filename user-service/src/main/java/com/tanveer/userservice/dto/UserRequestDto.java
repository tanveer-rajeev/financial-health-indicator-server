package com.tanveer.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

  @NotBlank(message = "email is required")
  private String email;

  @NotBlank(message = "password is required")
  private String password;
}
