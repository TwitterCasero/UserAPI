package com.twittercasero.users.infrastructure.adapters.output;

import com.twittercasero.users.domain.entities.User;
import com.twittercasero.users.domain.exceptions.UserNotFoundException;
import com.twittercasero.users.infrastructure.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserOutputAdapterTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserOutputAdapter userOutputAdapter;

    @Test
    public void testFindAll() {

        when(userRepository.findAll()).thenReturn(Collections.singletonList(User.builder().build()));

        List<User> users = userOutputAdapter.findAll();

        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
    }

    @Test
    public void testFindByNickName() {

        String nickName = "eMueller";
        when(userRepository.findByNickName(nickName)).thenReturn(Optional.of(User.builder().build()));

        User foundUser = userOutputAdapter.findByNickName(nickName);

        assertNotNull(foundUser);
    }

    @Test
    public void testFindByNickName_ThrowsIllegalArgumentException_WhenNickNameIsEmpty() {

        assertThrows(IllegalArgumentException.class, () -> {

            userOutputAdapter.findByNickName("");
        });
    }

    @Test
    public void testFindByNickName_ThrowsUserNotFoundException_WhenUserNotFound() {

        String nickName = "unknown_user";
        when(userRepository.findByNickName(nickName)).thenReturn(Optional.empty());


        assertThrows(UserNotFoundException.class, () -> {

            userOutputAdapter.findByNickName(nickName);
        });
    }

    @Test
    public void testFindFollowersByNickName() {

        String nickName = "eMueller";
        User user = User.builder().followers(List.of("follower1", "follower2")).build();
        when(userRepository.findByNickName(nickName)).thenReturn(Optional.of(user));

        List<String> followers = userOutputAdapter.findFollowersByNickName(nickName);

        assertNotNull(followers);
        assertEquals(2, followers.size());
    }

    @Test
    public void testFindFollowingByNickName() {

        String nickName = "eMueller";
        User user = User.builder().following(List.of("following1", "following2")).build();
        when(userRepository.findByNickName(nickName)).thenReturn(Optional.of(user));

        List<String> following = userOutputAdapter.findFollowingByNickName(nickName);

        assertNotNull(following);
        assertEquals(2, following.size());
    }
}