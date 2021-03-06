package com.fu.swp391.entities;

import com.fu.swp391.binding.entiity.exception.PrincipalBuildException;
import com.fu.swp391.common.enumConstants.roleEnum;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthPrinciple implements UserDetails {

    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String name;

    public AuthPrinciple(Long id, String username, String password,
        Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.email = username;
        this.password = password;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static AuthPrinciple built(User user) throws PrincipalBuildException {
        // Dựa vào list quyền trả về tạo đối tượng GrantedAuthority của spring cho quyền đó
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            System.out.println(role.getName());
        }
        // Cung cấp id, email , password và authorities (quyền)
        AuthPrinciple authPrinciple = new AuthPrinciple(user.getId(), user.getEmail(),
            user.getPasswordEncoder(), authorities);
        String userName = getUserNameAccordingRole(user.getRoles(),user);
        if(userName==null) throw new PrincipalBuildException();
        authPrinciple.setName(userName);
        return authPrinciple;
    }

    private static String getUserNameAccordingRole(Set<Role> roles,User user){
        List<String> roleString = new ArrayList<>();

        for (Role role:roles){
            roleString.add(role.getName());
        }
        if(roleString.contains(roleEnum.ADMIN)){
            return "admin";
        }

        if (roleString.contains(roleEnum.COMPANY)){
            List<Company> companies = user.getCompanies();
            if (companies == null) {
                return null;
            } else {
                if (companies.size() == 0) {
                    return null;
                }
            }
            return user.getCompanies().get(0).getName();
        }

        if (roleString.contains(roleEnum.CANDIDATE)) {
            List<Candidate> candidates = user.getCandidates();
            if (candidates == null) {
                return null;
            } else {
                if (candidates.size() == 0) {
                    return null;
                }
            }
            return user.getCandidates().get(0).getName();
        }
        return null;
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
