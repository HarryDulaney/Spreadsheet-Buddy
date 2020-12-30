package com.spreadsheetbuddy.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.controlsfx.control.spreadsheet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

/**
 * SpreadsheetUtility Methods can be defined here TODO: Define
 *
 * @author Harry Dulaney
 */
public class SsUtil {
    static Logger logger = LoggerFactory.getLogger(SsUtil.class);

    private final static int default_rows = 100;
    private final static int default_columns = 46;
    private final static int default_span = 1;
    private final static String BLANK_CELL = "   ";


    private static Map<CellAddress, XSSFComment> cellComments;

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
        logger.info("Getting cell value -> CellRef: " + cellRef.formatAsString());

        // get the text that appears in the cell by getting the cell value and applying any data formats (Date, 0.00, 1.23e9, $1.23, etc)
        return defaultFormatter.formatCellValue(cell);

    }

    /**
     * @return starter ObservableList<ObservableList<SpreadsheetCell>>
     * filled with blank SpreadsheetCells for default project startup.
     */
    public static ObservableList<ObservableList<SpreadsheetCell>> initSheet(ObservableList<ObservableList<SpreadsheetCell>> backingList) {
        backingList = FXCollections.observableArrayList();

        for (int r = 0; r < default_rows; r++) {
            ObservableList<SpreadsheetCell> observableRow = FXCollections.observableArrayList();
            for (int c = 0; c < default_columns; c++) {
                SpreadsheetCell cell = SpreadsheetCellType.STRING.createCell(r, c, default_span, default_span, BLANK_CELL);
                observableRow.add(cell);
            }
            backingList.add(observableRow);
        }
        return backingList;

    }
//
//    /**
//     * @param xssfSheet Sheet to map into ObservableList -> GridBase
//     * @param evaluator FormulaEvaluator from getCreationHelper()
//     * @return GridBase populated with the xssfSheet data (Including blank cells)
//     */
//    protected static GridBase mapSheetToGrid(XSSFSheet xssfSheet, FormulaEvaluator evaluator) {
//        ObservableList<ObservableList<SpreadsheetCell>> rows = FXCollections.observableArrayList();
//        GridBase grid = new GridBase(100, longestColIndex);
//        int start = xssfSheet.getFirstRowNum();
//        int end = xssfSheet.getLastRowNum();
////        cellComments = xssfSheet.getCellComments();
//        for (int rowIdx = start; rowIdx < end; rowIdx++) {
//            ObservableList<SpreadsheetCell> rowList = FXCollections.observableArrayList();
//            XSSFRow row = xssfSheet.getRow(rowIdx);
//            DataFormatter formatter = new DataFormatter();
//            for (int col = 0; col < longestColIndex; col++) {
//                if (Objects.nonNull(row)) {
//                    XSSFCell cell = row.getCell(col);
//                    String value = formatter.formatCellValue(cell, evaluator);
//                    rowList.add(SpreadsheetCellType.STRING.createCell(rowIdx, col, 1, 1, value));
//                } else {
//                    rowList.add(SpreadsheetCellType.STRING.createCell(rowIdx, col, 1, 1, BLANK_CELL));
//
//                }
//            }
//            rows.add(rowList);
//        }
//        grid.setRows(rows);
//        return grid;
//
//    }

    protected static GridBase mapSheetToGrid(XSSFSheet xssfSheet, FormulaEvaluator evaluator) {
        ObservableList<ObservableList<SpreadsheetCell>> rows = FXCollections.observableArrayList();
        GridBase grid = new GridBase(1000, 1000);
//        int start = xssfSheet.getFirstRowNum();
        int end = xssfSheet.getLastRowNum();
//        cellComments = xssfSheet.getCellComments();
        for (int i = 0; i < grid.getRowCount(); i++) {
            ObservableList<SpreadsheetCell> rowList = FXCollections.observableArrayList();
            XSSFRow row = xssfSheet.getRow(i);
            DataFormatter formatter = new DataFormatter();
            for (int col = 0; col < grid.getColumnCount(); col++) {
                if (Objects.nonNull(row)) {
                    XSSFCell cell = row.getCell(col);
                    String value = formatter.formatCellValue(cell, evaluator);
                    rowList.add(SpreadsheetCellType.STRING.createCell(i, col, 1, 1, value));
                } else {
                    rowList.add(SpreadsheetCellType.STRING.createCell(i, col, 1, 1, BLANK_CELL));
                }
            }
            rows.add(rowList);
        }
        grid.setRows(rows);
        return grid;
    }

    public static ContextMenu createContextMenu(ContextMenu contextMenu) {
        contextMenu.getItems().addAll(new MenuItem("Format Cell"), new MenuItem("Insert"), new MenuItem("Style Cell"));
        return contextMenu;
    }

    public static Map<CellAddress, XSSFComment> getCellComments() {
        return cellComments;
    }

}