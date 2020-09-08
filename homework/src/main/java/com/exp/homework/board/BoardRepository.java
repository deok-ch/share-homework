package com.exp.homework.board;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exp.homework.board.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

}
