package com.twittercasero.users.application.useCases.impl;

import com.twittercasero.users.application.port.output.UserOutputPort;
import com.twittercasero.users.application.useCases.GetFollowingByNickNameUseCase;

import java.util.List;

public class GetFollowingByNickNameUseCaseImpl implements GetFollowingByNickNameUseCase {

    private final UserOutputPort userOutputPort;

    public GetFollowingByNickNameUseCaseImpl(UserOutputPort userOutputPort) {
        this.userOutputPort = userOutputPort;
    }

    @Override
    public List<String> apply(String nickName) {
        return userOutputPort.findFollowingByNickName(nickName);
    }
}
