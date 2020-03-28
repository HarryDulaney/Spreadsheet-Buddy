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
import org.controlsfx.control.spreadsheet.*;
import org.springframework.stereotype.Controller;

import javafx.stage.Stage;

/**
 * @author HGDIV
 */
@Controller
public class TabPaneController extends ParentController {

    public static String TABPANE_FXML = "/fxml/TabPaneView.fxml";
    public static String WORKBOOK = "workbook";

    private WorkbookCE workbookCE;
    private Workbook workbook;

    @FXML
    private SpreadsheetView ssView;

    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tab;


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
        workbookCE = SpreadSheetUtils.mapSheet(workbook, 0);

        int numRows = workbookCE.getMaxRow();
        int numColumns = workbookCE.getMaxColumn();
        GridBase grid = new GridBase(numRows, numColumns);

        ObservableList<ObservableList<SpreadsheetCell>> observableSheet = FXCollections.observableArrayList();
        List<List<Cell>> sheetAsList = workbookCE.getSheetAsList();

        for (int curRow = 0; curRow < sheetAsList.size(); curRow++) {
            final ObservableList<SpreadsheetCell> observableRow = FXCollections.observableArrayList();
            List<Cell> rowAsList = sheetAsList.remove(curRow);

            for (Cell cell : rowAsList) {
                int rowIndex = cell.getRowIndex();
                int columnIndex = cell.getColumnIndex();
                String temp = BuiltinFormats.getBuiltinFormat(cell.getCellStyle().getDataFormat());
                if (temp.equals(BuiltinFormats.getBuiltinFormat(0xe)) ||
                        temp.equals(BuiltinFormats.getBuiltinFormat(0xf)) ||
                        temp.equals(BuiltinFormats.getBuiltinFormat(0x10)) ||
                        temp.equals(BuiltinFormats.getBuiltinFormat(0x11))) {

                    observableRow.add(SpreadsheetCellType.DATE.createCell(cell.getRowIndex(), cell.getColumnIndex(), 1, 1,
                            LocalDate.from(cell.getLocalDateTimeCellValue())));
                } else {
                    switch (cell.getCellType()) {
                        case STRING:
                            observableRow.add(SpreadsheetCellType.STRING.createCell(rowIndex, columnIndex, 1, 1, cell.getStringCellValue().trim()));
                            break;
                        case NUMERIC:
                            observableRow.add(SpreadsheetCellType.DOUBLE.createCell(rowIndex, columnIndex, 1, 1, cell.getNumericCellValue()));
                            break;
                        case FORMULA:
                            observableRow.add(SpreadsheetCellType.STRING.createCell(rowIndex, columnIndex, 1, 1, cell.getCellFormula()));
                            break;
                        case ERROR:
                            observableRow.add(SpreadsheetCellType.STRING.createCell(rowIndex, columnIndex, 1, 1, String.valueOf(cell.getErrorCellValue())));
                            break;
                        default:
                            observableRow.add(SpreadsheetCellType.STRING.createCell(rowIndex, columnIndex, 1, 1, String.valueOf(cell.getRichStringCellValue())));
                            break;

                    }

                }
            }
            observableSheet.add(observableRow);
        }
        grid.setRows(observableSheet);
        ssView = SpreadSheetUtils.renderSSView(grid);
        tab.setText(workbookCE.getWorkbook().getSheetName(0));
        tabPane.getTabs().clear();
        tabPane.getTabs().set(0,tab);

    }


    public SpreadsheetView getSsView() {
        return ssView;
    }

    public void setSsView(SpreadsheetView ssView) {
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
