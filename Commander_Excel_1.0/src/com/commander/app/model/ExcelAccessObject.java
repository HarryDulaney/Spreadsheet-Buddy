package com.commander.app.model;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

@XmlRootElement
public class ExcelAccessObject extends ProjectElement {

	/**
	 * The current Workbook being operated on
	 */
	private static Workbook wB;
	/**
	 * The current Sheet being operated on
	 */
	private static Sheet sheet;
	/**
	 * The current Cell being operated on
	 */
	private static Cell cell;

	/**
	 * Like a DB object this is an "Excel Access Object"
	 */

	/**
	 * 
	 * @param file      - System file containing location path for the worksheet
	 * @param colValue  - The header or title of the column to retrieve
	 * @param sheetname
	 * @return ArrayList
	 */
	public static ArrayList<String> getColumn(File file, String colValue, String sheetname) {

		ArrayList<String> colList = new ArrayList<>();

		try {
			wB = new XSSFWorkbook(file);
		} catch (Exception e) {
			e.printStackTrace();
			showAlert("IO Exception Thrown", e);
		}
		try {
			sheet = wB.getSheet(sheetname);
		} catch (Exception e) {
			showAlert("Something went wrong try to load the sheet", e);
		}

		if (sheet != null) {

			Row headerRow = sheet.getRow(0); // The first row containing the headers to search
			int columnNum = 0; // the horizontal index of the column we will return
			int numColums = headerRow.getPhysicalNumberOfCells(); // Number of non-null cells in the header row

			for (int i = 0; i < numColums; i++) {

				Cell c = headerRow.getCell(i);

				if (c != null && c.getCellType() == CellType.STRING) {

					if (c.getStringCellValue() == colValue) {
						columnNum = i;
						break;
					}

				} else {

				}

			}
			if (columnNum != 0) {

				for (int i = 1; i < sheet.getLastRowNum(); i++) {

					Row currentRow = sheet.getRow(i);
					Cell c = currentRow.getCell(columnNum);

					colList.add(c.getStringCellValue());

				}

			}
		}

		return colList;

	}

	public static void showAlert(String contentText, Exception e) {

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Exception Dialog");
		alert.setHeaderText("Something went wrong");
		alert.setContentText(contentText);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("The exception stacktrace was:");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		alert.getDialogPane().setExpandableContent(expContent);

	}

	public Workbook getwB() {
		return wB;
	}

	public Sheet getSheet() {
		return sheet;
	}

	public Cell getCell() {
		return cell;

	}

}
