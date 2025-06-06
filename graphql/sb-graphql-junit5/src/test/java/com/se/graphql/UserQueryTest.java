package com.se.graphql;

import com.se.graphql.models.User;
import com.se.graphql.service.UserService;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserQueryTest {
    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @MockBean
    UserService userServiceMock;

    static List<User> users;

    @BeforeAll
    static void setUp() {
        users=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user=new User();
            user.setUserName(RandomStringUtils.randomAlphabetic(10));
            user.setAddress(RandomStringUtils.randomAlphabetic(20));
            user.setId(UUID.randomUUID());
            user.setPhoneNumber(RandomUtils.nextInt(1,9));
            users.add(user);
        }
    }

    @Test
    @DisplayName("Get all users, a non empty list of users with size equals to 10")
    public void testGetUsers() throws IOException {
        doReturn(users).when(userServiceMock).getAllUsers();
        GraphQLResponse response = graphQLTestTemplate.postForResource("schema.graphqls");
        assertThat(response.isOk()).isTrue();
        assertThat(response.getRaw("$.data.getUsers")).isNotNull();
        assertThat(response.getRaw("$.data.getUsers")).asList().hasSize(10);
    }
}
