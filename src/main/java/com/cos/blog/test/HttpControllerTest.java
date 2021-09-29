package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {
	
	private static final String  TAG ="HttpControllerTest :"; 
	
	@GetMapping("http/get")
	public String getTest(Member m) {
		return null;
	}
	@PostMapping("http/post")
	public String postTest() {
		return "post요";
	}
	@PutMapping("http/put")
	public String putTest() {
		return "put요청";
	}
	@DeleteMapping("http/delete")
	public String delteTest() {
		return "delete 요청";
	}
	
	@GetMapping("http/lombok")
	public String lombokTest() {
	 Member m = new Member(1,"ssar","1234","email");
	 System.out.println(TAG+"getter"+m.getId());
	 m.setId(5000);
	 System.out.println(TAG+"getter"+m.getId());
	 return "lombok 테스트";
	}
}
