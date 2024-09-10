package com.twittercasero.users.infrastructure.adapters.output;

import com.twittercasero.users.application.port.output.UserOutputPort;
import com.twittercasero.users.domain.entities.User;
import com.twittercasero.users.domain.exceptions.UserNotFoundException;
import com.twittercasero.users.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOutputAdapter implements UserOutputPort {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByNickName(String nickName) {

        if (nickName == null || nickName.isEmpty()) {
            throw new IllegalArgumentException("NickName must not be empty");
        }

        return userRepository.findByNickName(nickName)
                .orElseThrow(() -> new UserNotFoundException("User not found with nickName: " + nickName));

    }

    @Override
    public List<String> findFollowersByNickName(String nickName) {
        return this.findByNickName(nickName).getFollowers();
    }

    @Override
    public List<String> findFollowingByNickName(String nickName) {
        return this.findByNickName(nickName).getFollowing();
    }
}
