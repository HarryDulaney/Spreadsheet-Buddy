package func;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import control.MainMenuController;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import model.Project;

public class Writer {
	
	public DirectoryChooser directoryChooser;
	public String nameOfWorkbook;
	

	public Writer(String nameOfWorkbook , DirectoryChooser directoryLocation) {
		this.nameOfWorkbook = nameOfWorkbook;
		this.directoryChooser = directoryLocation;

	}


	public void writeWorkbook() throws Exception{
		
		Workbook workbook = new XSSFWorkbook();

		FileOutputStream fileOut = new FileOutputStream(new File(this.directoryChooser.toString() + nameOfWorkbook +".xlsx"));
		
		workbook.createSheet("Sheet 1");

		workbook.write(fileOut);
			
		workbook.close();

	}

}
