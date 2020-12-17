package com.spreadsheetbuddy.util;

import com.spreadsheetbuddy.model.Project;
import javafx.scene.control.Alert;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.spreadsheet.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * SpreadsheetUtility Methods can be defined here TODO: Define
 */
public class SpreadsheetUtil {

//    private SpreadsheetCellEditor ssEditor;
//    private SpreadsheetColumn ssColumn;
//
//

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

    /**
     * @param workbook poi workbook
     * @param sheetNum the current sheet to be mapped
     * @return a the sheet mapped to a nested List format
     * TODO: Customize for your needs
     */
    public static List<List<Cell>> mapSheetFromXlsx(Workbook workbook, int sheetNum) {
        List<List<Cell>> sheetList = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(sheetNum);

        int numRows = sheet.getLastRowNum();

        for (int r = 0; r < 100; r++) {
            Row row = sheet.getRow(r);
            List<Cell> rowList = new ArrayList<>();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                rowList.add(cell);
            }
            int count = 0;
            for (int c = 0; c < 30; c++) {
                rowList.add(row.getCell(c));
            }
            sheetList.add(rowList);

        }

        return sheetList;

    }

}
