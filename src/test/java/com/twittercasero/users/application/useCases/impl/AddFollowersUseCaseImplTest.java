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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddFollowersUseCaseImplTest {

    @Mock
    private UserInputPort userInputPort;

    @Mock
    private UserOutputPort userOutputPort;

    @InjectMocks
    private AddFollowersUseCaseImpl addFollowersUseCase;

    private User currentUser;
    private User userToFollow;

    @BeforeEach
    void setUp() {

        currentUser = User.builder().nickName("eMueller")
                .followers(new ArrayList<>())
                .blockedFollowing(new ArrayList<>())
                .build();

        userToFollow = User.builder().nickName("JMendosa")
                .following(new ArrayList<>())
                .build();

    }

    @Test
    public void whenUserIsNotBlockedAndNotAlreadyFollowedThenFollowSuccessfully() {

        when(userOutputPort.findByNickName("user1")).thenReturn(currentUser);
        when(userOutputPort.findByNickName("user2")).thenReturn(userToFollow);

        addFollowersUseCase.accept("user1", "user2");

        assertTrue(currentUser.getFollowers().contains("user2"));
        assertTrue(userToFollow.getFollowing().contains("user1"));
        verify(userInputPort, times(2)).save(any(User.class));
    }

    @Test
    public void whenUserIsBlockedThenThrowException() {

        currentUser.getBlockedFollowing().add("user2");
        when(userOutputPort.findByNickName("user1")).thenReturn(currentUser);
        when(userOutputPort.findByNickName("user2")).thenReturn(userToFollow);

        Exception exception = assertThrows(RuntimeException.class, () -> {

            addFollowersUseCase.accept("user1", "user2");
        });

        assertEquals("Cannot follow because the user is blocked.", exception.getMessage());
    }

    @Test
    public void whenAlreadyFollowingThenDoNotAddAgain() {

        currentUser.getFollowers().add("user2");
        when(userOutputPort.findByNickName("user1")).thenReturn(currentUser);
        when(userOutputPort.findByNickName("user2")).thenReturn(userToFollow);

        addFollowersUseCase.accept("user1", "user2");

        assertEquals(1, currentUser.getFollowers().size());
        verify(userInputPort, times(1)).save(userToFollow);
    }
}
