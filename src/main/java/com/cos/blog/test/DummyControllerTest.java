package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.reposity.UserRepository;

@RestController
public class DummyControllerTest {
	
	
	@Autowired //의존성 주입
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);	
		} catch (EmptyResultDataAccessException e) {
			return "해당 데이터는 없습니다.";
		}
		
		
		
		return "삭제되었습니다";
	}
	
	//email, password
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id :"+id);
		System.out.println("password :" + requestUser.getPassword());
		System.out.println("email :" + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});

		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		
//		userReposity.save(user);
		return user;
	}
	
	
	//{id}로 주소로 파라메터 전달 받을수 있
	//localhost:8080/dummy/user/3
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2,sort="id",direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUsers = userRepository.findAll(pageable);
		List<User> users = pagingUsers.getContent();
		return users;
	}
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		//user 4 를 찾았을때 없으면 Null이 되기 때문에
		//그럼 return user 가 null이 되면 에러이기 때문에
		//Optional 로 user를 감싸서 가져와서 null인지 아닌지 판단
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

		@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저가 없습니다 :" +id);
			}
		});
		return user;
		
		//user 객체는 java 오브젝트이 근데 요청은 웹에서 온
		//그래서 변환을 해야 한다 웹브라우저가 이해할수 있게 변환해야한다
		//그게 바로 json(Gson)
		//스프링부트는 messageconverter라는 애가 응답시 자동으로 작동해서 jackson 라이브러리를 호출해서
		//user 오브젝트를 json으로 변환해서 던져준다
		
		
	}
	//http://localhost:8080/blog/dummy/join 요청
	//http의 바디에 있는 변수들로 받아
	@PostMapping("/dummy/join")
	private String join(User user) { //Key=Value 약속된규칙 
		System.out.println("username" + user.getUsername());
		System.out.println("password" + user.getPassword());
		System.out.println("email" + user.getEmail());
		
		user.setRole(RoleType.User);
		userRepository.save(user);
		return "회원가입완료";
	}
}
