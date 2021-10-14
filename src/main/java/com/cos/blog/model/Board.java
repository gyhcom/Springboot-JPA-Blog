package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에 연결된 Db의 전략을 따라간다.
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob
	private String content; //섬머노트 라이브러리를 이요한 내용이 
	
	@ManyToOne(fetch = FetchType.EAGER) //Many = Board , One = user
	@JoinColumn(name="userId")
	private User user; //DB는 오브젝트를 저장할수 없음, 자바는 저장가
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)//Mapper 관계의 주인이아님 Fk가 아니기 때문에 DB에 컬럼 필요없어요 
	private List<Reply> reply;
	
	@ColumnDefault("0")
	private int count; //조회
	
	@CreationTimestamp
	private Timestamp createDate;
}
