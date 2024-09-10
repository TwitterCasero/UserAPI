package com.twittercasero.users.infrastructure.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserCreateRequest {

    @NotBlank(message = "Nickname cannot be empty")
    private String nickName;

    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;
}
