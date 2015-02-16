package com.paperuni.demo.web.selectoption;

import java.util.ArrayList;
import java.util.List;

public class Includegraph {
	
	private String include;
	
	public String getInclude() {
		return include;
	}

	public void setInclude(String include) {
		this.include = include;
	}

	public Includegraph(String include){
		this.include = include;
	}
	
	public static List<Includegraph> getAllIncludegraphs(){
		List<Includegraph> viewlist = new ArrayList<Includegraph>();
		viewlist.add(new Includegraph("NO NEED"));
		viewlist.add(new Includegraph("Yes"));
//		viewlist.add(new Includegraph("CALCULATION"));
//		viewlist.add(new Includegraph("DIAGRAM"));
//		viewlist.add(new Includegraph("DATA ANALYSIS"));
		return viewlist;
	}
}
