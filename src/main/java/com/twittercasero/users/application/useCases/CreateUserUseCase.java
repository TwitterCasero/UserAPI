package com.twittercasero.users.application.useCases;

import com.twittercasero.users.domain.entities.User;

import java.util.function.Function;

public interface CreateUserUseCase extends Function<User, User> {
}
