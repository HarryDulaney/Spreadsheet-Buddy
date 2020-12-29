package com.spreadsheetbuddy.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
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

    /**
     * @param xssfSheet Sheet to map into ObservableList -> GridBase
     * @param evaluator FormulaEvaluator from getCreationHelper()
     * @return GridBase populated with the xssfSheet data (Including blank cells)
     */
    protected static GridBase mapSheetToGrid(XSSFSheet xssfSheet, FormulaEvaluator evaluator) {
        int[] size = sheetSize(xssfSheet);
        GridBase grid = new GridBase(size[0], size[1]);
        ObservableList<ObservableList<SpreadsheetCell>> rows = FXCollections.observableArrayList();
        Row poiRow;
        Cell cell;
        String value;
        for (int row = 0; row < grid.getRowCount(); ++row) {
            final ObservableList<SpreadsheetCell> list = FXCollections.observableArrayList();
            poiRow = xssfSheet.getRow(row);
            DataFormatter formatter = new DataFormatter();
            for (int column = 0; column < grid.getColumnCount(); ++column) {
                cell = poiRow.getCell(column);
                value = formatter.formatCellValue(cell, evaluator);
                list.add(SpreadsheetCellType.STRING.createCell(row, column, 1, 1, value));
            }
            rows.add(list);
        }
        grid.setRows(rows);
        return grid;

    }

    /**
     * @param sheet to evaluate
     * @return array with the sheets highest row number and highest column number
     */
    protected static int[] sheetSize(XSSFSheet sheet) {
        int rows = sheet.getLastRowNum();
        int columns = Integer.MIN_VALUE;

        for (int i = 0; i < rows; i++) {
            XSSFRow row = sheet.getRow(i);
            int testColumn = row.getLastCellNum();
            columns = Math.max(columns, testColumn);

        }

        return new int[]{rows, columns};
    }

    public static ContextMenu createContextMenu(ContextMenu contextMenu) {
        contextMenu.getItems().addAll(new MenuItem("Format Cell"), new MenuItem("Insert"), new MenuItem("Style Cell"));
        return contextMenu;
    }
}