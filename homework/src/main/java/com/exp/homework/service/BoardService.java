package com.exp.homework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.homework.model.Board;
import com.exp.homework.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository repository;
	
	public List<Board> getAll(){		//게시글 전체 불러오기
		return repository.findAll();
	}
	
	public void save(Board board) {		//게시글 입력, 수정
		repository.save(board);
	}
	@Transactional
	public void delete(Long id) {		//게시글 삭제
		repository.deleteById(id);
	}
	public Page<Board> paging(Pageable pageable){		//게시글 전체 불러오기 페이징
		return repository.findAll(pageable);
	}
	public Board getOne(Long id) {		//게시글 상세
		return repository.getOne(id);
	}

	
}
