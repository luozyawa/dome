package com.mir.demo.service;

import com.mir.demo.entity.T_user;

import java.util.Optional;

public interface UserService {
    public Optional<T_user> findUserById(String id);
    //public Optional<T_user> findUser(String name,String pwd);
    public Object save(T_user user);
}
