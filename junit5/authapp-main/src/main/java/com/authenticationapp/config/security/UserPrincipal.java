//package com.authenticationapp.config.security;
//
//import com.authenticationapp.model.Role;
//import com.authenticationapp.model.RoleRef;
//import com.authenticationapp.model.User;
//import com.authenticationapp.repository.RoleRepository;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//public class UserPrincipal implements UserDetails {
//    private Long userId;
//    private String username;
//    private String firstName;
//    private String lastName;
//    private boolean enabled;
//    private boolean accountNonLocked;
//    private boolean credentialsNonExpired;
//    private boolean accountNonExpired;
//
//
//    private Collection<? extends GrantedAuthority> authorities;
//
//    public UserPrincipal(Long userId, String username, String firstName, String lastName,Collection<? extends GrantedAuthority> authorities) {
//        this.userId = userId;
//        this.username = username;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.authorities = authorities;
//    }
//
//    public static UserPrincipal createUserAuthInfo(User user, RoleRepository roleRepository) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        try{
//            for(RoleRef roleRef: user.getRoles()){
//                Role role = roleRepository.findById(roleRef.getRole()).orElseThrow(() -> new Exception("Role not found"));
//                authorities.add(new SimpleGrantedAuthority(role.getName()));
//            }
//        } catch (Exception ex){
//            ex.printStackTrace();
//        }
//
//
//        return new UserPrincipal(
//                user.getId(),
//                user.getUsername(),
//                user.getFirstName(),
//                user.getLastName(),
//                authorities
//        );
//    }
//
//
//
//	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
//		this.authorities = authorities;
//	}
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String getUsername() {
//		return this.username;
//	}
//
//	public Long getUserId() {
//		return userId;
//	}
//
//	public void setUserId(Long userId) {
//		this.userId = userId;
//	}
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public void setUsername(String username) {
//		this.username = username;
//	}
//
////	public void setPassword(String password) {
////		this.password = password;
////	}
//
//	public void setEnabled(boolean enabled) {
//		this.enabled = enabled;
//	}
//
//	public void setAccountNonLocked(boolean accountNonLocked) {
//		this.accountNonLocked = accountNonLocked;
//	}
//
//	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
//		this.credentialsNonExpired = credentialsNonExpired;
//	}
//
//	public void setAccountNonExpired(boolean accountNonExpired) {
//		this.accountNonExpired = accountNonExpired;
//	}
//
//
//}