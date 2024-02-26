package com.amazon.demo.service;

import com.amazon.demo.model.MyUser;
import com.amazon.demo.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserServiceImpl implements MyUserService {

    @Autowired
    private MyUserRepository userRepository;

    @Override
    public MyUser signUp(MyUser user) {
        // Additional validation and business logic can be added here
        return userRepository.save(user);
    }

    @Override
    public MyUser login(String emailAddress, String password) {
        // Validate user credentials and perform login logic
        Optional<MyUser> optionalUser = userRepository.findByEmailAddress(emailAddress);
        if (optionalUser.isPresent() && checkPassword(optionalUser.get(), password)) {
            return optionalUser.get();
        }
        return null; // Invalid credentials
    }

    @Override
    public Optional<MyUser> findByEmail(Object email) {
        // Implement the findByEmail method based on your requirements
        // Example: return userRepository.findByEmailAddress((String) email);
        return Optional.empty();
    }

    @Override
    public MyUser saveUser(MyUser user) {
        // Implement the saveUser method
        // Example: return userRepository.save(user);
        return userRepository.save(user);
    }

    @Override
    public boolean checkPassword(MyUser user, String password) {
        // Implement your password checking logic here
        // For example, you might compare the hashed password in user.getPasswordHash() with the hashed password of the provided password
        // You should use a secure password hashing library for this purpose
        return user.getPasswordHash().equals(password);
    }
}
