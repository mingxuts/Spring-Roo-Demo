package com.paperuni.demo.web.selectoption;

import java.util.ArrayList;
import java.util.List;

public class Wordcount {
	
	private String value;
	private int id;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Wordcount(int id, String value){
		this.id = id;
		this.value = value;
	}
	
	public static List<Wordcount> getAllWordCountValues(){
		List<Wordcount> viewlist = new ArrayList<Wordcount>();
		viewlist.add(new Wordcount(250, " 250 words/ 1PAGE"));
		for (int i = 2; i<100; i++){
			String s = String.valueOf(250 * i) + "words/ " + String.valueOf(i) + " PAGES";
			viewlist.add(new Wordcount(250 * i, s));
		}
		viewlist.add(new Wordcount(25250, "25250 words above/100 PAGES+"));
		return viewlist;
	}

}
