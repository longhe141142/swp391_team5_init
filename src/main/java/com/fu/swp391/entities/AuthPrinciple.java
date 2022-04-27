package com.fu.swp391.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthPrinciple implements UserDetails {
    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> roles;

    public AuthPrinciple(Long id, String username, String password, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.email = username;
        this.password = password;
        this.roles = roles;
    }

    public static AuthPrinciple built(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role: user.getRoles()){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
            System.out.println(role.getName());
        }
        return new AuthPrinciple(user.getId(), user.getEmail(), user.getPasswordEncoder(), authorities );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
