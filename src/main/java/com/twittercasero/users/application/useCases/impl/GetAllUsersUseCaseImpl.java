package com.twittercasero.users.application.useCases.impl;

import com.twittercasero.users.application.port.output.UserOutputPort;
import com.twittercasero.users.application.useCases.GetAllUsersUseCase;
import com.twittercasero.users.domain.entities.User;

import java.util.List;

public class GetAllUsersUseCaseImpl implements GetAllUsersUseCase {

    private final UserOutputPort userOutputPort;

    public GetAllUsersUseCaseImpl(UserOutputPort userOutputPort) {
        this.userOutputPort = userOutputPort;
    }

    @Override
    public List<User> get() {
        return userOutputPort.findAll();
    }
}
