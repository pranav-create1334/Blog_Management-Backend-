package com.example.blog.service;

import com.example.blog.entity.*;
import com.example.blog.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

//    public User register(User user)
//    {
//        user.setPassword(encoder.encode(user.getPassword()));
//
//        if (user.getRole() == null) {
//            user.setRole(Role.ROLE_USER);
//        } else if (user.getRole() == Role.ROLE_ADMIN) {
//            user.setRole(Role.ROLE_ADMIN);
//        } else {
//            user.setRole(Role.ROLE_USER);
//        }
//        return userRepository.save(user);
//    }
public User register(User user) {

    // Encode password
    user.setPassword(encoder.encode(user.getPassword()));

    // Set default role if not provided
    if (user.getRole() == null) {
        user.setRole(Role.ROLE_USER);
    }

    return userRepository.save(user);
}
}
// user.setRole(Role.ROLE_USER);
