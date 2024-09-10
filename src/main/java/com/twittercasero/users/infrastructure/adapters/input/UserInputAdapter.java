package com.twittercasero.users.infrastructure.adapters.input;

import com.twittercasero.users.application.port.input.UserInputPort;
import com.twittercasero.users.domain.entities.User;
import com.twittercasero.users.infrastructure.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInputAdapter implements UserInputPort {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(@Valid User user) {
        return userRepository.save(user);
    }
}
