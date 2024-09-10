package com.twittercasero.users.application.useCases.impl;

import com.twittercasero.users.application.port.input.UserInputPort;
import com.twittercasero.users.domain.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseImplTest {

    @Mock
    private UserInputPort userInputPort;

    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    @Test
    public void whenCreateUser() {
        User user = User.builder()
                .nickName("emueller")
                .email("emueller@gmail.com")
                .build();

        when(userInputPort.save(any(User.class))).thenReturn(user);

        User savedUser = createUserUseCase.apply(user);

        assertNotNull(savedUser);
        assertEquals("emueller", savedUser.getNickName());
        verify(userInputPort, times(1)).save(user);
    }
}
