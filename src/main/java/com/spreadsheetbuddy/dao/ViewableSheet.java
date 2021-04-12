package com.spreadsheetbuddy.dao;

import org.apache.poi.ss.usermodel.Cell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellBase;
import org.controlsfx.control.spreadsheet.SpreadsheetView;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ViewableSheet {

    SpreadsheetView spreadSheetView;
    /* Mapping of the sheet read in from XSSFSheet */
    private LinkedList<List<Map<String, Cell>>> sheetData;


}
