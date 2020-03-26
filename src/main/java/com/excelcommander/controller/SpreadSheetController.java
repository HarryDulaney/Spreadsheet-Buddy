package com.excelcommander.controller;

import java.util.*;

import com.excelcommander.util.SpreadSheetUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.apache.poi.ss.formula.atp.WorkdayCalculator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.spreadsheet.*;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import javafx.stage.Stage;

/**
 * @author HGDIV
 */
@Controller
public class SpreadSheetController extends ParentController {

    public static String FRESH_SS_VIEW = "/fxml/FreshSSView.fxml";
    public static String SSCONTROLLER_TAG = "spreadsheet.controller";


    protected static Workbook openWorkbook;

    @FXML
    protected Tab startTab;

    @FXML
    StackPane stackPane;

    @FXML
    protected TabPane tabPane;


    private ApplicationContext ctx;


    @Override
    public <T> void init(Stage stage, HashMap<String, T> parameters) {
        super.init(stage, parameters);

        checkParams(parameters);


    }

    private <T> void checkParams(HashMap<String, T> parameters) {

        if (parameters != null) {
            openWorkbook = (Workbook) parameters.get(SSCONTROLLER_TAG);
            initWorkbook();
        }else{
            //Do default behavior
        }


    }

    /**
     * TODO: Complete implementing multiple sheets (only does single sheet now)
     */
    private void initWorkbook() {
//        List<ObservableList<ObservableList<SpreadsheetCell>>> workbookObservable = new ArrayList<>();
        ObservableList<ObservableList<SpreadsheetCell>> observableSheet = FXCollections.observableArrayList();
        int longestRow = 0;
        int longestColumn = 0;
        int numSheets = openWorkbook.getNumberOfSheets();

        for (int curSheet = 0; curSheet < numSheets; curSheet++) {
            Sheet sheet = openWorkbook.getSheetAt(curSheet);


            for (int curRow = 0; curRow < sheet.getLastRowNum(); curRow++) {
                Row row = sheet.getRow(curRow);
                if (longestColumn < curRow) {
                    longestColumn = curRow;
                }
                final ObservableList<SpreadsheetCell> observableRow = FXCollections.observableArrayList();

                short startCell = row.getFirstCellNum();
                short endCell = row.getLastCellNum();
                if (endCell > longestRow) {
                    longestRow = endCell;
                }

                for (short cellIndex = startCell; cellIndex < endCell; cellIndex++) {
                    Cell cell = row.getCell(cellIndex);
                    if (cell == null) {
                        continue;
                    }
                    Object value;
                    switch (cell.getCellType()) {
                        case NUMERIC:
                            value = cell.getNumericCellValue();
                            break;
                        case STRING:
                            value = cell.getStringCellValue().trim();
                            break;
                        case BOOLEAN:
                            value = cell.getBooleanCellValue();
                            break;
                        case FORMULA:
                            value = cell.getCellFormula();
                            break;
                        case ERROR:
                            value = cell.getErrorCellValue();
                            break;
                        default:
                            value = cell.getRichStringCellValue();
                            break;


                    }
                    observableRow.add(SpreadsheetCellType.STRING.createCell(curRow, cellIndex, 1, 1, String.valueOf(value)));


                }
                observableSheet.add(observableRow);


            }


        }

        GridBase grid = new GridBase(longestRow, longestColumn);
        grid.setRows(observableSheet);

        SpreadsheetView ssView = new SpreadsheetView(grid);
        ssView.setScaleShape(true);
        String name = openWorkbook.getSheetName(0);
        startTab.setText(name);
        startTab.setContent(ssView);

        tabPane.getTabs().add(startTab);


    }


    @Override
    protected void onClose() {
        // TODO Auto-generated method stub

    }

    @Autowired
    void setApplicationContext(ApplicationContext ctx) {
        this.ctx = ctx;
    }

}
