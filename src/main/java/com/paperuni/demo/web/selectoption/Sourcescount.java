package com.paperuni.demo.web.selectoption;

import java.util.ArrayList;
import java.util.List;

public class Sourcescount {
	
	private String value;
	private int id;
	
	public Sourcescount(int id, String value){
		this.id = id;
		this.value = value;
	}

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

	public static List<Sourcescount> getAllSourcescountValues(){
		List<Sourcescount> viewlist = new ArrayList<Sourcescount>();
		viewlist.add(new Sourcescount(0, "NO NEED"));
		for (int i = 0; i < 10; i++){
			String s = String.valueOf(i * 5 + 1) + " - " + String.valueOf(i * 5 + 5) + "  ";
			viewlist.add(new Sourcescount(i * 5 + 5, s));
		}
		viewlist.add(new Sourcescount(51, "51+"));
		return viewlist;
	}
}
