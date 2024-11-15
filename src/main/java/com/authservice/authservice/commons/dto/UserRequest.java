package com.authservice.authservice.commons.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest
{
    @NotNull
    private String username;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;
}
