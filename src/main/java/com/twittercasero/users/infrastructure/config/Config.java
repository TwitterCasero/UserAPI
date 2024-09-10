package com.twittercasero.users.infrastructure.config;

import com.twittercasero.users.application.port.input.UserInputPort;
import com.twittercasero.users.application.port.output.UserOutputPort;
import com.twittercasero.users.application.useCases.AddFollowersUseCase;
import com.twittercasero.users.application.useCases.BlockFollowingUseCase;
import com.twittercasero.users.application.useCases.CreateUserUseCase;
import com.twittercasero.users.application.useCases.DeleteFollowersUseCase;
import com.twittercasero.users.application.useCases.GetAllUsersUseCase;
import com.twittercasero.users.application.useCases.GetFollowersByNickNameUseCase;
import com.twittercasero.users.application.useCases.GetFollowingByNickNameUseCase;
import com.twittercasero.users.application.useCases.GetUserByNickNameUseCase;
import com.twittercasero.users.application.useCases.impl.AddFollowersUseCaseImpl;
import com.twittercasero.users.application.useCases.impl.BlockFollowingUseCaseImpl;
import com.twittercasero.users.application.useCases.impl.CreateUserUseCaseImpl;
import com.twittercasero.users.application.useCases.impl.DeleteFollowersUseCaseImpl;
import com.twittercasero.users.application.useCases.impl.GetAllUsersUseCaseImpl;
import com.twittercasero.users.application.useCases.impl.GetFollowersByNickNameUseCaseImpl;
import com.twittercasero.users.application.useCases.impl.GetFollowingByNickNameUseCaseImpl;
import com.twittercasero.users.application.useCases.impl.GetUserByNickNameUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    CreateUserUseCase createUserUseCase(UserInputPort userInputPort) {
        return new CreateUserUseCaseImpl(userInputPort);

    }

    @Bean
    AddFollowersUseCase addFollowersUseCase(UserInputPort userInputPort, UserOutputPort userOutputPort) {
        return new AddFollowersUseCaseImpl(userInputPort, userOutputPort);

    }

    @Bean
    DeleteFollowersUseCase deleteFollowersUseCase(UserInputPort userInputPort, UserOutputPort userOutputPort) {
        return new DeleteFollowersUseCaseImpl(userInputPort, userOutputPort);

    }

    @Bean
    BlockFollowingUseCase blockFollowingUseCase(UserInputPort userInputPort, UserOutputPort userOutputPort) {
        return new BlockFollowingUseCaseImpl(userInputPort, userOutputPort);

    }

    @Bean
    GetAllUsersUseCase getAllUsersUseCase(UserOutputPort userOutputPort) {
        return new GetAllUsersUseCaseImpl(userOutputPort);

    }

    @Bean
    GetUserByNickNameUseCase getUserByNickNameUseCase(UserOutputPort userOutputPort) {
        return new GetUserByNickNameUseCaseImpl(userOutputPort);

    }

    @Bean
    GetFollowersByNickNameUseCase getFollowersByNickNameUseCase(UserOutputPort userOutputPort) {
        return new GetFollowersByNickNameUseCaseImpl(userOutputPort);

    }

    @Bean
    GetFollowingByNickNameUseCase getFollowingByNickNameUseCase(UserOutputPort userOutputPort) {
        return new GetFollowingByNickNameUseCaseImpl(userOutputPort);

    }
}
