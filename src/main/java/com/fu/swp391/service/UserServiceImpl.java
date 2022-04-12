package com.fu.swp391.service;

import com.fu.swp391.entities.User;
import com.fu.swp391.repository.UserRepository;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final MessageSource messageSource;

//    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, MessageSource messageSource) {
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
}
