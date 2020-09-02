package com.exp.homework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exp.homework.model.Board;
import com.exp.homework.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository repository;
	
	public List<Board> getAll(){
		return repository.findAll();
	}
}
