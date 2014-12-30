package com.paperuni.demo.web.selectoption;

import java.util.ArrayList;
import java.util.List;

public class Referencingformat {
	
	private String format;
	
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Referencingformat(String format){
		this.format = format;
	}
	
	public static List<Referencingformat> getAllReferencingformat(){
		List<Referencingformat> viewlist = new ArrayList<Referencingformat>();
		viewlist.add(new Referencingformat("NO NEED"));
		viewlist.add(new Referencingformat("Harvard"));
		viewlist.add(new Referencingformat("APA"));
		viewlist.add(new Referencingformat("MLA"));
		viewlist.add(new Referencingformat("Chicago"));
		viewlist.add(new Referencingformat("Turabian"));
		viewlist.add(new Referencingformat("Vancouver"));
		viewlist.add(new Referencingformat("Others"));
		return viewlist;
	}

}
