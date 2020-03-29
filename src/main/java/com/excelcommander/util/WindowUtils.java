package com.excelcommander.util;

import com.excelcommander.ExcelCommanderApplication;
import com.excelcommander.controller.ParentController;
import impl.org.controlsfx.skin.GridCellSkin;
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
import org.controlsfx.control.spreadsheet.*;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * @author HGDIV
 */
public class WindowUtils {

    private static ConfigurableApplicationContext ctx = ExcelCommanderApplication.getCtx();

    public static <T> void replaceFxmlOnWindow(Pane root, String path, Stage stage, HashMap<String, T> parameters)
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

    /**
     * Creates new Grid model and set it to the live SpreadsheetView
     *
     * @param currentView The SpreadsheetView from the current window
     * @param workbook XSSFWorkbook loaded from an file
     */
    public static void renderNewSheet(SpreadsheetView currentView, Workbook workbook, Tab currentTab) {

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

                    String temp = BuiltinFormats.getBuiltinFormat(cell.getCellStyle().getDataFormat());

                    if (temp.equals(BuiltinFormats.getBuiltinFormat(0xe)) ||
                            temp.equals(BuiltinFormats.getBuiltinFormat(0xf)) ||
                            temp.equals(BuiltinFormats.getBuiltinFormat(0x10)) ||
                            temp.equals(BuiltinFormats.getBuiltinFormat(0x11))) {

                        observableRow.add(SpreadsheetCellType.DATE.createCell(curRow, cellNum, 1, 1,
                                LocalDate.from(cell.getLocalDateTimeCellValue())));

                    } else {
                        switch (cell.getCellType()) {
                            case STRING:
                                observableRow.add(SpreadsheetCellType.STRING.createCell(curRow, cellNum, 1, 1, cell.getStringCellValue().trim()));
                                break;
                            case NUMERIC:
                                observableRow.add(SpreadsheetCellType.DOUBLE.createCell(curRow, cellNum, 1, 1, cell.getNumericCellValue()));
                                break;
                            case FORMULA:
                                observableRow.add(SpreadsheetCellType.STRING.createCell(curRow, cellNum, 1, 1, cell.getCellFormula()));
                                break;
                            case ERROR:
                                observableRow.add(SpreadsheetCellType.STRING.createCell(curRow, cellNum, 1, 1, String.valueOf(cell.getErrorCellValue())));
                                break;
                            case BLANK:
                                observableRow.add(SpreadsheetCellType.STRING.createCell(curRow, cellNum, 1, 1, " "));
                                break;
                            default:
                                observableRow.add(SpreadsheetCellType.STRING.createCell(curRow, cellNum, 1,
                                        1, String.valueOf(cell.getRichStringCellValue())));

                        }

                    }
                } else {
                    observableRow.add(SpreadsheetCellType.STRING.createCell(curRow, cellNum, 1, 1, " ")); //Write a Blank cell to model Workbook's Blank cell

                }

            }
            observableSheet.add(observableRow);
        }
        currentTab.setText(String.valueOf(workbook.getSheetName(0)));
        grid.setRows(observableSheet);
        currentView.setGrid(grid); //Set new gridbase Grid model to the current Spreadsheetview
        currentView.autosize();

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


}
