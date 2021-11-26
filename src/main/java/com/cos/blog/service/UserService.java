package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.reposity.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	
	@Transactional
	public User findName(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}
	
	@Transactional //하나의 트랜잭션으로 묶여서 하나의 메소드가성공하면 커밋 아니면 롤
	public void join(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.User);
		userRepository.save(user);
	}
	
	@Transactional
	public void update(User user) {
		//수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 오브젝트를 수정
		//select를 해서 User오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해
		//영속화된 오브젝트를 변경하면 자동으로 DB에 Update를 날려줌
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원찾기 실패");
		});
		
		if(persistance.getOauth() == null || persistance.getOauth() == "") {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
		}
		
		//회원수정함수 종료시 = 서비스 종료 = 트랜잭션 종 = commit가 자동으로 됩니다.
		//영속화된 persistance 객체의 변화를 감지해 더티체킹을 해서 update문을 날려
	}
	/*
	 * @Transactional(readOnly = true) //select 할때 트랜잭션 시,서비스 종료시에 트랜잭션 종료(정합성)
	 * public User login(User user) { return
	 * userReposity.findByUsernameAndPassword(user.getUsername(),user.getPassword())
	 * ; }
	 */
}
