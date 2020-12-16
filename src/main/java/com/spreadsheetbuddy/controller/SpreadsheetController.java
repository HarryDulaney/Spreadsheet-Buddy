package com.spreadsheetbuddy.controller;

import com.spreadsheetbuddy.util.BackingListUtil;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import net.rgielen.fxweaver.core.FxmlView;
import org.controlsfx.control.spreadsheet.Grid;
import org.controlsfx.control.spreadsheet.GridChange;
import org.controlsfx.control.spreadsheet.SpreadsheetView;
import org.springframework.stereotype.Component;

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

    EventHandler<GridChange> ssChangeEvent;
    Grid ssGrid;


    @FXML
    public void initialize() {
        ssGrid = ssView.getGrid();
        ssGrid.setRows(BackingListUtil.getBlankSheet());
        ssView.setGrid(ssGrid);

    }

}
