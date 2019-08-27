package com.commander.app.model.tasks;

import java.io.File;
import java.util.ArrayList;

import com.codoid.products.exception.FilloException;
import com.commander.app.model.ExcelAO;
import com.commander.app.model.MyTask;

import javafx.scene.control.TextInputDialog;

public class DuplicateChecker extends MyTask {

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

	public ArrayList<String> checkForDuplicates() throws FilloException {

		ArrayList<String> duplicates = new ArrayList<>();

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Compare For Duplicates Task ");
		dialog.setHeaderText("Task data requested...");
		dialog.setContentText("For " + fileOne.getName() + " enter the name of the worksheet " + "that contains the "
				+ columnToCheck);
		dialog.showAndWait();
		ArrayList<String> fileOneList = ExcelAO.getColumn(fileOne, columnToCheck, dialog.getResult());

		TextInputDialog dialogTwo = new TextInputDialog();
		dialogTwo.setTitle("Compare For Duplicates Task ");
		dialogTwo.setHeaderText("Task data requested...");
		dialogTwo.setContentText("For " + fileTwo.getName() + " enter the name of the worksheet " + "that contains the "
				+ columnToCheck);
		dialogTwo.showAndWait();

		ArrayList<String> fileTwoList = ExcelAO.getColumn(fileTwo, columnToCheck, dialogTwo.getResult());

		for (String str : fileOneList) {
			

			if (fileTwoList.contains(str)) {
				
				duplicates.add(str);

			}
		}
		return duplicates;
	}

}
