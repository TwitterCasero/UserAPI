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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteFollowersUseCaseImplTest {

    @Mock
    private UserInputPort userInputPort;

    @Mock
    private UserOutputPort userOutputPort;

    @InjectMocks
    private DeleteFollowersUseCaseImpl deleteFollowersUseCase;

    private User currentUser;
    private User userToUnFollow;

    @BeforeEach
    void setUp() {

        currentUser = User.builder()
                .nickName("eMueller")
                .build();

        currentUser.getFollowers().add("JMendosa");

        userToUnFollow = User.builder().nickName("JMendosa")
                .build();

        userToUnFollow.getFollowing().add("eMueller");

    }

    @Test
    public void whenUnFollowThenRemoveFollowerAndFollowing() {

        when(userOutputPort.findByNickName("eMueller")).thenReturn(currentUser);
        when(userOutputPort.findByNickName("JMendosa")).thenReturn(userToUnFollow);

        deleteFollowersUseCase.accept("eMueller", "JMendosa");

        assertFalse(currentUser.getFollowers().contains("JMendosa"));
        assertFalse(userToUnFollow.getFollowing().contains("eMueller"));
        verify(userInputPort, times(1)).save(currentUser);
        verify(userInputPort, times(1)).save(userToUnFollow);
    }

    @Test
    public void whenNoFollowerToUnFollowThenDoNothing() {

        currentUser.getFollowers().remove("JMendosa");
        userToUnFollow.getFollowing().remove("eMueller");
        when(userOutputPort.findByNickName("eMueller")).thenReturn(currentUser);
        when(userOutputPort.findByNickName("JMendosa")).thenReturn(userToUnFollow);

        deleteFollowersUseCase.accept("eMueller", "JMendosa");

        assertFalse(currentUser.getFollowers().contains("JMendosa"));  // No change
        assertFalse(userToUnFollow.getFollowing().contains("eMueller")); // No change
        verify(userInputPort, never()).save(currentUser);
        verify(userInputPort, never()).save(userToUnFollow);
    }
}
