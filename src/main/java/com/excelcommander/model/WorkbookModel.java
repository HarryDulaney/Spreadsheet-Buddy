package com.excelcommander.model;

import javafx.scene.control.Tab;
import org.apache.metamodel.util.FileResource;
import org.apache.poi.ss.usermodel.Workbook;
import org.controlsfx.control.spreadsheet.SpreadsheetView;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class WorkbookModel {
    @Transient
    private Workbook workbook; //Current active workbook
    @Transient
    private Tab tab;
    @Transient
    private SpreadsheetView spreadsheetView;

    private String workbookName;
    private FileResource fileResource;

    
    public WorkbookModel(){

    }

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

    public String getWorkbookName() {
        return workbookName;
    }

    public void setWorkbookName(String workbookName) {
        this.workbookName = workbookName;
    }

    public FileResource getFileResource() {
        return fileResource;
    }

    public void setFileResource(FileResource fileResource) {
        this.fileResource = fileResource;
        this.workbookName = fileResource.getName();
    }


}
