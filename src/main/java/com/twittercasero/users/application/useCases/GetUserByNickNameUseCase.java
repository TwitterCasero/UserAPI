package com.twittercasero.users.application.useCases;

import com.twittercasero.users.domain.entities.User;

import java.util.function.Function;

public interface GetUserByNickNameUseCase extends Function<String, User> {
}
