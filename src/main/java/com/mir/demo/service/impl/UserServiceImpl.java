package com.mir.demo.service.impl;

import com.mir.demo.entity.T_user;
import com.mir.demo.dao.T_userRepository;
import com.mir.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private T_userRepository repository;

    @Override
    public Optional<T_user> findUserById(String id) {
        return repository.findById(id);
    }


    @Override
    public Object save(T_user user) {
        return  repository.save(user);
    }
}
