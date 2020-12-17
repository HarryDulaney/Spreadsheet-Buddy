package com.spreadsheetbuddy.controller;

import com.spreadsheetbuddy.util.BackingListUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import net.rgielen.fxweaver.core.FxmlView;
import org.apache.poi.ss.usermodel.Workbook;
import org.controlsfx.control.spreadsheet.Grid;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetView;
import org.springframework.stereotype.Component;

import static org.controlsfx.control.spreadsheet.GridChange.GRID_CHANGE_EVENT;

/**
 * <p>
 * MenuController controls and handles events for the File MenuBar
 * </p>
 */
@Component
@FxmlView("/fxml/ssheet.fxml")
public class SpreadsheetController {

    @FXML
    SpreadsheetView ssView;

    Grid ssGrid;
    Workbook workbook;
    /**
     * The List holding references to the currently displayed SpreadsheetView
     */
    private static ObservableList<ObservableList<SpreadsheetCell>> backingList;


    @FXML
    public void initialize() {
        ssView.addEventHandler(GRID_CHANGE_EVENT,event -> {

        });
        ssGrid = ssView.getGrid();
        backingList = BackingListUtil.createAndGetStarterSheet(backingList);
        ssGrid.setRows(backingList);
        ssView.setGrid(ssGrid);

    }

}
