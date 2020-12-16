package com.spreadsheetbuddy.model;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;

public class Project {
    private File currentWorkbookFile;
    private Workbook workbook;


    public File getCurrentWorkbookFile() {
        return currentWorkbookFile;
    }

    public void setCurrentWorkbookFile(File currentWorkbookFile) {
        this.currentWorkbookFile = currentWorkbookFile;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }


}
