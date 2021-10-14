package com.cos.blog.reposity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

//Dao
//자동으로 Bean 등
//@Reposity 생략가능
public interface UserReposity extends JpaRepository<User, Integer>{

}
