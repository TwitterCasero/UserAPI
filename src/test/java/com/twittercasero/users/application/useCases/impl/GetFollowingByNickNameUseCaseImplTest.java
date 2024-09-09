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
public class GetFollowingByNickNameUseCaseImplTest {

    @Mock
    private UserOutputPort userOutputPort;

    @InjectMocks
    private GetFollowingByNickNameUseCaseImpl getFollowingByNickNameUseCase;

    @Test
    public void testApply() {

        String nickName = "emueller";
        List<String> expectedFollowing = Arrays.asList("following1", "following2");
        when(userOutputPort.findFollowingByNickName(nickName)).thenReturn(expectedFollowing);

        List<String> actualFollowing = getFollowingByNickNameUseCase.apply(nickName);

        assertNotNull(actualFollowing);
        assertEquals(2, actualFollowing.size());
        assertTrue(actualFollowing.contains("following1"));
        assertTrue(actualFollowing.contains("following2"));
        verify(userOutputPort, times(1)).findFollowingByNickName(nickName);
    }
}
