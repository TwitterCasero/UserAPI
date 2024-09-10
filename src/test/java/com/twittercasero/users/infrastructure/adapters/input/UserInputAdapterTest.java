package com.twittercasero.users.infrastructure.adapters.input;

import com.twittercasero.users.domain.entities.User;
import com.twittercasero.users.infrastructure.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserInputAdapterTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserInputAdapter userInputAdapter;

    @Test
    public void testSaveUser() {

        User user = User.builder()
                .nickName("eMueller")
                .email("e.mueller@gmail.com")
                .build();

        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userInputAdapter.save(user);

        assertNotNull(savedUser);
        assertEquals("eMueller", savedUser.getNickName());
        verify(userRepository, times(1)).save(user);
    }

}