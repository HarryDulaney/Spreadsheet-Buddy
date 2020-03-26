package com.excelcommander.util;

import javafx.scene.control.Alert;
import org.apache.metamodel.util.FileResource;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.spreadsheet.GridBase;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;
import org.controlsfx.control.spreadsheet.SpreadsheetView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.jdbc.Work;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error during writing your new file");
            alert.show();
            ex.printStackTrace();

        }


    }

    public static Workbook loadFromFile(File file) throws IOException, InvalidFormatException {

        return new XSSFWorkbook(file);

    }

    /**
     *
     * @param wb the Workbook to map
     * @return returns a triple nested HashMap which holds the content of the Workbook
     *         and a reference to its position in the Sheet, Row, or Cell=(Column)
     */
    public static Map<String,Map<String,Map<String,Object>>> mapWorkbook(Workbook wb) {

        Map<String, Map<String, Map<String,Object>>> sheetMap = new HashMap<>();

        for (int sheetNum = 0; sheetNum < wb.getNumberOfSheets(); sheetNum++) {

            Sheet sheet = wb.getSheetAt(sheetNum);
            String sheetKey =sheetNum + "";

            int lastRow = sheet.getLastRowNum();
            int firstRow = sheet.getFirstRowNum();

            Map<String,Map<String,Object>> rowMap = new HashMap<>();

            for (int r = 0; r < lastRow; r++) {

                Row row = sheet.getRow(r);
                String rowKey = r + "";

                Map<String, Object> cellMap = new HashMap<>();
                Iterator<Cell> cellIterator = row.cellIterator();
                int cellIndex = 0;
                while (cellIterator.hasNext()) {
                    String cellKey = cellIndex + "";
                    Cell cell = cellIterator.next();

                    switch (cell.getCellType()) {
                        case NUMERIC:
                            cellMap.put(cellKey, cell.getNumericCellValue());
                            break;
                        case STRING:
                            cellMap.put(cellKey, cell.getStringCellValue().trim());
                            break;
                        case BOOLEAN:
                            cellMap.put(cellKey, cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            cellMap.put(cellKey, cell.getCellFormula());
                            break;
                        case ERROR:
                            cellMap.put(cellKey, cell.getErrorCellValue());
                            break;
                        default:
                            cellMap.put(cellKey, cell.getRichStringCellValue());
                            break;

                    }
                }
                rowMap.put(rowKey,cellMap);

            }

            sheetMap.put(sheetKey, rowMap);
        }
        return sheetMap;
    }


    public static SpreadsheetView createSSView() {

        int rowCount = 40; // Default row count

        int columnCount = 40; // Default column count

        GridBase grid = new GridBase(rowCount, columnCount);

        ObservableList<ObservableList<SpreadsheetCell>> rows = FXCollections.observableArrayList();
        {
            for (int row = 0; row < grid.getRowCount(); ++row) {
                final ObservableList<SpreadsheetCell> list = FXCollections.observableArrayList();
                for (int column = 0; column < grid.getColumnCount(); ++column) {
                    list.add(SpreadsheetCellType.STRING.createCell(row, column, 1, 1, "value"));
                }
                rows.add(list);
            }
            grid.setRows(rows);

            return new SpreadsheetView(grid);
        }
    }
}
