package com.twittercasero.users.application.useCases.impl;

import com.twittercasero.users.application.port.input.UserInputPort;
import com.twittercasero.users.application.port.output.UserOutputPort;
import com.twittercasero.users.application.useCases.AddFollowersUseCase;
import com.twittercasero.users.domain.entities.User;

public class AddFollowersUseCaseImpl implements AddFollowersUseCase {

    private final UserInputPort userInputPort;

    private final UserOutputPort userOutputPort;

    public AddFollowersUseCaseImpl(UserInputPort userInputPort, UserOutputPort userOutputPort) {
        this.userInputPort = userInputPort;
        this.userOutputPort = userOutputPort;
    }

    @Override
    public void accept(String userNickName, String nickNameToFollow) {

        User currentUser = userOutputPort.findByNickName(userNickName);
        User userToFollow = userOutputPort.findByNickName(nickNameToFollow);

        if (currentUser.getBlockedFollowing().contains(nickNameToFollow)) {
            throw new RuntimeException("Cannot follow because the user is blocked.");
        }

        if (!currentUser.getFollowers().contains(nickNameToFollow)) {
            currentUser.getFollowers().add(nickNameToFollow);
            userInputPort.save(currentUser);
        }

        if (!userToFollow.getFollowing().contains(userNickName)) {
            userToFollow.getFollowing().add(userNickName);
            userInputPort.save(userToFollow);

        }
    }


}
