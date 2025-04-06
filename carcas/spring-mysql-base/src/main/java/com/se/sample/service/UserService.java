package com.se.sample.service;

import java.util.List;

import com.se.sample.exception.NotFoundException;
import com.se.sample.model.User;
import com.se.sample.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        logger.debug("User create action called");
        return userRepository.save(user);
    }

    public User findOne(Long userId) {
        logger.debug("User findById action called");
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User of id " +  userId + " not found."));
    }

    public List<User> findAll() {
        logger.debug("User findAll action called");
        return userRepository.findAll();
    }

    public User update(Long id, User updated) {
        logger.debug("User update action called");

        return userRepository.findById(id)
                .map(employee -> {
                    employee.setFirstName(updated.getFirstName());
                    employee.setLastName(updated.getLastName());
                    employee.setEmail(updated.getEmail());
                    return userRepository.save(employee);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public void delete(Long userId) {
        logger.debug("User delete action called");
        userRepository.deleteById(userId);
    }

}