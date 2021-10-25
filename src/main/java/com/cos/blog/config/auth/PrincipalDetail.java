package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

//스프링 시큐리티가 로그인을 가로채서 로그인이 완료 되면 Userdetails 의 타입 오브젝트들을 스프링 시큐리티 고유 세션에 저장
public class PrincipalDetail implements UserDetails{
	
	private User user; //콤포지션이라고 함 객체를 품고 있음 extends는 상속이고

	public PrincipalDetail(User user) {
		this.user = user;
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}
//계정이 만료되었는지 리턴한다. (true:만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
//비밀번호가 만료되었는지 리턴한다. (true:만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
//계정이 활성화(사용가능) 되어 있는지를 리턴한다.(true:활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}

//계정이 갖고 있는 권한을 리턴한다.(권한이 여러개 있어서 루프를 돌려야할수도 있는데 지금은 일단 하나)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList();
		collectors.add(()->{return "ROLE_"+user.getRole();});
		
		return collectors;
	}
	
}
