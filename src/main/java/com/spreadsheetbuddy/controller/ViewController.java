package com.spreadsheetbuddy.controller;

import com.spreadsheetbuddy.model.Project;
import com.spreadsheetbuddy.service.FileService;
import com.spreadsheetbuddy.util.DialogHelper;
import com.spreadsheetbuddy.util.RecentFilesUtil;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
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
    Logger logger = LoggerFactory.getLogger(ViewController.class);

    @FXML
    protected Menu recentFilesMenu;

    private final FxWeaver fxWeaver;
    private final String aboutPageUri = "https://github.com/HarryDulaney/Spreadsheet-Buddy"; //TODO: Replace
    private final String issuesPageUri = "https://github.com/HarryDulaney/Spreadsheet-Buddy/issues"; //TODO: Replace
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
    public ViewController(
            FxWeaver fxWeaver,
            FxControllerAndView<SpreadsheetController, SpreadsheetView> sheetControlandView) {
        this.fxWeaver = fxWeaver;
        this.sheetControlandView = sheetControlandView;
    }


    @FXML
    public void initialize() {
        project = new Project(System.getProperties().getProperty("user.name"));

        //Get recent files from persistent memory and create an ObservableList<>
        recentFilesMenu = RecentFilesUtil.initRecentFileMenu(recentFilesMenu);

/************************* FXWeaver examples of runtime event definition *************************/
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

    /*************************************** UI ActionEvent Handling *****************************************/
    @FXML
    protected void openAboutPage(ActionEvent actionEvent) {
        fxWeaver.getBean(HostServices.class).showDocument(aboutPageUri);
    }

    @FXML
    protected void openWorkBook(ActionEvent event) {

        File wbFile = DialogHelper.showFilePrompt("Choose the workbook to open", ".xlsx");
        assert wbFile != null;
        logger.info(".xlsx file picked to open -> " + wbFile.getName());
//        sheetControlandView.getView() //TODO: This is how you get the SpreadsheetView

    }

    @FXML
    protected void exitRequested(ActionEvent actionEvent) {
        try {
            fxWeaver.getBean(Application.class).stop();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Error exiting when calling JavaFxApplication.stop() method");
            DialogHelper.showAlert("Something went wrong: " + ex.getLocalizedMessage(), "Background Process " +
                            "Running Error",
                    "Alert!", Alert.AlertType.ERROR);
        }
    }

    /**
     * Example of using FxWeaver to get a registered JavaFx Bean
     * @param actionEvent fire event from MenuItem
     */
    @FXML
    protected void openIssuesPage(ActionEvent actionEvent) {
        fxWeaver.getBean(HostServices.class).showDocument(issuesPageUri);

    }

    @FXML
    protected void createNewWorkbook(ActionEvent actionEvent) {
        // TODO: Define
    }

    @FXML
    protected void closeWorkbook(ActionEvent actionEvent) {
        // TODO: Define

    }

    @FXML
    protected void saveWorkbook(ActionEvent actionEvent) {
        // TODO: Define
    }

    @FXML
    protected void saveWorkbookAs(ActionEvent actionEvent) {
        // TODO: Define

    }

    @FXML
    protected void openPreferences(ActionEvent actionEvent) {
        // TODO: Define

    }
}