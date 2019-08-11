package com.commander.app.model;

import org.apache.commons.csv.CSVFormat;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import com.opencsv.CSVWriter;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Reader;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import com.commander.app.model.SpecialObj;

import InProcess.abstractTask;

/**
 * @author H.G. Dulaney IV
 */

public class CSVfilter extends abstractTask {

	private File csvFilePath;

	public CSVfilter() {

	}

	public CSVfilter(File file) {

		this.csvFilePath = file;

	}

	/**
	 * @param stringList takes in Array of Strings that represent the headers the
	 *                   user chose to extract from the csvFilePath
	 * @param finishFile The user defines this file to output the extracted data
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public void extractToXLSX(ArrayList<String> stringL) throws IOException, InvalidFormatException {

		final int stringListsize = stringL.size();

		String[] stringList = new String[stringListsize];

		for (int i = 0; i < stringListsize; i++) {
			stringList[i] = stringL.get(i);
		}

		String col1 = "";
		String col2 = "";
		String col3 = "";
		String col4 = "";
		String col5 = "";

		ArrayList<SpecialObj> objList = new ArrayList<>();

		Reader reader = Files.newBufferedReader(Paths.get(csvFilePath.getAbsolutePath()));

		CSVParser records = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase());

		Workbook wb = new XSSFWorkbook();
		Sheet sheet = null;

		if (stringListsize == 1) {
			for (CSVRecord record : records) {

				col1 = record.get(stringList[0]);

				SpecialObj ws = new SpecialObj();
				ws.setStr1(col1);
				objList.add(ws);
			}

			TextInputDialog inDialog = new TextInputDialog();
			inDialog.setHeaderText("Information Requested");
			inDialog.setContentText("Input a name for your new spreadsheet. "
					 			+ 	"Don't forget to select a .csv or .xlsx output format");
			inDialog.setHeight(150);
			inDialog.setWidth(235);
			inDialog.showAndWait();

			if (inDialog.getResult().isEmpty()) {
				inDialog.setContentText("Sheet name can not be blank. Please input a name for your spreadsheet.");
				inDialog.showAndWait();

			}

			String sheetName = inDialog.getResult();
			sheet = wb.createSheet(sheetName);

			Row header = sheet.createRow(0);

			for (int i = 0; i < stringListsize; i++) {
				Cell cell = header.createCell(i);
				cell.setCellValue(stringList[0]);

			}
			int numRow = 1;

			for (SpecialObj ws : objList) {

				Row row = sheet.createRow(numRow++);

				row.createCell(0).setCellValue(ws.getStr1());

			}
		} else if (stringListsize == 2) {
			for (CSVRecord record : records) {

				col1 = record.get(stringList[0]);
				col2 = record.get(stringList[1]);

				SpecialObj ws = new SpecialObj();
				ws.setStr1(col1);
				ws.setStr2(col2);
				objList.add(ws);
			}

			TextInputDialog inDialog = new TextInputDialog();
			inDialog.setContentText("Input a name for your new spreadsheet. "
		 			+ 	"Don't forget to select a .csv or .xlsx output format");
			inDialog.setHeaderText("Information Requested");
			inDialog.setHeight(150);
			inDialog.setWidth(235);
			inDialog.showAndWait();

			if (inDialog.getResult().isEmpty()) {
				inDialog.setContentText("Sheet name can not be blank. Please input a name for your spreadsheet.");
				inDialog.showAndWait();

			}

			String sheetName = inDialog.getResult();
			sheet = wb.createSheet(sheetName);

			Row header = sheet.createRow(0);

			for (int i = 0; i < 2; i++) {
				Cell cell = header.createCell(i);
				cell.setCellValue(stringList[i]);
			}

			int numRow = 1;

			for (SpecialObj ws : objList) {

				Row row = sheet.createRow(numRow++);

				row.createCell(0).setCellValue(ws.getStr1());
				row.createCell(1).setCellValue(ws.getStr2());
			}
		} else if (stringListsize == 3) {

			for (CSVRecord record : records) {

				col1 = record.get(stringList[0]);
				col2 = record.get(stringList[1]);
				col3 = record.get(stringList[2]);

				SpecialObj ws = new SpecialObj();
				ws.setStr1(col1);
				ws.setStr2(col2);
				ws.setStr3(col3);
				objList.add(ws);

			}

			TextInputDialog inDialog = new TextInputDialog();
			inDialog.setContentText("Input a name for your new spreadsheet. "
		 			+ 	"Don't forget to select a .csv or .xlsx output format");
			inDialog.setHeaderText("Information Requested");
			inDialog.setHeight(150);
			inDialog.setWidth(235);
			inDialog.showAndWait();

			if (inDialog.getResult().isEmpty()) {
				inDialog.setContentText("Sheet name can not be blank. Please input a name for your spreadsheet.");
				inDialog.showAndWait();

			}

			String sheetName = inDialog.getResult();
			sheet = wb.createSheet(sheetName);

			Row header = sheet.createRow(0);

			for (int i = 0; i < stringListsize; i++) {
				Cell cell = header.createCell(i);
				cell.setCellValue(stringList[i]);

			}
			int numRow = 1;

			for (SpecialObj ws : objList) {

				Row row = sheet.createRow(numRow++);

				row.createCell(0).setCellValue(ws.getStr1());
				row.createCell(1).setCellValue(ws.getStr2());
				row.createCell(2).setCellValue(ws.getStr3());
			}
		} else if (stringListsize == 4) {

			for (CSVRecord record : records) {

				col1 = record.get(stringList[0]);
				col2 = record.get(stringList[1]);
				col3 = record.get(stringList[2]);
				col4 = record.get(stringList[3]);

				SpecialObj ws = new SpecialObj();
				ws.setStr1(col1);
				ws.setStr2(col2);
				ws.setStr3(col3);
				ws.setStr4(col4);

				objList.add(ws);

			}

			TextInputDialog inDialog = new TextInputDialog();
			inDialog.setContentText("Input a name for your new spreadsheet. "
		 			+ 	"Don't forget to select a .csv or .xlsx output format");
			inDialog.setHeaderText("Information Requested");
			inDialog.setHeight(150);
			inDialog.setWidth(235);
			inDialog.showAndWait();

			if (inDialog.getResult().isEmpty()) {
				inDialog.setContentText("Sheet name can not be blank. Please input a name for your spreadsheet.");
				inDialog.showAndWait();

			}

			String sheetName = inDialog.getResult();
			sheet = wb.createSheet(sheetName);

			Row header = sheet.createRow(0);

			for (int i = 0; i < stringListsize; i++) {
				Cell cell = header.createCell(i);
				cell.setCellValue(stringList[i]);

			}

			int numRow = 1;

			for (SpecialObj ws : objList) {

				Row row = sheet.createRow(numRow++);

				row.createCell(0).setCellValue(ws.getStr1());
				row.createCell(1).setCellValue(ws.getStr2());
				row.createCell(2).setCellValue(ws.getStr3());
				row.createCell(3).setCellValue(ws.getStr4());

			}

		} else if (stringListsize == 5) {
			for (CSVRecord record : records) {

				col1 = record.get(stringList[0]);
				col2 = record.get(stringList[1]);
				col3 = record.get(stringList[2]);
				col4 = record.get(stringList[3]);
				col5 = record.get(stringList[4]);

				SpecialObj ws = new SpecialObj();
				ws.setStr1(col1);
				ws.setStr2(col2);
				ws.setStr3(col3);
				ws.setStr4(col4);
				ws.setStr5(col5);

				objList.add(ws);

			}

			TextInputDialog inDialog = new TextInputDialog();
			inDialog.setContentText("Input a name for your new spreadsheet. "
		 			+ 	"Don't forget to select a .csv or .xlsx output format");
			inDialog.setHeaderText("Information Requested");
			inDialog.setHeight(150);
			inDialog.setWidth(235);
			inDialog.showAndWait();

			if (inDialog.getResult().isEmpty()) {
				inDialog.setContentText("Sheet name can not be blank. Please input a name for your spreadsheet.");
				inDialog.showAndWait();

			}

			String sheetName = inDialog.getResult();
			sheet = wb.createSheet(sheetName);

			Row header = sheet.createRow(0);

			for (int i = 0; i < stringListsize; i++) {
				Cell cell = header.createCell(i);
				cell.setCellValue(stringList[i]);

			}

			int numRow = 1;

			for (SpecialObj ws : objList) {

				Row row = sheet.createRow(numRow++);

				row.createCell(0).setCellValue(ws.getStr1());
				row.createCell(1).setCellValue(ws.getStr2());
				row.createCell(2).setCellValue(ws.getStr3());
				row.createCell(3).setCellValue(ws.getStr4());
				row.createCell(4).setCellValue(ws.getStr5());

			}

		}
		for (int i = 0; i < stringListsize; i++) {
			sheet.autoSizeColumn(i);
		}

		FileChooser fchooser = new FileChooser();
		fchooser.setTitle("Name and save your \".xlsx\" file");
		fchooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".xlsx", "*.xlsx"));
		fchooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".csv", ".csv"));
		File finishFile = fchooser.showSaveDialog(new Stage(StageStyle.UTILITY));

		if (finishFile != null) {
			if (finishFile.toString().contains(".xlsx")) {

				try {
					FileOutputStream outStream = new FileOutputStream(finishFile);
					wb.write(outStream);

					Alert alrt = new Alert(AlertType.CONFIRMATION);
					alrt.setHeaderText("Task Completed");
					alrt.setContentText(
							"Your spreadsheet was created successfully. It can be found at: "
									+ finishFile.getAbsolutePath());
					alrt.showAndWait();

				} catch (IOException e) {
					Alert alr = new Alert(AlertType.ERROR);
					alr.setHeaderText("Error writing file");
					alr.setContentText(
							"Something went wrong creating your file. " + "This error usually results from choosing"
									+ "location to save your finished file that's protected by security software. "
									+ "Check you anti-virus software for more information");
					alr.showAndWait();
				} finally {

					wb.close();
					records.close();

				}
			} else if (finishFile.toString().contains(".csv")) {

				try (Writer writer = Files.newBufferedWriter(Paths.get(finishFile.getAbsolutePath()));

						CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR,
								CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER,
								CSVWriter.DEFAULT_LINE_END);) {

					csvWriter.writeNext(stringList);

					for (SpecialObj ws : objList) {

						csvWriter.writeNext(ws.getStrings());
					}
					Alert alr = new Alert(AlertType.INFORMATION);
					alr.setHeaderText("Task Completed");
					alr.setContentText(
							"Your spreadsheet was created successfully. It can be found at: "
									+ finishFile.getAbsolutePath());
					alr.showAndWait();
				}

			}

		}

	}

	public File setUserFile() {

		return csvFilePath;

	}

}
