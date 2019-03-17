package com.spring.boot.springbootfirstwebapp.model;

import java.util.Date;

import javax.validation.constraints.Size;

public class TodoTemp {

//	private int id;

	@Size(min = 10, message = "Enter minimum 10 characters")
	private String desc;

	private Date targetDate;

	public TodoTemp() {

	}

	public TodoTemp(String desc) {
		this.desc = desc;
	}

	public TodoTemp(String desc, Date targetDate) {
		this.targetDate = targetDate;
		this.desc = desc;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
