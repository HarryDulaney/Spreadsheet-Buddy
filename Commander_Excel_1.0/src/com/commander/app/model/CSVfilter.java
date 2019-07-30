package com.commander.app.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.script.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CSVfilter extends abstractTask {
	
	
	
	public static String taskName = "CSV File Filter";
	private File file;

	public CSVfilter() {

		setTaskName(taskName);

		setFile(setUserFile());

	}

	public void extractToXLSX(String csvPath, ArrayList<String> parsedList) throws IOException {

		try (

				Reader reader = Files.newBufferedReader(Paths.get(csvPath));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader("col1", "col2", "col3")
						/**
						 * The ignoreHeaderCase setting is used make the header names case-insensitive,
						 * and the trim setting trims leading and trailing blank spaces from the column
						 * values.
						 */
						.withIgnoreHeaderCase().withTrim());

		) {
			for (CSVRecord csvRecord : csvParser) {
				String column1 = csvRecord.get("col1");
				String column2 = csvRecord.get("col2");
				String column3 = csvRecord.get("col3");

			}

		}

	}

	public File setUserFile() {

		FileChooser chooser = new FileChooser();
		chooser.setTitle("Select the CSV file you want to work with");
		File file = chooser.showOpenDialog(new Stage(StageStyle.UTILITY));

		return file;

	}

	/**
	 * @return the chooser
	 */
	private File getFile() {
		return file;
	}

	/**
	 * @param chooser the chooser to set
	 */
	private void setFile(File file) {
		this.file = file;
	}

}
