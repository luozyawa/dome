package com.mir.demo.dao;

import com.mir.demo.entity.T_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface T_userRepository extends JpaRepository<T_user,String>{
    @Query(value = "select * from t_user where userName=:u and password=:p",nativeQuery = true)
    public List<T_user> queryUser(@Param("u") String u, @Param("p") String p);

}

