package com.twittercasero.users.application.useCases.impl;

import com.twittercasero.users.application.port.input.UserInputPort;
import com.twittercasero.users.application.useCases.CreateUserUseCase;
import com.twittercasero.users.domain.entities.User;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserInputPort userInputPort;

    public CreateUserUseCaseImpl(UserInputPort userInputPort) {
        this.userInputPort = userInputPort;
    }

    @Override
    public User apply(User user) {
        return userInputPort.save(user);
    }
}
