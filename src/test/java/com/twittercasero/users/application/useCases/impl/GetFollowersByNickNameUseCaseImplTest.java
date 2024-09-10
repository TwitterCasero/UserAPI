package com.twittercasero.users.application.useCases.impl;

import com.twittercasero.users.application.port.output.UserOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetFollowersByNickNameUseCaseImplTest {

    @Mock
    private UserOutputPort userOutputPort;

    @InjectMocks
    private GetFollowersByNickNameUseCaseImpl getFollowersByNickNameUseCase;

    @Test
    public void testApply() {

        String nickName = "emueller";
        List<String> expectedFollowers = Arrays.asList("follower1", "follower2");
        when(userOutputPort.findFollowersByNickName(nickName)).thenReturn(expectedFollowers);

        List<String> actualFollowers = getFollowersByNickNameUseCase.apply(nickName);

        assertNotNull(actualFollowers);
        assertEquals(2, actualFollowers.size());
        assertTrue(actualFollowers.contains("follower1"));
        assertTrue(actualFollowers.contains("follower2"));
        verify(userOutputPort, times(1)).findFollowersByNickName(nickName);
    }
}
