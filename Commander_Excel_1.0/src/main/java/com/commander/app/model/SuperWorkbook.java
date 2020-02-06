package com.commander.app.model;

import java.io.File;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SuperWorkbook extends XSSFWorkbook {

	
	private String name;
	private File fileLoc;

	public SuperWorkbook() {
		super();
	}

	public SuperWorkbook(File file) {
		fileLoc = file;
	}

	public String getName() {
		return name;
	}

	public void seName(String name) {
		this.name = name;
	}

	public File getFile() {
		return fileLoc;
	}

	public void setFile(File fileLoc) {
		this.fileLoc = fileLoc;
	}

}
