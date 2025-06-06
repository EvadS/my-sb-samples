package com.se.sample.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import com.se.sample.SbSwaggerJwtApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = SbSwaggerJwtApplication.class)
public class BasicConfigurationIntegrationTest {

    TestRestTemplate restTemplate;
    URL base;

    @LocalServerPort int port;

    @Before
    public void setUp() throws MalformedURLException {
        restTemplate = new TestRestTemplate("spring", "secret")
                //("user", "password")
        ;
        base = new URL("http://localhost:" + port+ "/secure/test");
    }

    @Test
    public void whenLoggedUserRequestsHomePage_ThenSuccess() throws IllegalStateException, IOException {
        ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response
          .getBody()
          .contains("test"));
    }

    @Test
    public void whenUserWithWrongCredentials_thenUnauthorizedPage() throws IllegalStateException, IOException {
        restTemplate = new TestRestTemplate("user", "wrongpassword");
        ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
      //  assertNull(response.getBody());
    }

    @Test
    public void whenUserWithCredentials_thenUnauthorizedPage() throws IllegalStateException, IOException {
        restTemplate = new TestRestTemplate("spring", "secret");
        ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        //  assertNull(response.getBody());
    }
}
