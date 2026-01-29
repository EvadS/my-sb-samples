package com.authenticationapp.controller;

import com.authenticationapp.dto.response.GetUserDetailsDTO;
import com.authenticationapp.dto.response.SuccessMessageDTO;
import com.authenticationapp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/loggedinUser")
    public ResponseEntity<?> getLoggedinUser(@AuthenticationPrincipal(expression = "username") String username){
        return ResponseEntity.ok(new SuccessMessageDTO(true, username+" is authenticated" ));
    }

    @GetMapping("/userDetails")
    public ResponseEntity<?> getUserDetails(Authentication authentication){
        User user = (User)authentication.getPrincipal();
        GetUserDetailsDTO userDetails = new GetUserDetailsDTO(user.getId(),user.getFirstName(), user.getLastName(), user.getUsername());
        return ResponseEntity.ok(userDetails);
    }
    @GetMapping("/userInContext")
    public ResponseEntity<?> getUserFromContext(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        GetUserDetailsDTO userDetails = new GetUserDetailsDTO(user.getId(),user.getFirstName(), user.getLastName(), user.getUsername());
        return ResponseEntity.ok(userDetails);
    }
}
