package com.cos.blog.reposity;


import org.springframework.data.jpa.repository.JpaRepository;
import com.cos.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	
}
