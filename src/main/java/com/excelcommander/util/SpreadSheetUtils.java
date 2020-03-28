package com.excelcommander.util;

import com.excelcommander.model.WorkbookCE;
import javafx.scene.control.Alert;
import org.apache.metamodel.util.FileResource;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.spreadsheet.GridBase;
import org.controlsfx.control.spreadsheet.SpreadsheetView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author HG Dulaney IV
 */
public class SpreadSheetUtils {




    public static void createBlankWorkbook(FileResource fr) {
        Workbook wb = new XSSFWorkbook();

        try (FileOutputStream fOs = new FileOutputStream(fr.getFile())) {
            wb.createSheet("New Sheet");
            wb.write(fOs);

            DialogHelper.showInfoAlert("Successful Operation", "You created a new workbook with one spreadsheet", "New Sheet", false);

        } catch (Exception ex) {
            DialogHelper.showSimpleAlert("Error during writing your new file", Alert.AlertType.ERROR);
            ex.printStackTrace();

        }


    }

    public static Workbook loadFromFile(File file) throws IOException, InvalidFormatException {

        return new XSSFWorkbook(file);

    }

    /**
     * @param workbook poi workbook
     * @param sheetNum the current sheet to be mapped
     * @return a the sheet mapped to a nested List format
     */
    public static WorkbookCE mapSheet(Workbook workbook, int sheetNum) {
      WorkbookCE workbookCE = new WorkbookCE(workbook);
        List<List<Cell>> sheetList = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(sheetNum);

        int numRows = sheet.getLastRowNum();

        workbookCE.setMaxRow(100);
        workbookCE.setMaxColumn(30);

        for (int r = 0; r < 100; r++) {
            Row row = sheet.getRow(r);
            List<Cell> rowList = new ArrayList<>();
            Iterator<Cell> cellIterator = row.cellIterator();

            int count = 0;
            for (int c = 0; c < 30; c++) {
                rowList.add(row.getCell(c));
            }
            sheetList.add(rowList);

        }
        workbookCE.setSheetAsList(sheetList);

        return workbookCE;

    }

    public static SpreadsheetView renderSSView(GridBase gridBase) {
        return new SpreadsheetView(gridBase);
    }

    /**
     * @param  The Workbook to map to a list
     * @return returns a triple nested List of Cells which hold the content of the Workbook
     */
/*    public static List<List<List<Cell>>> mapWorkbook(Workbook wb) {
         workbookCE = new WorkbookCE(wb);

        int numberOfSheets = wb.getNumberOfSheets();
        List<List<List<Cell>>> workbookAsList = new ArrayList<>();

        for (int curSheet = 0; curSheet < numberOfSheets; curSheet++) {

            workbookAsList.add(mapSheet(wb, curSheet));
        }
        return workbookAsList;


    }*/


}
