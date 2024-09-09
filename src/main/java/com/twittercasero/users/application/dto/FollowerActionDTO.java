package com.twittercasero.users.application.dto;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FollowerActionDTO {

    private final String userNickName;
    private final String targetNickName;
}
