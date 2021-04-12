package com.spreadsheetbuddy.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.*;
import org.controlsfx.control.spreadsheet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * SpreadsheetUtility Methods can be defined here
 * TODO: Define a SpreadSheetView + Apache POI XSSFSheet Object to operate on here.
 *
 * @author Harry Dulaney
 */
public class SsUtil {
    static Logger logger = LoggerFactory.getLogger(SsUtil.class);

    private final static int DEFAULT_COLUMN_SPAN = 1;
    private final static int DEFAULT_ROW_SPAN = 1;
    private final static String BLANK_CELL = "\t";
    private final static int MIN_ROWS = 250;
    private final static int MIN_ROW_LENGTH = 26;


    protected static List<Map<CellAddress, XSSFComment>> COMMENT_MAP;


    static Integer evaluateColumns(XSSFSheet sheet, int lastRowNum) {
        int longestRow = MIN_ROW_LENGTH;

        for (int i = 0; i < lastRowNum; i++) {
            Row row = sheet.getRow(i);
            if (Objects.isNull(row)) continue;
            int currentRowLength = row.getLastCellNum();
            longestRow = Math.max(longestRow, currentRowLength);
            logger.info("Reading Row #" + row.getRowNum() + ", length is: " + currentRowLength + ". Longest Row is:" +
                    " " + longestRow);

        }
        return longestRow;
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

        for (int r = 0; r < MIN_ROWS; r++) {
            ObservableList<SpreadsheetCell> observableRow = FXCollections.observableArrayList();
            for (int c = 0; c < MIN_ROW_LENGTH; c++) {
                SpreadsheetCell cell = SpreadsheetCellType.STRING.createCell(r, c, DEFAULT_COLUMN_SPAN, DEFAULT_COLUMN_SPAN, BLANK_CELL);
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

        int lastRow = Math.max(xssfSheet.getLastRowNum(), MIN_ROWS);
        logger.info("Start reading sheet, last row is: " + lastRow);

        int gridColumnValue = evaluateColumns(xssfSheet, lastRow);
        GridBase gridBase = new GridBase(lastRow, gridColumnValue);

        DataFormatter dataFormatter = new DataFormatter();
        ObservableList<ObservableList<SpreadsheetCell>> rows = FXCollections.observableArrayList();
        for (int rowIdx = 0; rowIdx < lastRow; rowIdx++) {
            ObservableList<SpreadsheetCell> observableRow = FXCollections.observableArrayList();
            XSSFRow row = xssfSheet.getRow(rowIdx);
            if (row == null) {
                int i = 0;
                while (i < gridColumnValue) {
                    observableRow.add(SpreadsheetCellType.STRING.createCell(rowIdx, i++, DEFAULT_ROW_SPAN, DEFAULT_COLUMN_SPAN,
                            BLANK_CELL));
                }
            } else {
                for (int colIdx = 0; colIdx < gridColumnValue; colIdx++) {
                    XSSFCell cell = row.getCell(colIdx, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    CellType cellType = cell.getCellType();
                    String value = "";
                    switch (cellType) {
                        case BLANK:
                        case _NONE:
                            value = BLANK_CELL;
                            break;
                        case NUMERIC:
                        case STRING:
                        case ERROR:
                        case BOOLEAN:
                        case FORMULA:
                            value = dataFormatter.formatCellValue(cell, evaluator);
                    }

//                    if (cell == null) {
//                        observableRow.add(SpreadsheetCellType.STRING.createCell(rowIdx, colIdx, DEFAULT_ROW_SPAN, DEFAULT_COLUMN_SPAN,
//                                BLANK_CELL));

//                    } else {
                    observableRow.add(SpreadsheetCellType.STRING.createCell(rowIdx, colIdx, DEFAULT_ROW_SPAN, DEFAULT_COLUMN_SPAN,
                            value));
//                    }
                }
            }
            rows.add(observableRow);
        }
        gridBase.setRows(rows);
        return gridBase;
    }

    public static ContextMenu createContextMenu(ContextMenu contextMenu) {
        contextMenu.getItems().addAll(new MenuItem("Format Cell"), new MenuItem("Insert"), new MenuItem("Style Cell"));
        return contextMenu;
    }


    public static List<Map<CellAddress, XSSFComment>> getCellComments() {
        return COMMENT_MAP;
    }

}