package com.spreadsheetbuddy.controller;

import com.spreadsheetbuddy.util.SsUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.spreadsheet.Grid;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 * MenuController controls and handles events for the File MenuBar
 * </p>
 */
@Component
@FxmlView("/fxml/ssheet.fxml")
public class SpreadsheetController {
    private final Logger logger = LoggerFactory.getLogger(SpreadsheetController.class);

    private final FxWeaver fxWeaver;
    protected int sheetNumber;
    private static ObservableList<ObservableList<SpreadsheetCell>> backingList;


    @FXML
    protected SpreadsheetView ssView;

    Grid ssGrid;

    /**
     * The List holding references to the currently displayed SpreadsheetView
     */


    public SpreadsheetController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }


    @FXML
    public void initialize() {

        /* Initialize blank Untitled spreadsheet tab */
        ssGrid = ssView.getGrid();
        backingList = SsUtil.initSheet(backingList);
        ssGrid.setRows(backingList);
        ssView.setGrid(ssGrid);
        ssView.setEditable(true);
        ContextMenu contextMenu = SsUtil.createContextMenu(ssView.getSpreadsheetViewContextMenu());
        ssView.setContextMenu(contextMenu);
    }

}
