package com.paperuni.demo.web.selectoption;

import com.paperuni.demo.model.TdMessage;
import com.paperuni.demo.model.TdUserinfo;

public class AdminMessageconverter {
	
	private String key;
	private TdMessage message;
	private TdUserinfo student;
	private TdUserinfo writer;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public TdMessage getMessage() {
		return message;
	}
	public void setMessage(TdMessage message) {
		this.message = message;
	}
	public TdUserinfo getStudent() {
		return student;
	}
	public void setStudent(TdUserinfo student) {
		this.student = student;
	}
	public TdUserinfo getWriter() {
		return writer;
	}
	public void setWriter(TdUserinfo writer) {
		this.writer = writer;
	}	
	
	public AdminMessageconverter(String key, TdMessage message, TdUserinfo student, TdUserinfo writer){
		this.key = key;
		this.message = message;
		this.student = student;
		this.writer = writer;
	}

}
