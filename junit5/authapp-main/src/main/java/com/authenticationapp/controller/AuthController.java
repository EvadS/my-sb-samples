package com.authenticationapp.controller;

import com.authenticationapp.config.security.JwtAuthenticationResponse;
import com.authenticationapp.config.security.JwtTokenProvider;
import com.authenticationapp.config.security.LoginRequest;
import com.authenticationapp.dto.request.AddUserDTO;
import com.authenticationapp.dto.response.SuccessMessageDTO;
import com.authenticationapp.model.User;
import com.authenticationapp.repository.RoleRepository;
import com.authenticationapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

//import com.authenticationapp.config.security.UserPrincipal;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
@Profile("!test")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        System.out.println("Authenticate user "+loginRequest.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        System.out.println("Results "+authentication.getName()+" principal "+authentication.getPrincipal().toString());
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, (User) authentication.getPrincipal()));//, authentication.getPrincipal()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@Valid @RequestBody AddUserDTO addUserDTO) throws Exception {
        userService.addUser(addUserDTO);
        SuccessMessageDTO successMessageDTO = new SuccessMessageDTO(true, "User added successfully.");
        return new ResponseEntity<SuccessMessageDTO> (successMessageDTO, HttpStatus.OK);
    }

}
