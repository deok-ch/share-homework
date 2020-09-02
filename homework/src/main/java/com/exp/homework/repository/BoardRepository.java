package com.exp.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exp.homework.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

}
