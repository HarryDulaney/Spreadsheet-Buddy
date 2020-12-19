package com.spreadsheetbuddy.util;

import com.spreadsheetbuddy.model.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.spreadsheet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.apache.poi.ss.usermodel.CellType.*;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;

/**
 * SpreadsheetUtility Methods can be defined here TODO: Define
 */
public class SpreadsheetUtil {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //    private SpreadsheetCellEditor ssEditor;
//    private SpreadsheetColumn ssColumn;
//
//
    private final static int default_rows = 100;
    private final static int default_columns = 46;
    private final static int default_span = 1;
    private final static String blank_cell = "   ";

    public static void createBlankWorkbook(File file) {
        Workbook wb = new XSSFWorkbook();

        try (FileOutputStream fOs = new FileOutputStream(file)) {
            wb.createSheet("New Sheet");
            wb.write(fOs);
            wb.close();
            DialogHelper.showInfoAlert("Successful Operation", "You created a new workbook with one spreadsheet", "New Sheet", false);

        } catch (Exception ex) {
            DialogHelper.showSimpleAlert("Error during writing your new file", Alert.AlertType.ERROR);
            ex.printStackTrace();

        }


    }

    public static void saveWorkbook(Project project) throws IOException {
        Workbook wb = project.getWorkbook();

        try (OutputStream fileOut = new FileOutputStream(project.getCurrentWorkbookFile())) {
            wb.write(fileOut);
        }
        wb.close();

    }


    public static Workbook loadFromFile(File file) throws IOException, InvalidFormatException {

        return new XSSFWorkbook(file);

    }


    public static LinkedList<LinkedList<String>> getAllCellValues(Sheet sheet) {

        LinkedList<LinkedList<String>> sheetAsList = new LinkedList<>();

        for (Row row : sheet) {
            LinkedList<String> rowAsList = new LinkedList<>();
            for (Cell cell : row) {
                String cellValueText = getCellValue(cell, row);
                rowAsList.add(cellValueText);
            }
            sheetAsList.add(rowAsList);
        }
        return sheetAsList;
    }


    public static String getCellValue(Cell cell, Row row) {

        DataFormatter defaultFormatter = new DataFormatter();
        CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
        System.out.print(cellRef.formatAsString());
        System.out.print(" - ");
        // get the text that appears in the cell by getting the cell value and applying any data formats (Date, 0.00, 1.23e9, $1.23, etc)
        return defaultFormatter.formatCellValue(cell);

    }

    /**
     * @return starter ObservableList<ObservableList<SpreadsheetCell>>
     * filled with blank SpreadsheetCells for default project startup.
     */
    public static ObservableList<ObservableList<SpreadsheetCell>> createAndGetStarterSheet(ObservableList<ObservableList<SpreadsheetCell>> backingList) {
        backingList = null;
        backingList = FXCollections.observableArrayList();

        for (int r = 0; r < default_rows; r++) {
            ObservableList<SpreadsheetCell> observableRow = FXCollections.observableArrayList();
            for (int c = 0; c < default_columns; c++) {
                SpreadsheetCell cell = SpreadsheetCellType.STRING.createCell(r, c, default_span, default_span, blank_cell);
                observableRow.add(cell);
            }
            backingList.add(observableRow);
        }
        return backingList;

    }
}

//    /**
//     * @param workbook poi workbook
//     * @param sheetNum the current sheet to be mapped
//     * @return a the sheet mapped to a nested List format
//     * TODO: Customize for your needs
//     */
//    public static List<List<Cell>> mapSheetFromXlsx(Workbook workbook, int sheetNum) {
//        List<List<Cell>> sheetList = new LinkedList<>();
//        Sheet sheet = workbook.getSheetAt(sheetNum);
//
//        int numRows = sheet.getLastRowNum();
//
//        for (int r = 0; r < 100; r++) {
//            Row row = sheet.getRow(r);
//            List<Cell> rowList = new LinkedList<>();
//            Iterator<Cell> cellIterator = row.cellIterator();
//            while (cellIterator.hasNext()) {
//                Cell cell = cellIterator.next();
//                rowList.add(cell);
//            }
//            int count = 0;
//            for (int c = 0; c < 30; c++) {
//                rowList.add(row.getCell(c));
//            }
//            sheetList.add(rowList);
//
//        }
//
//        return sheetList;
//
//    }