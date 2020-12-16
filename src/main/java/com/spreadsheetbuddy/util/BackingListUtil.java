package com.spreadsheetbuddy.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;

import java.util.ArrayList;
import java.util.List;

public class BackingListUtil {
    private final static int default_rows = 100;
    private final static int default_columns = 46;
    private final static int default_span = 1;
    private final static String blank_cell = " ";
    protected static List<ObservableList<SpreadsheetCell>> backingList;

    /**
     *
     * @return starter ObservableList<ObservableList<SpreadsheetCell>>
     * filled with blank SpreadsheetCells for default project startup.
     */
    public static ObservableList<ObservableList<SpreadsheetCell>> getBlankSheet() {

        backingList = new ArrayList<>();

        for (int r = 0; r < default_rows; r++) {
            List<SpreadsheetCell> list = new ArrayList<>();
            for (int c = 0; c < default_columns; c++) {
                SpreadsheetCell cell = SpreadsheetCellType.STRING.createCell(r, c, default_span, default_span, blank_cell);
                list.add(cell);
            }
            ObservableList<SpreadsheetCell> observableRow = FXCollections.observableList(list);
            backingList.add(observableRow);

        }
        return FXCollections.observableList(backingList);

    }


}
