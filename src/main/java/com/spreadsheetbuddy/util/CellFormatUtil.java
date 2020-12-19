package com.spreadsheetbuddy.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.List;

/**
 * Cell formatting utility class.
 */
public class CellFormatUtil {

    /**
     * Gets supported cell format types for populating the menu's spinner.
     *
     * @return the cell format types
     */
    public static ObservableList<String> getCellTypes() {
        List<String> list = Arrays.asList("General", "Number", "Formula", "Date", "List", "Image", "Dollar", "Image");
        return FXCollections.observableArrayList(list);

    }
}
