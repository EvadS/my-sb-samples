package com.se.sample;


import com.se.sample.model.User;
import com.se.sample.model.UserRegisteredEvent;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CustomService {



    @Autowired
    private ApplicationEventPublisher publisher;

    public void registerUser(User user) {
        // Save the user to the database or do whatever setup is needed
        publisher.publishEvent(new UserRegisteredEvent(this, user.getEmail()));
    }
}
