package com.exp.homework.board;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Sort.Direction;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.exp.homework.common.Common;
import com.exp.homework.board.Board;
import com.exp.homework.board.BoardService;


@Controller
public class BoardController {

	@Autowired
	private BoardService service;
	

	@GetMapping("/")
	public RedirectView home() {
		return new RedirectView("/board");
	}
	
	@GetMapping("/board")
	public String main(Model model, Integer page) {
		if(page == null) page = 0; else page--;		//paging 0부터 시작이기때문에..
		//jpa로 rownum 사용방법 찾기.
		Page<Board> paging = service.paging(PageRequest.of(page, 10, Direction.DESC,"id"));
		model.addAttribute("list", paging);
		model.addAttribute("nowpage", page+1);	//0에서 시작했기때문에 밖에선..
		model.addAttribute("pagecnt", paging.getTotalPages());
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
	
	@GetMapping("/board/create")
	public String create(Model model) {
		Board board = new Board();
		model.addAttribute("board", board);
		return "board/writeform";
	}
	
	@GetMapping("/board/update/**")
	public String update(Model model, HttpServletRequest request) {
		String path = (String) request.getAttribute(
	            HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		Long id = Common.replacePath(path, "/board/update/");
		Board board = service.getOne(id);
		model.addAttribute("board", board);
		return "board/writeform";
	}
	
	@PostMapping("/board/save")
	public String save(Model model, @Validated Board board, @RequestParam(name = "upload") MultipartFile file, HttpServletRequest request) throws IOException {
		
		board.setWriteid("oom177");		//로그인 미구현
		board.setName("chd");
		
		System.out.println(board.toString());
		
		if(!file.isEmpty()) {
			board.setFilepath(Common.fileuploads(file));	//파일저장
		}
		try{
			service.save(board);
		}//
		catch(Exception ex) {
			model.addAttribute("message", "글쓰기 실패.");
			model.addAttribute("returnurl", "/board");
			return "check";
		}
		model.addAttribute("message", "글쓰기 완료.");
		model.addAttribute("returnurl", "/board");
		return "check";
	}
	
	@GetMapping("/board/delete/**")
	public String delete(Model model, HttpServletRequest request) {
		String path = (String) request.getAttribute(
	            HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		Long id = Common.replacePath(path, "/board/delete/");
		try {
			service.delete(id);
		}catch(Exception ex) {
			model.addAttribute("message", "삭제 실패.");
			model.addAttribute("returnurl", "/board");
			return "check";
		}
		
		model.addAttribute("message", "삭제 완료.");
		model.addAttribute("returnurl", "/board");
		
		return "check";
	}
	
	
}
