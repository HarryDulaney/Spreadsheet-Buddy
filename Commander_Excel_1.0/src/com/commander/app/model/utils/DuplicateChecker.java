package com.commander.app.model.utils;

import java.io.File;
import java.util.ArrayList;

import com.commander.app.PHelper;
import com.commander.app.model.ExcelAccessObject;

import javafx.scene.control.TextInputDialog;

public class DuplicateChecker extends SpreadSheetTask {

	private File fileOne;
	private File fileTwo;
	private String columnToCheck;

	public DuplicateChecker(File fileOne, File fileTwo, String columnToCheck) {
		this.fileOne = fileOne;
		this.fileTwo = fileTwo;
		this.columnToCheck = columnToCheck;
	}

	public File getFileOne() {
		return fileOne;
	}

	public File getFileTwo() {
		return fileTwo;
	}

	public String getColumnToCheck() {
		return columnToCheck;
	}

	public ArrayList<String> checkForDuplicates()  {

		ArrayList<String> duplicates = new ArrayList<>();
		
		String sheetName = PHelper.showInputPrompt("Task data requested...", 
				"For " + fileOne.getName() + " enter the name of the worksheet "
							+ "that contains the "
								+ columnToCheck, "Compare For Duplicates Task ");
		
		if (sheetName != null) {
			ArrayList<String> fileOneList = ExcelAccessObject.getColumn(fileOne, columnToCheck, sheetName);

			TextInputDialog dialogTwo = new TextInputDialog();
			dialogTwo.setTitle("Compare For Duplicates Task ");
			dialogTwo.setHeaderText("Task data requested...");
			dialogTwo.setContentText("For " + fileTwo.getName() + " enter the name of the worksheet "
					+ "that contains the " + columnToCheck);
			dialogTwo.showAndWait();

			if (dialogTwo.getResult() != null) {

				ArrayList<String> fileTwoList = ExcelAccessObject.getColumn(fileTwo, columnToCheck, dialogTwo.getResult());

				for (String str : fileOneList) {

					if (fileTwoList.contains(str)) {

						duplicates.add(str);

					}
				}
			}
		}
		return duplicates;
	}

}
