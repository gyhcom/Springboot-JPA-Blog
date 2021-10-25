package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//ORM이라는건
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //User 클래스가 밑에 변수를 읽어서 자동으로 테이블 생성해 줌
//@DynamicInsert insert시에 null 값 제외
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에 연결된 Db의 전략을 따라간다.
	private int id; //시퀀스, auto_increamt
	
	@Column(unique = true,nullable = false, length=30 )
	private String username;
	
	@Column(nullable = false, length=100) // 해쉬(비밀번호암호화)
	private String password;
	
	@Column(nullable = false, length=50)
	private String email;
	
	//DB는 Role 타입이 없음
	//@ColumnDefault("user")
	@Enumerated(EnumType.STRING)
	private RoleType role; //정확하게 하려면 Enum을 써야함 이걸 쓰면 어떤 데이터의 Domain을 생성할수 있다
	
	@CreationTimestamp //시간이 자동생
	private Timestamp createDate;
}
