package com.authenticationapp.controller;

import com.authenticationapp.dto.response.SuccessMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("/check")
    @PreAuthorize("hasAnyAuthority('Admin')")
    public ResponseEntity<?> getAdmin(){
        return new ResponseEntity<SuccessMessageDTO>(new SuccessMessageDTO(true, "Admin Area"), HttpStatus.OK);
    }

    @GetMapping("/get")
   // @PreAuthorize("hasAnyAuthority('Admin')")
    public ResponseEntity<?> check(@AuthenticationPrincipal(expression = "username") String username){
        if(username.length()>1) throw new UsernameNotFoundException("not found");
        return new ResponseEntity<SuccessMessageDTO>(new SuccessMessageDTO(true, username+" Area"), HttpStatus.OK);
    }
}
