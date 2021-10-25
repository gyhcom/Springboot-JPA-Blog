package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;


//빈 등록: 스프링 컨테이너에서 객체를 관리할수 있는것
@Configuration //빈등록(IoC관리)
@EnableWebSecurity //필터를 추가함 =스프링 시큐리티가 활성화 되어 있는데 해당 설정을 지금 이 파일에서 하겠다는뜻
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private PrincipalDetailService principalDetailService;
	
	
	@Bean //Ioc가 됨 리턴하는 값을 스프링이 관리
	public BCryptPasswordEncoder endcodePWD() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(endcodePWD());
	}
	//시큐리티가 대신 로그인을 하는 그때 password를 가로챔 해당 password가 무엇으로 해쉬가 되었는지 알아야
	//같은 해쉬로 암호화 해서 DB에 있는 해쉬랑 비교를 할수 있음
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .csrf().disable()
	    .authorizeRequests()
	    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/auth/**")
	    .permitAll()
	    .anyRequest().authenticated()
	    .and()
	    .formLogin().loginPage("/auth/loginForm")
	    .loginProcessingUrl("/auth/loginProc") //스프링 시큐리티가 로그인을 가로챈다 해당주소로
	    .defaultSuccessUrl("/")
		;

	}
	
}
