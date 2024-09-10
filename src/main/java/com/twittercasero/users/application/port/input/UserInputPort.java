package com.twittercasero.users.application.port.input;

import com.twittercasero.users.domain.entities.User;

public interface UserInputPort {
    User save(User user);

}
