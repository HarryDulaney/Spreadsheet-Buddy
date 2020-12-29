//package com.spreadsheetbuddy.util;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.FormulaEvaluator;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.controlsfx.control.spreadsheet.GridBase;
//import org.controlsfx.control.spreadsheet.SpreadsheetCell;
//import org.controlsfx.control.spreadsheet.SpreadsheetView;
//
//import java.io.File;
//import java.io.FileInputStream;
//
///**
// * This class allows for the displaying of excel files within a window by
// * utilizing SpreadsheetView from controlsFX and the reading capabilities of     the POI library.
// * <p>
// * Only usable for .xlsx files
// */
//
//public class ex {
//
//    private String filePath;
//
//    private int sheetIndex;
//
//    private boolean editible;
//
//    private FileInputStream inStream;
//    private XSSFWorkbook poiWorkbook;
//    private XSSFSheet poiSheet;
//
//    private SpreadsheetView theView;
//
//    public ex(String path, int sheetIndex, boolean editable) {
//        filePath = path;
//        this.editible = editable;
//        this.sheetIndex = sheetIndex;
//    }
//
//    public ex(String path, int sheetIndex) {
//        filePath = path;
//        this.editible = false;
//        this.sheetIndex = sheetIndex;
//    }
//
//    private void initializeView() throws Exception {
//        GridBase grid = excelToGrid();
//
//        theView = new SpreadsheetView(grid);
//        theView.setEditable(editible);
//    }
//
//    public SpreadsheetView getView() throws Exception {
//        initializeView();
//        return theView;
//    }
//
//    /**
//     * Updates the values in the view. This may happen after the Excel file has been
//     * modified after the initial reading.
//     *
//     * @throws Exception
//     */
//    public void updateView() throws Exception {
//        GridBase newgrid = excelToGrid();
//
//        theView.setGrid(newgrid);
//    }
//
//
//
//
//
//
//
//
//
//
//
//    /**
//     * Calculates the number of rows and columns in the sheet by looping
//     * and reading all the things :)
//     *
//     * @return the size as int[{rows, cols}]
//
//
//    private void openBook() throws Exception {
//        try {
//            File myFile = new File(filePath);
//            inStream = new FileInputStream(myFile);
//
//            poiWorkbook = new XSSFWorkbook(inStream);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }
//
//    private void closeBook() throws Exception {
//
//
//        try {
//            poiWorkbook.close();
//            inStream.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }
//}