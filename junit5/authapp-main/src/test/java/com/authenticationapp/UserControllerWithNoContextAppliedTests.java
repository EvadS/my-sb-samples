package com.authenticationapp;

import com.authenticationapp.model.User;
import com.authenticationapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class UserControllerWithNoContextAppliedTests {
    @Autowired
    private WebApplicationContext applicationContext;
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    public void init(){
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();
        setupUser();
    }

    private void setupUser(){
        User user = new User();
        user.setEnabled(true);
        user.setPassword("1234567");
        user.setUsername("sidney@gmail.com");
        user.setCellNumber("0300111111113");
        user.setFirstName("Sidney");
        user.setLastName("Sheldon");
        userRepository.save(user);
    }

    @Test
    @WithUserDetails("sidney@gmail.com")
    public void getUserFromContext() throws Exception {
        mockMvc.perform(get("/user/userInContext"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Sidney"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("sidney@gmail.com"))
                .andDo(print());
    }

}
