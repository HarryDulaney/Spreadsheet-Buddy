package com.spreadsheetbuddy.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.controlsfx.control.spreadsheet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

/**
 * SpreadsheetUtility Methods can be defined here TODO: Define
 *
 * @author Harry Dulaney
 */
public class SsUtil {
    static Logger logger = LoggerFactory.getLogger(SsUtil.class);
    //    private SpreadsheetCellEditor ssEditor;
//    private SpreadsheetColumn ssColumn;
//
//
    private final static int default_rows = 100;
    private final static int default_columns = 46;
    private final static int default_span = 1;
    private final static String blank_cell = "   ";


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
                SpreadsheetCell cell = SpreadsheetCellType.STRING.createCell(r, c, default_span, default_span, blank_cell);
                observableRow.add(cell);
            }
            backingList.add(observableRow);
        }
        return backingList;

    }

    public static ContextMenu createContextMenu(ContextMenu contextMenu) {
        contextMenu.getItems().addAll(new MenuItem("Format Cell"), new MenuItem("Insert"), new MenuItem("Style Cell"));
        return contextMenu;
    }
}