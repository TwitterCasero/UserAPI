package com.twittercasero.users.application.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FollowerActionDTO {

    private String userNickName;
    private String targetNickName;
}
