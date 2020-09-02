package com.exp.homework.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 50)
	private String name;
	@Column(length = 50)
	private String title;
	@Column(length = 2000)
	private String contents;
	@Column(length = 50)
	private String post_pass;
	private Date regdate;
	private Long hit;
}
