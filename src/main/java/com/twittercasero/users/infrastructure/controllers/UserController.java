package com.twittercasero.users.infrastructure.controllers;

import com.twittercasero.users.application.useCases.CreateUserUseCase;
import com.twittercasero.users.application.useCases.GetAllUsersUseCase;
import com.twittercasero.users.application.useCases.GetFollowersByNickNameUseCase;
import com.twittercasero.users.application.useCases.GetFollowingByNickNameUseCase;
import com.twittercasero.users.application.useCases.GetUserByNickNameUseCase;
import com.twittercasero.users.domain.entities.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @Autowired
    private GetAllUsersUseCase getAllUsersUseCase;

    @Autowired
    private GetUserByNickNameUseCase getUserByNickNameUseCase;

    @Autowired
    private GetFollowersByNickNameUseCase getFollowersByNickNameUseCase;

    @Autowired
    private GetFollowingByNickNameUseCase getFollowingByNickNameUseCase;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        User savedUser = createUserUseCase.apply(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> Users = getAllUsersUseCase.get();
        return new ResponseEntity<>(Users, HttpStatus.OK);
    }

    @GetMapping("/{nickname}")
    public ResponseEntity<User> getUserByNickname(@PathVariable String nickname) {
        User user = getUserByNickNameUseCase.apply(nickname);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{nickname}/followers")
    public ResponseEntity<List<String>> getFollowersByNickname(@PathVariable String nickname) {
        List<String> users = getFollowersByNickNameUseCase.apply(nickname);
        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{nickname}/following")
    public ResponseEntity<List<String>> getFollowingByNickname(@PathVariable String nickname) {
        List<String> users = getFollowingByNickNameUseCase.apply(nickname);
        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
