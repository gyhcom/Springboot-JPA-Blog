package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.reposity.BoardRepository;
import com.cos.blog.reposity.ReplyRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	
	@Transactional //하나의 트랜잭션으로 묶여서 하나의 메소드가성공하면 커밋 아니면 롤
	public void write(Board board,User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	@Transactional(readOnly = true)
	public Page<Board> boardList(Pageable pageble){
		return boardRepository.findAll(pageble);
	}
	@Transactional(readOnly = true)
	public Board boardDetail(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을수 없습니다.");
		});
	}
	@Transactional
	public void boardDelete(int id) {
			boardRepository.deleteById(id);
	}
	
	@Transactional
	public void boardUpdate(int id, Board requestboard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 수정 실패 : 아이디를 찾을수 없습니다.");
		}); //영속화 완료
		board.setTitle(requestboard.getTitle());
		board.setContent(requestboard.getContent());
		//해당 함수 종료시 (service 종료될때) 트랜잭션이 종료되면서 더티체킹을 함 - 자동업데이트가 됨
	}
	
	@Transactional
	public void replyWrite(User user,int boardId,Reply requestReply) {
		Board board = boardRepository.findById(boardId)
		.orElseThrow(()->{
			return new IllegalArgumentException("댓글쓰기 실패 : 게시글을 찾을수 없습니다.");
		});
		
		requestReply.setUser(user);
		requestReply.setBoard(board);
		
		replyRepository.save(requestReply);
		
	}
}
