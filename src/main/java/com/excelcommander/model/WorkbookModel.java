package com.excelcommander.model;

import javafx.scene.control.Tab;
import org.apache.poi.ss.usermodel.Workbook;
import org.controlsfx.control.spreadsheet.SpreadsheetView;


public class WorkbookModel {

    private Workbook workbook;
    private Tab tab;
    private SpreadsheetView spreadsheetView;

    public WorkbookModel(Workbook workbook, Tab tab, SpreadsheetView spreadsheetView) {
        this.tab = tab;
        this.spreadsheetView = spreadsheetView;
        this.workbook = workbook;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }

    public SpreadsheetView getSpreadsheetView() {
        return spreadsheetView;
    }

    public void setSpreadsheetView(SpreadsheetView spreadsheetView) {
        this.spreadsheetView = spreadsheetView;
    }

    public Tab getTab() {
        return tab;
    }

    public void setTab(Tab tab) {
        this.tab = tab;
    }
}
