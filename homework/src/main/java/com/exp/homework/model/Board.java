package com.exp.homework.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50)
	private String name;
	
	@Column(length = 50, nullable = false)
	@NotBlank(message = "제목을 입력하세요.")
	private String title;
	
	@Column(length = 2000, nullable = false)
	@NotBlank(message = "내용을 입력하세요.")
	private String contents;
	
	private Date regdate;
	private Long hit;
	private String filepath;
	
	@Column(length = 50, nullable = false)
	private String email;
	
	
	public Board() {
		this.regdate = new Date();
	}
}
