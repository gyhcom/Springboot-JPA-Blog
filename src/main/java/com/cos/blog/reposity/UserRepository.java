package com.cos.blog.reposity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

//Dao
//자동으로 Bean 등
//@Reposity 생략가능
public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String username);
}

//JPA naming 전략
//	User findByUsernameAndPassword(String username, String password);