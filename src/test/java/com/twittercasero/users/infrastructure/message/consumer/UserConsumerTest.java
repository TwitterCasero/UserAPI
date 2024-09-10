package com.twittercasero.users.infrastructure.message.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twittercasero.users.application.dto.FollowerActionDTO;
import com.twittercasero.users.application.useCases.AddFollowersUseCase;
import com.twittercasero.users.application.useCases.BlockFollowingUseCase;
import com.twittercasero.users.application.useCases.DeleteFollowersUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserConsumerTest {

    @Mock
    private AddFollowersUseCase addFollowersUseCase;
    @Mock
    private DeleteFollowersUseCase deleteFollowersUseCase;
    @Mock
    private BlockFollowingUseCase blockFollowingUseCase;

    @InjectMocks
    private UserConsumer userConsumer;

    private ObjectMapper mapper = new ObjectMapper();
    private final String sampleMessage = "{\"userNickName\":\"user1\",\"targetNickName\":\"user2\"}";

    @BeforeEach
    void setUp() {
        // Puedes configurar comportamientos adicionales aquí si es necesario
    }

    @Test
    void testReceiveMessageToAddFollowers() throws Exception {
        FollowerActionDTO dto = mapper.readValue(sampleMessage, FollowerActionDTO.class);

        userConsumer.receiveMessageToAddFollowers(sampleMessage);

        verify(addFollowersUseCase).accept(dto.getUserNickName(), dto.getTargetNickName());
    }

    @Test
    void testReceiveMessageToDeleteFollowers() throws Exception {
        FollowerActionDTO dto = mapper.readValue(sampleMessage, FollowerActionDTO.class);

        userConsumer.receiveMessageToDeleteFollowers(sampleMessage);

        verify(deleteFollowersUseCase).accept(dto.getUserNickName(), dto.getTargetNickName());
    }

    @Test
    void testReceiveMessageToBlockFollowing() throws Exception {
        FollowerActionDTO dto = mapper.readValue(sampleMessage, FollowerActionDTO.class);

        userConsumer.receiveMessageToBlockFollowing(sampleMessage);

        verify(blockFollowingUseCase).accept(dto.getUserNickName(), dto.getTargetNickName());
    }
}
