package com.fu.swp391.service;

import com.fu.swp391.binding.entiity.UserCandidate;
import com.fu.swp391.common.enumConstants.Gender;
import com.fu.swp391.common.enumConstants.GenderEnum;
import com.fu.swp391.common.enumConstants.roleEnum;
import com.fu.swp391.entities.Candidate;
import com.fu.swp391.entities.Role;
import com.fu.swp391.entities.User;
import com.fu.swp391.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl2 implements UserService  {

    @Autowired
    RoleService roleService;

    private final UserRepository userRepository;

    private final MessageSource messageSource;

//    private final RoleService roleService;

    public UserServiceImpl2(UserRepository userRepository, MessageSource messageSource) {
        this.userRepository = userRepository;
        this.messageSource = messageSource;
//        this.roleService = roleService;
    }
    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    @Override
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    };

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("load function");
        User user = userRepository.findByEmail(username);
        System.out.println(user.getPassword());
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.built(user);
    }

    @Override
    public User save(User user) throws Exception {
        return userRepository.save(user);
    }

    @Override
    public List<String> getListGender(){
        List<Gender> genderList= new GenderEnum().getGenders();
        List<String> genderStringList = genderList.stream().map(Gender::getName).collect(Collectors.toList());
        genderStringList.forEach(
                gen -> {
                    System.out.println(gen);
                });
        return genderStringList;
    }

    @Override
    public Optional<Role>  addRoleToUser(String role, UserCandidate userCandidate){
        Optional<Role> roleUser = roleService.findRoleByDescription(roleEnum.CANDIDATE);
        Candidate candidate = userCandidate.getCandidate();
        User user = userCandidate.getUser();
        user.setCandidate(candidate);
        return roleUser;
    }

    public void getServiceName(){
        System.out.println("User service 2nd instance");
    }
}
