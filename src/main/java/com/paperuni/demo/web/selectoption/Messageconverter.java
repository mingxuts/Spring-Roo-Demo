package com.paperuni.demo.web.selectoption;

import com.paperuni.demo.model.TdMessage;

public class Messageconverter {
	
	private String key;
	private TdMessage message;
	
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
	
	public Messageconverter(String key, TdMessage message){
		this.key = key;
		this.message = message;
	}
	
}
