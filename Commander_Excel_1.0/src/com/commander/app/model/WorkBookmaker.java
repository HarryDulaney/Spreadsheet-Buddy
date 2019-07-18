package com.commander.app.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.commander.app.MainMenu;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * @author Harry Gingles Dulaney IV
 */

/**
 * Simple task to create a new spreadsheet
 * 
 * @see {@link Task}
 * 
 */
public class WorkBookmaker extends CommanderTask {

	public WorkBookmaker() {
		this(null, null);

	}

	/**
	 * Constructor for createSpreadSheetTask object
	 * 
	 * @param project
	 */
	public WorkBookmaker(String bookName, String sheetName) {

		this.p1 = new SimpleStringProperty(bookName);
		this.p2 = new SimpleStringProperty(sheetName);
		taskName = new SimpleStringProperty("New Workbook");

	}

	@Override
	public void executeTask() throws IOException {

		String filePath = MainMenu.getCurrentProject().getProjectFolder() + "\\" + p1.getValue() + ".xlsx";

		try {
			FileOutputStream fp = new FileOutputStream(filePath);
			Workbook wb = new XSSFWorkbook();
			wb.createSheet(p2.getValue());
			wb.write(fp);
			wb.close();

			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setHeaderText("Successful Operation");
			a.setContentText("You created a new workbook with one spreadsheet");
			a.show();
			
		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("I'm sorry something went wrong");
			alert.show();
			e1.printStackTrace();
		}

	
	}

}
