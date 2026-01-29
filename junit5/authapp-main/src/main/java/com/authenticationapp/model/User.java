package com.authenticationapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Table("users")
@Getter
@Setter
public class User implements UserDetails {
    @Id
    private long id;
    private String firstName;
    private String lastName;
    @Column("email")
    private String username;
    @JsonIgnore
    private String password;
    private String cellNumber;
    private boolean enabled;
    private Set<RoleRef> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

//        for (Role role : roles) {
//            String name = role.getName();
//            authorities.add(new SimpleGrantedAuthority(name));
//        }

        return authorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void addRole(Role role){
        roles.add(createRoleRef(role));
    }


    public RoleRef createRoleRef(Role role){
        RoleRef roleRef = new RoleRef();
        roleRef.setRole(role.getId());
        return roleRef;
    }
}
