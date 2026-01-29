package com.authenticationapp.service;

import com.authenticationapp.config.security.PasswordBcrypt;
import com.authenticationapp.dto.request.AddUserDTO;
import com.authenticationapp.model.Role;
import com.authenticationapp.model.User;
import com.authenticationapp.repository.RoleRepository;
import com.authenticationapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import com.authenticationapp.config.security.UserPrincipal;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordBcrypt passwordBcrypt;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findActiveUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }

    public void addUser(AddUserDTO addUserDTO) throws Exception {
        User user = new User();
        user.setFirstName(addUserDTO.getFirstName());
        user.setLastName(addUserDTO.getLastName());
        user.setCellNumber(addUserDTO.getCellNumber());
        user.setUsername(addUserDTO.getEmail());
        user.setPassword(passwordBcrypt.hashPassword(addUserDTO.getPassword()));
        user.setEnabled(true);
        for(Integer roleId: addUserDTO.getRoleIds()){
            Role role = roleRepository.findById(roleId).orElseThrow(() -> new Exception("Role not found"));
            user.addRole(role);
        }
        userRepository.save(user);
    }

}
