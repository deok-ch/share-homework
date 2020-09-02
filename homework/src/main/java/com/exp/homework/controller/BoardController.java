package com.exp.homework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exp.homework.model.Board;
import com.exp.homework.service.BoardService;

@RestController
public class BoardController {

	@Autowired
	private BoardService service;
	
	@GetMapping("/home")
	public List<Board> main() {
		List<Board> board = service.getAll();
		return board;
	}
}
