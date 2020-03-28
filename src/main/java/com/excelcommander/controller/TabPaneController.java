package com.excelcommander.controller;

import java.time.LocalDate;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.apache.poi.ss.usermodel.*;
import org.controlsfx.control.spreadsheet.*;
import org.springframework.stereotype.Controller;

import javafx.stage.Stage;

/**
 * @author HGDIV
 */
@Controller
public class TabPaneController extends ParentController {

    public static String TABPANE_FXML = "/fxml/TabPaneView.fxml";
    public static String SSController_KEY = "spreadsheet.controller";

    @FXML
    private SpreadsheetView ssView;

    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tab;

    private Workbook workbook;


    @Override
    public <T> void init(Stage stage, HashMap<String, T> parameters) {
        super.init(stage, parameters);

        handleParams(parameters);

    }

    private <T> void handleParams(HashMap<String, T> parameters) {
        if (parameters != null) {
            workbook = (Workbook) parameters.get(SSController_KEY);
            loadWorkbook();
        }

    }

    /**
     * TODO: Complete implementing multiple sheets (only does single sheet now)
     */
    protected void loadWorkbook() {

        //Do default behavior
//        List<ObservableList<ObservableList<SpreadsheetCell>>> workbookObservable = new ArrayList<>();// multiple sheets
        ObservableList<ObservableList<SpreadsheetCell>> observableSheet = FXCollections.observableArrayList();

        int numSheets = workbook.getNumberOfSheets();

        for (int curSheet = 0; curSheet < numSheets; curSheet++) {
            Sheet sheet = workbook.getSheetAt(curSheet);


            for (int curRow = 0; curRow < sheet.getLastRowNum(); curRow++) {
                Row row = sheet.getRow(curRow);
                final ObservableList<SpreadsheetCell> observableRow = FXCollections.observableArrayList();

                short startCell = row.getFirstCellNum();
                short endCell = row.getLastCellNum();

                for (short cellIndex = startCell; cellIndex < endCell; cellIndex++) {

                    Cell cell = row.getCell(cellIndex);
                    String temp = BuiltinFormats.getBuiltinFormat(cell.getCellStyle().getDataFormat());
                    if (temp.equals(BuiltinFormats.getBuiltinFormat(0xe)) ||
                            temp.equals(BuiltinFormats.getBuiltinFormat(0xf)) ||
                            temp.equals(BuiltinFormats.getBuiltinFormat(0x10)) ||
                            temp.equals(BuiltinFormats.getBuiltinFormat(0x11))) {

                        observableRow.add(SpreadsheetCellType.DateType.DATE.createCell(curRow, cellIndex, 1, 1, LocalDate.from(cell.getLocalDateTimeCellValue())));
                    } else {

                        switch (cell.getCellType()) {
                            case STRING:
                                observableRow.add(SpreadsheetCellType.STRING.createCell(curRow, cellIndex, 1, 1, cell.getStringCellValue().trim()));
                                break;
                            case NUMERIC:
                                observableRow.add(SpreadsheetCellType.DOUBLE.createCell(curRow, cellIndex, 1, 1, cell.getNumericCellValue()));
                                break;
                            case FORMULA:
                                observableRow.add(SpreadsheetCellType.STRING.createCell(curRow, cellIndex, 1, 1, cell.getCellFormula()));
                                break;
                            case ERROR:
                                observableRow.add(SpreadsheetCellType.STRING.createCell(curRow, cellIndex, 1, 1, String.valueOf(cell.getErrorCellValue())));
                                break;
                            default:
                                observableRow.add(SpreadsheetCellType.STRING.createCell(curRow, cellIndex, 1, 1, String.valueOf(cell.getRichStringCellValue())));
                                break;

                        }

                    }
                }
                observableSheet.add(observableRow);
            }
        }

        GridBase grid = new GridBase(100, 30);
        grid.setRows(observableSheet);

        ssView = new SpreadsheetView(grid);
        tab.setText(workbook.getSheetName(0));

        tabPane.getTabs().add(tab);
    }

    public SpreadsheetView getSsView() {
        return ssView;
    }

    public  void setSsView(SpreadsheetView ssView) {
        this.ssView = ssView;
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }


    @Override
    protected void onClose() {

    }

}
