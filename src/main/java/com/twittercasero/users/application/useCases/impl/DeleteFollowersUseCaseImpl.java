package com.twittercasero.users.application.useCases.impl;

import com.twittercasero.users.application.port.input.UserInputPort;
import com.twittercasero.users.application.port.output.UserOutputPort;
import com.twittercasero.users.application.useCases.DeleteFollowersUseCase;
import com.twittercasero.users.domain.entities.User;

public class DeleteFollowersUseCaseImpl implements DeleteFollowersUseCase {

    private final UserInputPort userInputPort;

    private final UserOutputPort userOutputPort;

    public DeleteFollowersUseCaseImpl(UserInputPort userInputPort, UserOutputPort userOutputPort) {
        this.userInputPort = userInputPort;
        this.userOutputPort = userOutputPort;
    }

    @Override
    public void accept(String userNickName, String nickNameToUnFollow) {

        User currentUser = userOutputPort.findByNickName(userNickName);
        User userToUnFollow = userOutputPort.findByNickName(nickNameToUnFollow);

        if (currentUser.getFollowers().contains(nickNameToUnFollow)) {
            currentUser.getFollowers().remove(nickNameToUnFollow);
            userInputPort.save(currentUser);
        }

        if (userToUnFollow.getFollowing().contains(userNickName)) {
            userToUnFollow.getFollowing().remove(userNickName);
            userInputPort.save(userToUnFollow);

        }

    }
}
