package com.twittercasero.users.application.useCases.impl;

import com.twittercasero.users.application.port.output.UserOutputPort;
import com.twittercasero.users.application.useCases.GetUserByNickNameUseCase;
import com.twittercasero.users.domain.entities.User;

public class GetUserByNickNameUseCaseImpl implements GetUserByNickNameUseCase {

    private final UserOutputPort userOutputPort;

    public GetUserByNickNameUseCaseImpl(UserOutputPort userOutputPort) {
        this.userOutputPort = userOutputPort;
    }

    @Override
    public User apply(String nickName) {
        return userOutputPort.findByNickName(nickName);
    }
}
