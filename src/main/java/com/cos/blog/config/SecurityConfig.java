package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


//빈 등록: 스프링 컨테이너에서 객체를 관리할수 있는것
@Configuration //빈등록(IoC관리)
@EnableWebSecurity //필터를 추가함 =스프링 시큐리티가 활성화 되어 있는데 해당 설정을 지금 이 파일에서 하겠다는뜻
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean //Ioc가 됨 리턴하는 값을 스프링이 관리
	public BCryptPasswordEncoder endcodePWD() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .csrf().disable()
	    .authorizeRequests()
	    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/auth/**")
	    .permitAll()
	    .anyRequest().authenticated()
	    .and()
	    .formLogin().loginPage("/auth/loginForm");

	}
	
}
