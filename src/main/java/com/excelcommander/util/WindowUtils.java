package com.excelcommander.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import org.controlsfx.control.spreadsheet.SpreadsheetView;
import org.springframework.context.ConfigurableApplicationContext;

import com.excelcommander.ExcelCommanderApplication;
import com.excelcommander.controller.ParentController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    public static <T> Stage open(String fxmlPath, String title, HashMap<String, T> parameters, Modality windowModality)
            throws Exception {

        Stage stage = new Stage();
        stage.initModality(windowModality);

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
            loader.setControllerFactory(ctx::getBean);
//            loader.setResources();


            return loader;
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
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
