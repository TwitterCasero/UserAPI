package com.twittercasero.users.application.useCases;

import java.util.List;
import java.util.function.Function;

public interface GetFollowingByNickNameUseCase extends Function<String, List<String>> {
}
