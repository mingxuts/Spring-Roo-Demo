package com.paperuni.demo.web.selectoption;

import java.util.ArrayList;
import java.util.List;

public class Presentationformat {
	
	private String format;
	
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Presentationformat(String format){
		this.format = format;
	}
	
	public static List<Presentationformat> getAllPresentationformat(){
		List<Presentationformat> viewlist = new ArrayList<Presentationformat>();
		viewlist.add(new Presentationformat("REPORT"));
		viewlist.add(new Presentationformat("ESSAY"));
		viewlist.add(new Presentationformat("PROPOSAL"));
		viewlist.add(new Presentationformat("LITERATURE REVIEW"));
		viewlist.add(new Presentationformat("DISSERTATION/THESIS"));
		viewlist.add(new Presentationformat("PPT/SPEECH NOTES"));
		viewlist.add(new Presentationformat("BOOK REVIEW/SUMMARY"));
		viewlist.add(new Presentationformat("ONLINE HOMEWORK"));
		viewlist.add(new Presentationformat("TERMPAPER"));
		viewlist.add(new Presentationformat("PROGRAMMING"));
		viewlist.add(new Presentationformat("OTHERS"));
		return viewlist;
	}
}
