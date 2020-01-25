package main.java.com.commander.app.model.utils;

import java.io.File;
import java.util.ArrayList;

import main.java.com.commander.app.PHelper;
import main.java.com.commander.app.model.ExcelAccessObject;

public class DuplicateChecker extends SSTask {

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

	public ArrayList<String> checkForDuplicates() throws Exception {

		ArrayList<String> duplicates = new ArrayList<>();

		String sheetName = PHelper.showInputPrompt("Task data requested...",
				"For " + fileOne.getName() + " enter the name of the worksheet " + "that contains the " + columnToCheck,
				"Compare For Duplicates Task ");

		if (sheetName != null) {

			ArrayList<String> fileOneList = ExcelAccessObject.getColumn(fileOne, columnToCheck, sheetName);

			String dialogRes = PHelper.showInputPrompt("Task data requested...", "For " + fileTwo.getName()
					+ " enter the name of the worksheet " + "that contains the " + columnToCheck,
					"Compare For Duplicates Task ");

			if (dialogRes != null) {

				ArrayList<String> fileTwoList = ExcelAccessObject.getColumn(fileTwo, columnToCheck, dialogRes);

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
