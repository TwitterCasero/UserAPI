package com.twittercasero.users.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twittercasero.users.application.useCases.CreateUserUseCase;
import com.twittercasero.users.application.useCases.GetAllUsersUseCase;
import com.twittercasero.users.application.useCases.GetFollowersByNickNameUseCase;
import com.twittercasero.users.application.useCases.GetFollowingByNickNameUseCase;
import com.twittercasero.users.application.useCases.GetUserByNickNameUseCase;
import com.twittercasero.users.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CreateUserUseCase createUserUseCase;

    @Mock
    private GetAllUsersUseCase getAllUsersUseCase;

    @Mock
    private GetUserByNickNameUseCase getUserByNickNameUseCase;

    @Mock
    private GetFollowersByNickNameUseCase getFollowersByNickNameUseCase;

    @Mock
    private GetFollowingByNickNameUseCase getFollowingByNickNameUseCase;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void whenCreateUser() throws Exception {
        User user = User.builder()
                .nickName("eMueller")
                .firstName("Edu")
                .lastName("Mueller")
                .email("e.mueller@gmail.com")
                .build();
        when(createUserUseCase.apply(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenCreateUserFailByBadRequest() throws Exception {
        User user = User.builder().build();

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenGetAllUsers() throws Exception {
        List<User> users = List.of(User.builder().build(), User.builder().build());
        when(getAllUsersUseCase.get()).thenReturn(users);

        mockMvc.perform(get("/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void whenGetUserByNickname() throws Exception {
        String nickname = "eMueller";
        User user = User.builder().build();
        ; // setup user
        when(getUserByNickNameUseCase.apply(nickname)).thenReturn(user);

        mockMvc.perform(get("/users/{nickname}", nickname)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetFollowersByNickname() throws Exception {
        String nickname = "eMueller";
        List<String> followers = List.of("user1", "user2");
        when(getFollowersByNickNameUseCase.apply(nickname)).thenReturn(followers);

        mockMvc.perform(get("/users/{nickname}/followers", nickname)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void whenGetFollowingByNickname() throws Exception {
        String nickname = "eMueller";
        List<String> following = List.of("user3", "user4");
        when(getFollowingByNickNameUseCase.apply(nickname)).thenReturn(following);

        mockMvc.perform(get("/users/{nickname}/following", nickname)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}