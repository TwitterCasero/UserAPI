package com.twittercasero.users.application.useCases;

import java.util.List;
import java.util.function.Function;

public interface GetFollowersByNickNameUseCase extends Function<String, List<String>> {
}
