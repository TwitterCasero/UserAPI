package com.twittercasero.users.application.useCases.impl;

import com.twittercasero.users.application.port.input.UserInputPort;
import com.twittercasero.users.application.port.output.UserOutputPort;
import com.twittercasero.users.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BlockFollowingUseCaseImplTest {

    @Mock
    private UserInputPort userInputPort;

    @Mock
    private UserOutputPort userOutputPort;

    @InjectMocks
    private BlockFollowingUseCaseImpl blockFollowingUseCase;

    private User currentUser;
    private User userToBlock;

    @BeforeEach
    void setUp() {

        currentUser = User.builder().nickName("eMueller")
                .following(new ArrayList<>())
                .blockedFollowing(new ArrayList<>())
                .build();

        userToBlock = User.builder().nickName("JMendosa")
                .followers(new ArrayList<>())
                .build();

    }

    @Test
    public void whenBlockingUserNotAlreadyBlockedThenBlockUser() {

        when(userOutputPort.findByNickName("user1")).thenReturn(currentUser);
        when(userOutputPort.findByNickName("user2")).thenReturn(userToBlock);

        blockFollowingUseCase.accept("user1", "user2");

        assertTrue(currentUser.getBlockedFollowing().contains("user2"));
        assertFalse(currentUser.getFollowing().contains("user2"));
        assertFalse(userToBlock.getFollowers().contains("user1"));
        verify(userInputPort, times(1)).save(currentUser);
        verify(userInputPort, times(1)).save(userToBlock);
    }

    @Test
    public void whenBlockingAlreadyBlockedUserThenDoNothing() {

        currentUser.getBlockedFollowing().add("user2");
        when(userOutputPort.findByNickName("user1")).thenReturn(currentUser);
        when(userOutputPort.findByNickName("user2")).thenReturn(userToBlock);

        blockFollowingUseCase.accept("user1", "user2");

        verify(userInputPort, never()).save(currentUser);
        verify(userInputPort, never()).save(userToBlock);
    }
}
