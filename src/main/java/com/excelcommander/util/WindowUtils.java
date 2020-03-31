package com.excelcommander.util;

import com.excelcommander.ExcelCommanderApplication;
import com.excelcommander.controller.ParentController;
import com.excelcommander.model.WorkbookModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.controlsfx.control.spreadsheet.*;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.Format;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * @author HGDIV
 */
public class WindowUtils {

    private static ConfigurableApplicationContext ctx = ExcelCommanderApplication.getCtx();

    public static <T> void replaceWindowContent(Pane root, String path, Stage stage, HashMap<String, T> parameters)
            throws Exception {
        FXMLLoader loader = loadFxml(path);

        root.getChildren().clear();
        root.getChildren().add(loader.load());

        ParentController parentController = loader.getController();
        parentController.init(stage, parameters);
    }

    public static <T> Stage open(String fxmlPath, String title, HashMap<String, T> parameters, Modality mode)
            throws Exception {

        Stage stage = new Stage();
        stage.initModality(mode);

        return open(stage, fxmlPath, title, parameters);
    }

    public static <T> Stage open(Stage stage, String fxmlPath, String title, HashMap<String, T> parameters)
            throws Exception {

        stage.setTitle(title);
        stage.setResizable(true);

        FXMLLoader loader = loadFxml(fxmlPath);

        try {
            Scene scene = new Scene(loader.load());

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ParentController parentController = loader.getController();
        parentController.init(stage, parameters);

        return stage;
    }


    public static FXMLLoader loadFxml(String url) {

        try (InputStream fxmlStream = WindowUtils.class.getResourceAsStream(url)) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WindowUtils.class.getResource(url));
            loader.setControllerFactory(clazz -> ctx.getBean(clazz));
            /* loader.setResources(); */


            return loader;
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }


    public static void setView(ObservableList<ObservableList<SpreadsheetCell>> observableSheet,
                               SpreadsheetView ssView, Grid grid, Tab tab, String sheetName) {

        tab.setText(sheetName);
        grid.setRows(observableSheet);
        ssView.setGrid(grid);
        ssView.autosize();

    }

    public static void watchEvents(SpreadsheetView view, WatchListener listener) {
        view.focusedProperty().addListener((o, oldValue, newValue) -> {
            listener.watch(newValue);
        });
    }

    public static <T> void watchEvents(ComboBox<T> comboBox, WatchListener listener) {
        comboBox.focusedProperty().addListener((o, oldValue, newValue) -> {
            listener.watch(newValue);
        });
    }

    /**
     * Creates new Grid model and set it to the live SpreadsheetView
     *
     * @param workbookModel WorkbookModel Object
     */
    public static void parseToRender(WorkbookModel workbookModel) {
        Workbook workbook = workbookModel.getWorkbook();
        SpreadsheetView currentView = workbookModel.getSpreadsheetView();
        Tab currentTab = workbookModel.getTab();

        Grid grid = new GridBase(currentView.getGrid().getRowCount(), currentView.getGrid().getColumnCount());
        Sheet sheet = workbook.getSheetAt(0);
        ObservableList<ObservableList<SpreadsheetCell>> observableSheet = FXCollections.observableArrayList();

        for (int curRow = 0; curRow < grid.getRowCount(); curRow++) {
            Row row = null;
            ObservableList<SpreadsheetCell> observableRow = FXCollections.observableArrayList();
            if (curRow >= sheet.getFirstRowNum() && curRow <= sheet.getLastRowNum()) {
                row = sheet.getRow(curRow);

            }
            for (int cellNum = 0; cellNum < grid.getColumnCount(); cellNum++) {
                Cell cell = null;
                if (row != null) {
                    if (cellNum >= row.getFirstCellNum() && cellNum <= row.getLastCellNum()) {
                        cell = row.getCell(cellNum);
                    }

                }

                if (cell != null) {
                    DataFormatter formatter = new DataFormatter();
                    String cellAsString = formatter.formatCellValue(cell);
                    observableRow.add(SpreadsheetCellType.STRING.createCell(curRow, cellNum, 1, 1, cellAsString));

                } else {
                    observableRow.add(SpreadsheetCellType.STRING.createCell(curRow, cellNum, 1, 1, " ")); //Write a Blank cell to model Workbook's Blank cell

                }
            }
            observableSheet.add(observableRow);
        }
        setView(observableSheet, currentView, grid, currentTab, sheet.getSheetName());
    }


}
