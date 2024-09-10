package com.twittercasero.users.infrastructure.message.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twittercasero.users.application.dto.FollowerActionDTO;
import com.twittercasero.users.application.useCases.AddFollowersUseCase;
import com.twittercasero.users.application.useCases.BlockFollowingUseCase;
import com.twittercasero.users.application.useCases.DeleteFollowersUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumer {

    @Autowired
    AddFollowersUseCase addFollowersUseCase;

    @Autowired
    DeleteFollowersUseCase deleteFollowersUseCase;

    @Autowired
    BlockFollowingUseCase blockFollowingUseCase;

    ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = "add-followers-topic", groupId = "my-user-consumer-group")
    public void receiveMessageToAddFollowers(String message) {

        try {
            FollowerActionDTO followerAction = mapper.readValue(message, FollowerActionDTO.class);
            addFollowersUseCase.accept(followerAction.getUserNickName(), followerAction.getTargetNickName());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }

    @KafkaListener(topics = "delete-followers-topic", groupId = "my-user-consumer-group")
    public void receiveMessageToDeleteFollowers(String message) {

        try {
            FollowerActionDTO followerAction = mapper.readValue(message, FollowerActionDTO.class);
            deleteFollowersUseCase.accept(followerAction.getUserNickName(), followerAction.getTargetNickName());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }

    @KafkaListener(topics = "block-following-topic", groupId = "my-user-consumer-group")
    public void receiveMessageToBlockFollowing(String message) {

        try {
            FollowerActionDTO followerAction = mapper.readValue(message, FollowerActionDTO.class);
            blockFollowingUseCase.accept(followerAction.getUserNickName(), followerAction.getTargetNickName());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}
