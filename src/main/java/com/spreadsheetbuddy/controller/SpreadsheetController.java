package com.spreadsheetbuddy.controller;

import com.spreadsheetbuddy.util.BackingListUtil;
import com.spreadsheetbuddy.util.SpreadsheetUtil;
import com.sun.deploy.uitoolkit.impl.fx.FXWindow;
import javafx.beans.property.ListProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
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

    public static Integer openSheets;
    private final FxWeaver fxWeaver;

    @FXML
    SpreadsheetView ssView;

    Grid ssGrid;
    Workbook workbook;
    /**
     * The List holding references to the currently displayed SpreadsheetView
     */
    private static ObservableList<ObservableList<SpreadsheetCell>> backingList;
    private final FxControllerAndView<ViewController, TabPane> tabPaneControlView;


    public SpreadsheetController(FxWeaver fxWeaver,
                                 @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") FxControllerAndView<ViewController, TabPane> tabPaneControlView) {
        this.tabPaneControlView = tabPaneControlView;
        this.fxWeaver = fxWeaver;

    }


    @FXML
    public void initialize() {
        ssView.addEventHandler(GRID_CHANGE_EVENT, event -> {

        });
        ssGrid = ssView.getGrid();
        backingList = SpreadsheetUtil.createAndGetStarterSheet(backingList);
        ssGrid.setRows(backingList);
        ssView.setGrid(ssGrid);

    }

}
