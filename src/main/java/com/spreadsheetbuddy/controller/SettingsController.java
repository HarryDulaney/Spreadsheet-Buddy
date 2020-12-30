package com.spreadsheetbuddy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.prefs.Preferences;

@Component
@FxmlView("/fxml/settingsview.fxml")
public class SettingsController {
    private final Logger logger = LoggerFactory.getLogger(SettingsController.class);
    private final FxWeaver fxWeaver;
    private Stage popUpStage;
    private final Preferences preferences = Preferences.userNodeForPackage(getClass());

    @FXML
    protected AnchorPane anchorPane;
    @FXML
    protected HBox colorPck1;
    @FXML
    protected ToggleGroup radBtnPropGroup1;


    @Autowired
    public SettingsController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;

    }

    @FXML
    public void initialize() {
        this.popUpStage = new Stage();
        popUpStage.setScene(new Scene(anchorPane));
        //Set eventlisteners

    }

    void show() {
        popUpStage.show();
    }

    @FXML
    private void closeSettingsWindow(ActionEvent actionEvent) {
        popUpStage.close();

    }
}
