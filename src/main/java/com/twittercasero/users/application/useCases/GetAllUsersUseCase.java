package com.twittercasero.users.application.useCases;

import com.twittercasero.users.domain.entities.User;

import java.util.List;
import java.util.function.Supplier;

public interface GetAllUsersUseCase extends Supplier<List<User>> {
}
