package com.spreadsheetbuddy.controller;

import com.spreadsheetbuddy.model.Project;
import com.spreadsheetbuddy.service.FileService;
import com.spreadsheetbuddy.util.DialogHelper;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.controlsfx.control.spreadsheet.SpreadsheetView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import net.rgielen.fxweaver.core.FxControllerAndView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@FxmlView("/fxml/main.fxml")
public class ViewController {
//    @FXML
//    public SpreadsheetView ssheet;
    Logger logger = LoggerFactory.getLogger(ViewController.class);

    private final FxWeaver fxWeaver;
    private final String aboutPageUri;
    private static Project project;

    @Autowired
    private FileService fileService;

    @FXML
    protected VBox rootNode;

    @FXML
    MenuBar mainMenu;

    private final FxControllerAndView<SpreadsheetController, SpreadsheetView> sheetControlandView;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public ViewController(@Value("${sheetbuddy.aboutpage}") String aboutPageUri,
                          FxWeaver fxWeaver,
                          FxControllerAndView<SpreadsheetController, SpreadsheetView> sheetControlandView) {
        this.aboutPageUri = aboutPageUri;
        this.fxWeaver = fxWeaver;
        this.sheetControlandView = sheetControlandView;
    }


    @FXML
    public void initialize() {
        project = new Project();

//        helloButton.setOnAction(
//                actionEvent -> this.label.setText(greeting)
//        );
///*
//        openSimpleDialogButton.setOnAction(
//                actionEvent -> fxWeaver.loadController(DialogController.class).show()
//        );
//*/
//        openSimpleDialogButton.setOnAction(
//                actionEvent -> dialog.getController().show()
//        );
//
//        openTiledDialogButton.setOnAction(
//                actionEvent -> {
//                    FxControllerAndView<TiledDialogController, VBox> tiledDialog =
//                            fxWeaver.load(TiledDialogController.class);
//                    tiledDialog.getView().ifPresent(
//                            v -> {
//                                Label label = new Label();
//                                label.setText("Dynamically added Label");
//                                v.getChildren().add(label);
//                            }
//                    );
//                    tiledDialog.getController().show();
//                }
//        );
    }

    /********************************************* UI Events ***********************************************/
    @FXML
    protected void openAboutPage(ActionEvent actionEvent) {
        fxWeaver.getBean(HostServices.class).showDocument(aboutPageUri);
    }

    @FXML
    protected void openWorkBook(ActionEvent event) {
        File wbFile = DialogHelper.showFilePrompt("Choose the workbook to open", ".xlsx");
        logger.info(".xlsx file picked to open -> " + wbFile.getName());
//        sheetControlandView.getView()

    }

}