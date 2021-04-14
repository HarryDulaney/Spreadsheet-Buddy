package com.spreadsheetbuddy.model;

import org.apache.poi.ss.formula.functions.Function;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;

import java.util.Date;

public class CellModel {

    private XSSFCell poiCell;
    private XSSFFormulaEvaluator formulaEvaluator;


    public CellModel(XSSFCell poiCell) {
        this.poiCell = poiCell;
        formulaEvaluator = XSSFFormulaEvaluator.create(poiCell.getSheet().getWorkbook(), null, null);
    }

    public String asString() {
        DataFormatter dataFormatter = new DataFormatter();
        return dataFormatter.formatCellValue(poiCell, formulaEvaluator);

    }


    public XSSFCell getPoiCell() {
        return poiCell;
    }

    public void setPoiCell(XSSFCell poiCell) {
        this.poiCell = poiCell;
    }

}
