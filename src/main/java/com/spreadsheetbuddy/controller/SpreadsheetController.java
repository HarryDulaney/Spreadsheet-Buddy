package com.spreadsheetbuddy.controller;

import com.spreadsheetbuddy.util.SsUtil;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.controlsfx.control.spreadsheet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 * MenuController controls and handles events for the File MenuBar
 * </p>
 */
@Component
@FxmlView("/fxml/ssheet.fxml")
public class SpreadsheetController implements EventHandler<GridChange> {
    private final Logger logger = LoggerFactory.getLogger(SpreadsheetController.class);

    protected int sheetNumber;
    protected XSSFSheet poiSheet;

    public static boolean turnOffDefaultInit;
    private final FxWeaver fxWeaver;
    private ObservableList<ObservableList<SpreadsheetCell>> backingList;
    Grid ssGrid;

    @Override
    public void handle(GridChange event) {
    }

    @FXML
    protected SpreadsheetView ssView;


    public SpreadsheetController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }


    @FXML
    public void initialize() {
        /* Default initialize spreadsheetView */
        if (!turnOffDefaultInit) {
            ssGrid = ssView.getGrid();
            backingList = SsUtil.initSheet(backingList);
            ssGrid.setRows(backingList);
            ssView.setGrid(ssGrid);
            ssView.addEventHandler(GridChange.GRID_CHANGE_EVENT,this);
        }
        ContextMenu contextMenu = SsUtil.createContextMenu(ssView.getSpreadsheetViewContextMenu());
        ssView.setContextMenu(contextMenu);
        ssView.setEditable(true);

    }


    protected SpreadsheetView setSsGrid(Grid ssGrid) {
        this.ssGrid = ssGrid;
        ssView.setGrid(ssGrid);
        ssView.setEditable(true);
        return ssView;

    }

    public ObservableList<ObservableList<SpreadsheetCell>> getBackingList() {
        return backingList;
    }

    public Grid getSsGrid() {
        return ssGrid;
    }

    public SpreadsheetView getSsView() {
        return ssView;
    }

    public void setSsView(SpreadsheetView ssView) {
        this.ssView = ssView;
    }

    public void setBackingList(ObservableList<ObservableList<SpreadsheetCell>> backingList) {
        this.backingList = backingList;
    }

    void close() {

    }
}