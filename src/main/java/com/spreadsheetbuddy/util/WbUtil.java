package com.spreadsheetbuddy.util;

import com.spreadsheetbuddy.controller.WorkbookController;
import com.spreadsheetbuddy.model.Project;
import javafx.scene.control.Alert;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WbUtil {

    public static XSSFWorkbook loadFromFile(File file) throws IOException, InvalidFormatException {
        return new XSSFWorkbook(file);
    }

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

}
