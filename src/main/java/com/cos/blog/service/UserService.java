package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.reposity.UserReposity;

@Service
public class UserService {

	@Autowired
	private UserReposity userReposity;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional //하나의 트랜잭션으로 묶여서 하나의 메소드가성공하면 커밋 아니면 롤
	public void join(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.User);
		userReposity.save(user);
	}
	/*
	 * @Transactional(readOnly = true) //select 할때 트랜잭션 시,서비스 종료시에 트랜잭션 종료(정합성)
	 * public User login(User user) { return
	 * userReposity.findByUsernameAndPassword(user.getUsername(),user.getPassword())
	 * ; }
	 */
}
