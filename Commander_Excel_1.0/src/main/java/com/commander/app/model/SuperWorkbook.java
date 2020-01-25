package main.java.com.commander.app.model;

import java.io.File;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class SuperWorkbook extends XSSFWorkbook {

	@JsonInclude(value = Include.ALWAYS)
	private String workbookName;

	@JsonInclude(value = Include.ALWAYS)
	private File fileLoc;

	public SuperWorkbook() {
		super();
	}

	public SuperWorkbook(File file) {
		fileLoc = file;
	}

	public String getWorkbookName() {
		return workbookName;
	}

	public void setWorkbookName(String workbookName) {
		this.workbookName = workbookName;
	}

	public File getFileLoc() {
		return fileLoc;
	}

	public void setFileLoc(File fileLoc) {
		this.fileLoc = fileLoc;
	}

}
