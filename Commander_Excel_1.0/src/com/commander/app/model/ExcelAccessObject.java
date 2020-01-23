package com.commander.app.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.commander.app.PHelper;

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

	private static Logger logger;

	/**
	 * @param excelfile - the workbook that the user wants top save as a File
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void saveWorkbook(Workbook workbook) {

		File filePath = PHelper.showFilePrompt("Save your new workbook: ", ".xlsx", true);

		if (filePath != null) {

			FileOutputStream fOs = null;

			try {
				fOs = new FileOutputStream(filePath);
				workbook.write(fOs);

				fOs.close();
				workbook.close();

			} catch (IOException e) {
				logger.log(Level.WARNING, "IO Exception at ExcelAccesObject.saveWorkbook");
				e.printStackTrace();
			}

		}
	}

	/**
	 * Gets a column as an ArrayList, identified by its header.
	 * 
	 * @param file      - System file containing location path for the worksheet
	 * @param header    - The header or title of the column to retrieve
	 * @param sheetname
	 * @return ArrayList - List containing strings of each cell in the column under
	 *         the header
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public static ArrayList<String> getColumn(File file, String header, String sheetname) throws Exception {

		ArrayList<String> colList = new ArrayList<>();

		wB = new XSSFWorkbook(file);
		sheet = wB.getSheet(sheetname);

		if (sheet != null) {

			Row headerRow = sheet.getRow(0);// The first row which contains the headers to search

			int columnIndex = 0;

			for (Cell cell : headerRow) {
				System.out.println(cell.getRichStringCellValue().getString());
				if (cell.getRichStringCellValue().getString().equalsIgnoreCase(header)) {
					columnIndex = cell.getColumnIndex();
				}
			}

			for (Row row : sheet) {
				if (row == headerRow)
					continue;
				for (Cell cell : row) {
					System.out.println(cell.getRichStringCellValue().getString());
					if (cell.getColumnIndex() == columnIndex) {
						colList.add(cell.getRichStringCellValue().getString());

					}

				}
			}

		}

		return colList;

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
