package com.harry.zyneticbookstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harry.zyneticbookstore.model.User;
import com.harry.zyneticbookstore.service.JwtService;
import com.harry.zyneticbookstore.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSignup() throws Exception {
        User user = new User(1, "test@example.com", "password123");
        when(userService.saveUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }
}
