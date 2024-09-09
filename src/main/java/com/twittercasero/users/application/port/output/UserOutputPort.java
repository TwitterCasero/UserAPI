package com.twittercasero.users.application.port.output;

import com.twittercasero.users.domain.entities.User;

import java.util.List;

public interface UserOutputPort {
    List<User> findAll();

    User findByNickName(String nickName);

    List<String> findFollowersByNickName(String nickName);

    List<String> findFollowingByNickName(String nickName);
}
