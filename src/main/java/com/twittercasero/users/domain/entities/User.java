package com.twittercasero.users.domain.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Builder
@Getter
public class User {

    @Id
    private String id;

    @NotBlank(message = "Nickname cannot be empty")
    private String nickName;

    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    @Builder.Default
    private List<String> followers = new ArrayList<>();

    @Builder.Default
    private List<String> following = new ArrayList<>();

    @Builder.Default
    private List<String> blockedFollowing = new ArrayList<>();

}
