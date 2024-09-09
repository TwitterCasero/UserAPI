package com.twittercasero.users.application.useCases.impl;

import com.twittercasero.users.application.port.output.UserOutputPort;
import com.twittercasero.users.domain.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUserByNickNameUseCaseImplTest {

    @Mock
    private UserOutputPort userOutputPort;

    @InjectMocks
    private GetUserByNickNameUseCaseImpl getUserByNickNameUseCase;

    @Test
    public void testApply() {

        String nickName = "emueller";
        User expectedUser = User.builder()
                .nickName(nickName)
                .email("e.mueller@gmail.com")
                .build();

        when(userOutputPort.findByNickName(nickName)).thenReturn(expectedUser);

        User actualUser = getUserByNickNameUseCase.apply(nickName);

        assertNotNull(actualUser);
        assertEquals(nickName, actualUser.getNickName());
        assertEquals("e.mueller@gmail.com", actualUser.getEmail());
        verify(userOutputPort, times(1)).findByNickName(nickName);
    }
}
