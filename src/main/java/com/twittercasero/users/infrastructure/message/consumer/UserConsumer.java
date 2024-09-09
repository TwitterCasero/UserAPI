package com.twittercasero.users.infrastructure.message.consumer;

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

    @KafkaListener(topics = "add-followers-topic", groupId = "my-user-consumer-group")
    public void receiveMessageToAddFollowers(FollowerActionDTO message) {

        addFollowersUseCase.accept(message.getUserNickName(), message.getTargetNickName());
    }

    @KafkaListener(topics = "delete-followers-topic", groupId = "my-user-consumer-group")
    public void receiveMessageToDeleteFollowers(FollowerActionDTO message) {

        deleteFollowersUseCase.accept(message.getUserNickName(), message.getTargetNickName());
    }

    @KafkaListener(topics = "block-following-topic", groupId = "my-user-consumer-group")
    public void receiveMessageToBlockFollowing(FollowerActionDTO message) {

        blockFollowingUseCase.accept(message.getUserNickName(), message.getTargetNickName());
    }
}
