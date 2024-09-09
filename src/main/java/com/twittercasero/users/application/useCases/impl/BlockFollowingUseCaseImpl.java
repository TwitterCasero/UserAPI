package com.twittercasero.users.application.useCases.impl;

import com.twittercasero.users.application.port.input.UserInputPort;
import com.twittercasero.users.application.port.output.UserOutputPort;
import com.twittercasero.users.application.useCases.BlockFollowingUseCase;
import com.twittercasero.users.domain.entities.User;

public class BlockFollowingUseCaseImpl implements BlockFollowingUseCase {

    private final UserInputPort userInputPort;

    private final UserOutputPort userOutputPort;

    public BlockFollowingUseCaseImpl(UserInputPort userInputPort, UserOutputPort userOutputPort) {
        this.userInputPort = userInputPort;
        this.userOutputPort = userOutputPort;
    }

    @Override
    public void accept(String userNickName, String nickNameToBlock) {

        User currentUser = userOutputPort.findByNickName(userNickName);
        User userToBlock = userOutputPort.findByNickName(nickNameToBlock);

        // Añadir al usuario bloqueado
        if (!currentUser.getBlockedFollowing().contains(nickNameToBlock)) {
            currentUser.getBlockedFollowing().add(nickNameToBlock);

            // Opcional: remover al usuario bloqueado de followers y following
            currentUser.getFollowing().remove(nickNameToBlock);
            userToBlock.getFollowers().remove(userNickName);

            userInputPort.save(currentUser);
            userInputPort.save(userToBlock);
        }

    }
}
