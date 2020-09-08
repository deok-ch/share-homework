package com.exp.homework.comment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50)
	private String writeid;
	
	@Column(length = 1000)
	private String contents;
	private Date regdate;
	
	public Comment() {
		this.regdate = new Date();
	}
}
