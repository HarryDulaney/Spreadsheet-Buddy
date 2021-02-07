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

import java.util.*;

/**
 * SpreadsheetUtility Methods can be defined here TODO: Define
 *
 * @author Harry Dulaney
 */
public class SsUtil {
    static Logger logger = LoggerFactory.getLogger(SsUtil.class);

    private final static int DEFAULT_ROWS = 100;
    private final static int DEFAULT_COLUMNS = 100;
    private final static int DEFAULT_SPAN = 1;
    private final static String BLANK_CELL = "   ";
    private static DataFormatter dataFormatter;


    private static Map<CellAddress, XSSFComment> cellComments;
    protected static List<Map<CellAddress, XSSFComment>> commentsMap;

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

        for (int r = 0; r < DEFAULT_ROWS; r++) {
            ObservableList<SpreadsheetCell> observableRow = FXCollections.observableArrayList();
            for (int c = 0; c < DEFAULT_COLUMNS; c++) {
                SpreadsheetCell cell = SpreadsheetCellType.STRING.createCell(r, c, DEFAULT_SPAN, DEFAULT_SPAN, BLANK_CELL);
                observableRow.add(cell);
            }
            backingList.add(observableRow);
        }
        return backingList;

    }

    /**
     * Maps a .xlsx spreadsheet to a GridBase object using Apache POI.
     *
     * @param xssfSheet Sheet to map into ObservableList -> GridBase
     * @param evaluator FormulaEvaluator from getCreationHelper()
     * @return GridBase populated with the xssfSheet data (Including blank cells)
     */
    protected static GridBase mapSheetToGrid(XSSFSheet xssfSheet, FormulaEvaluator evaluator) {
        commentsMap = new ArrayList<>();
        int rowCount = 0, columnCount = 0;
        dataFormatter = new DataFormatter();
        cellComments = xssfSheet.getCellComments();
        commentsMap.add(cellComments);

        ObservableList<ObservableList<SpreadsheetCell>> rows = FXCollections.observableArrayList();
        for (int i = 0; i < xssfSheet.getLastRowNum() || i < DEFAULT_ROWS; i++) {
            rowCount++;
            ObservableList<SpreadsheetCell> rowList = FXCollections.observableArrayList();
            XSSFRow row = xssfSheet.getRow(i);
            if (Objects.nonNull(row)) {
                for (int col = 0; col < row.getLastCellNum() || col < DEFAULT_COLUMNS; col++) {
                    columnCount++;
                    XSSFCell cell = row.getCell(col);
                    String value = dataFormatter.formatCellValue(cell, evaluator);
                    rowList.add(SpreadsheetCellType.STRING.createCell(i, col, 1, 1, value));
                }
            } else {
                for (int col = 0; col < DEFAULT_COLUMNS; col++) {
                    columnCount++;
                    rowList.add(SpreadsheetCellType.STRING.createCell(i, col, 1, 1, BLANK_CELL));
                }
            }
            rows.add(rowList);
        }
        GridBase gridBase = new GridBase(rowCount, columnCount);
        gridBase.setRows(rows);
        return gridBase;
    }

    public static ContextMenu createContextMenu(ContextMenu contextMenu) {
        contextMenu.getItems().addAll(new MenuItem("Format Cell"), new MenuItem("Insert"), new MenuItem("Style Cell"));
        return contextMenu;
    }


    public static List<Map<CellAddress, XSSFComment>> getCellComments() {
        return commentsMap;
    }

}