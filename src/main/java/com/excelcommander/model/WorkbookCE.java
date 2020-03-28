package com.excelcommander.model;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;
import java.util.Map;


public class WorkbookCE {

    private Workbook workbook;
    private int maxRow;
    private int maxColumn;

    private List<List<Cell>> sheetAsList;


    public WorkbookCE(){}

    public WorkbookCE(Workbook workbook) {
        this.workbook = workbook;
    }


    public Workbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }

    public int getMaxRow() {
        return maxRow;
    }

    public void setMaxRow(int maxRow) {
        this.maxRow = maxRow;
    }

    public int getMaxColumn() {
        return maxColumn;
    }

    public void setMaxColumn(int maxColumn) {
        this.maxColumn = maxColumn;
    }

    public List<List<Cell>> getSheetAsList() {
        return sheetAsList;
    }

    public void setSheetAsList(List<List<Cell>> sheetAsList) {
        this.sheetAsList = sheetAsList;
    }


}
