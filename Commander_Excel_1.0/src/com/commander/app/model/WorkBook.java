package com.commander.app.model;

import java.io.File;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class WorkBook extends XSSFWorkbook {

	private static final String ELEMENT_TYPE = "Excel_Workbook";
	private File location;
	private int id;
	private List <XSSFSheet> sheets;
	

	public WorkBook() {
		id = makeId();
		System.out.println(id);

	}

	public File getLocation() {
		return location;
	}

	public void setLocation(File location) {
		this.location = location;
	}

	private int makeId() {

		int res = (int) (Math.random() * 999999 * Math.random());

		return res;

	}
	
	public String toString() {
		return "ELEMENT TYPE: " + ELEMENT_TYPE;
	}


}
