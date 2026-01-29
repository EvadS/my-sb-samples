package com.authenticationapp;

import com.authenticationapp.model.User;
import com.authenticationapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@ActiveProfiles("test")
public class UserControllerTests {
    @Autowired
    private WebApplicationContext applicationContext;
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void init(){
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext).apply(springSecurity())
                .build();
    }

    public User setupUser(){
        User user = new User();
        user.setEnabled(true);
        user.setPassword("test123");
        user.setUsername("test@gmail.com");
        user.setCellNumber("0300111111113");
        user.setFirstName("Sidney");
        user.setLastName("Sheldon");
        user = userRepository.save(user);
        return user;

    }

    @Test
    public void getLoggedinUser() throws Exception {
        mockMvc.perform(get("/user/loggedinUser").with(user(setupUser())))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andDo(print());
    }

    @Test
    public void getUserDetails() throws Exception {
        mockMvc.perform(get("/user/userDetails").with(user(setupUser())))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Sidney"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("test@gmail.com"))
                .andDo(print());
    }
    @Test
    public void getUserFromContext() throws Exception {
        mockMvc.perform(get("/user/userInContext").with(user(setupUser())))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Sidney"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("test@gmail.com"))
                .andDo(print());
    }

}
