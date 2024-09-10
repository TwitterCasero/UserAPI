package com.twittercasero.users.application.useCases.impl;

import com.twittercasero.users.application.port.output.UserOutputPort;
import com.twittercasero.users.application.useCases.GetFollowersByNickNameUseCase;

import java.util.List;

public class GetFollowersByNickNameUseCaseImpl implements GetFollowersByNickNameUseCase {

    private final UserOutputPort userOutputPort;

    public GetFollowersByNickNameUseCaseImpl(UserOutputPort userOutputPort) {
        this.userOutputPort = userOutputPort;
    }

    @Override
    public List<String> apply(String nickName) {
        return userOutputPort.findFollowersByNickName(nickName);
    }
}
