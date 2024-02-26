package com.amazon.demo.service;

import com.amazon.demo.model.MyUser;

import java.util.Optional;

public interface MyUserService {
    MyUser signUp(MyUser user);
    MyUser login(String emailAddress, String password);

    MyUser saveUser(MyUser myUser);

    Optional<MyUser> findByEmail(Object email);

    boolean checkPassword(MyUser myUser, String password);
}
