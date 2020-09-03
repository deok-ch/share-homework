package com.exp.homework.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import com.exp.homework.common.Common;
import com.exp.homework.model.Board;
import com.exp.homework.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService service;
	
	@GetMapping("/board")
	public String main(Model model, Integer page) {
		if(page == null) page = 0;
		Page<Board> paging = service.paging(PageRequest.of(page, 10, Direction.ASC,"id"));
		model.addAttribute("list", paging);
		return "board/list";
	}
	
	@GetMapping("/board/**")
	public String select(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

		String path = (String) request.getAttribute(
	            HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		Long id = Common.replacePath(path, "/board/");
		Board board = service.getOne(id);
		model.addAttribute("board", board);
		return "board/view";
	}
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/board/create")
	public String create() {
		return "board/writeform";
	}
	
	@PostMapping("/board/save")
	public String save(Board board) {
		System.out.println(board.toString());
		//service.save(board);
		return "board/list";
	}
	
	@GetMapping("/board/delete/**")
	public String delete(Model model, HttpServletRequest request) {
		String path = (String) request.getAttribute(
	            HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		Long id = Common.replacePath(path, "/board/delete/");
		service.delete(id);
		return "board/list";
	}
}
