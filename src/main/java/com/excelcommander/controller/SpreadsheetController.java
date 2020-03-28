package com.excelcommander.controller;

import java.time.LocalDate;
import java.util.*;

import com.excelcommander.model.WorkbookCE;
import com.excelcommander.util.SpreadSheetUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.controlsfx.control.spreadsheet.*;
import org.springframework.stereotype.Controller;

import javafx.stage.Stage;

/**
 * @author HGDIV
 */

public class SpreadsheetController extends ParentController {

    public static String SSVIEW_FXML = "/fxml/SpreadSheetView.fxml";
    public static String WORKBOOK = "workbook";

    private WorkbookCE workbookCE;
    private Workbook workbook;

    @FXML
    private SpreadsheetView ssView;


    @Override
    public <T> void init(Stage stage, HashMap<String, T> parameters) {
        super.init(stage, parameters);

        handleParams(parameters);

    }

    private <T> void handleParams(HashMap<String, T> parameters) {
        if (parameters != null) {
            workbook = (Workbook) parameters.get(WORKBOOK);
            loadWorkbook();
        }

    }

    /**
     * TODO: Complete implementing multiple sheets (only does single sheet now)
     */
    private void loadWorkbook() {

        Sheet sheet = workbook.getSheetAt(0);
        GridBase grid = new GridBase(100, 30);

        ObservableList<ObservableList<SpreadsheetCell>> observableSheet = FXCollections.observableArrayList();



    }


    public SpreadsheetView getSsView() {
        return ssView;
    }

    public void setSsView(SpreadsheetView ssView) {
        this.ssView = ssView;
    }

    @Override
    protected void onClose() {

    }

}
