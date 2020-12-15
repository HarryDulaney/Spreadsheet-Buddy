package com.spreadsheetbuddy.controller;

import com.spreadsheetbuddy.service.FileService;
import com.spreadsheetbuddy.util.DialogHelper;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.controlsfx.control.spreadsheet.SpreadsheetView;
import org.springframework.beans.factory.annotation.Autowired;
import net.rgielen.fxweaver.core.FxControllerAndView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;

@Component
@FxmlView("/fxml/main.fxml")
public class ViewController {

    private final FxWeaver fxWeaver;

    private final String aboutPageUri;
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


    }

}