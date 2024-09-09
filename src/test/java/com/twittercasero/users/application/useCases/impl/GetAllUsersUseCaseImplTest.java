package com.twittercasero.users.application.useCases.impl;

import com.twittercasero.users.application.port.output.UserOutputPort;
import com.twittercasero.users.domain.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllUsersUseCaseImplTest {

    @Mock
    private UserOutputPort userOutputPort;

    @InjectMocks
    private GetAllUsersUseCaseImpl getAllUsersUseCase;

    @Test
    public void whenGetAllUsers() {
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(User.builder().nickName("emueller").email("e.mueller@gmail.com").build());
        expectedUsers.add(User.builder().nickName("JMendosa").email("j.mendosa@gmail.com").build());
        when(userOutputPort.findAll()).thenReturn(expectedUsers);

        List<User> actualUsers = getAllUsersUseCase.get();

        assertNotNull(actualUsers);
        assertEquals(2, actualUsers.size());
        assertEquals("emueller", actualUsers.get(0).getNickName());
        verify(userOutputPort, times(1)).findAll();
    }

    @Test
    public void whenGetAllUsersThenIsEmpty() {
        List<User> expectedUsers = new ArrayList<>();
        when(userOutputPort.findAll()).thenReturn(expectedUsers);

        List<User> actualUsers = getAllUsersUseCase.get();

        assertNotNull(actualUsers);
        assertTrue(actualUsers.isEmpty());
        verify(userOutputPort, times(1)).findAll();
    }
}
