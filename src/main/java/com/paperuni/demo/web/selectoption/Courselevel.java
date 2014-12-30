package com.paperuni.demo.web.selectoption;

import java.util.ArrayList;
import java.util.List;

public class Courselevel {
	
	private String level;
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Courselevel(String level){
		this.level = level;
	}
	
	public static List<Courselevel> getAllCourses(){
		List<Courselevel> viewlist = new ArrayList<Courselevel>();
		viewlist.add(new Courselevel("LANGUAGE"));
		viewlist.add(new Courselevel("HIGH SCHOOL"));
		viewlist.add(new Courselevel("FOUNDATION"));
		viewlist.add(new Courselevel("UNDERGRADUATE"));
		viewlist.add(new Courselevel("POSTGRADUATE/MASTER"));
		viewlist.add(new Courselevel("PHD"));
		return viewlist;
	}

}
