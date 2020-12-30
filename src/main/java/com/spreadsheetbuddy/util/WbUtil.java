package com.spreadsheetbuddy.util;

import com.spreadsheetbuddy.controller.WorkbookController;
import com.spreadsheetbuddy.model.Project;
import com.spreadsheetbuddy.service.FileService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.spreadsheet.GridBase;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class WbUtil {


    public static void saveWorkbook(Workbook wb, Project project) throws IOException {

        try (OutputStream fileOut = new FileOutputStream(project.getOpenFile())) {
            wb.write(fileOut);
        }
        wb.close();

    }


    public static Workbook createBlankWorkbook(File file) throws Exception {
        Workbook wb = new XSSFWorkbook();

        try (FileOutputStream fOs = new FileOutputStream(file)) {
            wb.createSheet("New Sheet");
            wb.write(fOs);
            DialogHelper.showInfoAlert("Successful Operation", "You created a new workbook with one spreadsheet", "New Sheet", false);
            return wb;
        } catch (Exception ex) {
            DialogHelper.showSimpleAlert("Error during writing your new file", Alert.AlertType.ERROR);
            ex.printStackTrace();
        }
        throw new Exception("Failed to create new Workbook");
    }

    public static void validateSheetName(String name) throws IllegalArgumentException {
        try {
            WorkbookUtil.validateSheetName(name);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            DialogHelper.showInfoAlert(e.getMessage(), true);
        }
    }

    public static String createSafeSheetName(String name) {
        return WorkbookUtil.createSafeSheetName(name);
    }

    /**
     * Creates a {@link GridBase} object from the excel file located at the path
     *
     * @return List<GridBase>
     */
    public static List<GridBase> mapWorkbookGrid(XSSFWorkbook workbook) {
        List<GridBase> workbookContents = new LinkedList<>();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            XSSFSheet poiSheet = workbook.getSheetAt(i);
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            GridBase grid = SsUtil.mapSheetToGrid(poiSheet, evaluator);
            workbookContents.add(grid);
        }
        return workbookContents;
    }


}
