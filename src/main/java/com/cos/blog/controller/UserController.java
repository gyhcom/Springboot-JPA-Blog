package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*인증이 안되어 있는 사용자들이 출입할수 있는 경로 추가
그냥 주소가 / 이면 index.jsp 허용*/
@Controller
public class UserController {
	@GetMapping("/auth/joinForm")
	public String joinForm() {

		return "user/joinForm";
	}
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		
		return "user/loginForm";
	}
}
