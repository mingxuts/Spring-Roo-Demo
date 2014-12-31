package com.paperuni.demo.web.selectoption;

import java.util.ArrayList;
import java.util.List;

public class Groupname {
	
	private String name;
	private String label;
	
	public Groupname(String name, String label){
		this.name = name;
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public static List<Groupname> getAllGroupnames(){
		List<Groupname> viewlist = new ArrayList<Groupname>();
		viewlist.add(new Groupname("ROLE_STUDENT", "Student"));
		viewlist.add(new Groupname("ROLE_SUBADMIN", "Sub Admin"));
		return viewlist;
	}
}
