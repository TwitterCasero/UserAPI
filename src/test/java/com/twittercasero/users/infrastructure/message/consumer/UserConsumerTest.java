package com.twittercasero.users.infrastructure.message.consumer;

import com.twittercasero.users.application.dto.FollowerActionDTO;
import com.twittercasero.users.application.useCases.AddFollowersUseCase;
import com.twittercasero.users.application.useCases.BlockFollowingUseCase;
import com.twittercasero.users.application.useCases.DeleteFollowersUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserConsumerTest {

    @Mock
    private AddFollowersUseCase addFollowersUseCase;

    @Mock
    private DeleteFollowersUseCase deleteFollowersUseCase;

    @Mock
    private BlockFollowingUseCase blockFollowingUseCase;

    @InjectMocks
    private UserConsumer userConsumer;

    @Test
    public void testReceiveMessageToAddFollowers() {

        FollowerActionDTO message = FollowerActionDTO.builder()
                .userNickName("userNickName")
                .targetNickName("targetNickName").build();
        doNothing().when(addFollowersUseCase).accept(message.getUserNickName(), message.getTargetNickName());

        userConsumer.receiveMessageToAddFollowers(message);

        verify(addFollowersUseCase, times(1)).accept(message.getUserNickName(), message.getTargetNickName());
    }

    @Test
    public void testReceiveMessageToDeleteFollowers() {

        FollowerActionDTO message = FollowerActionDTO.builder()
                .userNickName("userNickName")
                .targetNickName("targetNickName").build();
        doNothing().when(deleteFollowersUseCase).accept(message.getUserNickName(), message.getTargetNickName());

        userConsumer.receiveMessageToDeleteFollowers(message);

        verify(deleteFollowersUseCase, times(1)).accept(message.getUserNickName(), message.getTargetNickName());
    }

    @Test
    public void testReceiveMessageToBlockFollowing() {

        FollowerActionDTO message = FollowerActionDTO.builder()
                .userNickName("userNickName")
                .targetNickName("targetNickName").build();
        doNothing().when(blockFollowingUseCase).accept(message.getUserNickName(), message.getTargetNickName());

        userConsumer.receiveMessageToBlockFollowing(message);

        verify(blockFollowingUseCase, times(1)).accept(message.getUserNickName(), message.getTargetNickName());
    }
}
