package com.spreadsheetbuddy.controller;

import com.spreadsheetbuddy.SheetBuddyApp;
import de.felixroske.jfxsupport.FXMLController;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * <p>
 * MenuController controls and handles events for the File MenuBar
 * </p>
 */
@FXMLController
public class MenuController {
    private final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Value("${sheetbuddy.aboutpage}")
    String aboutPageUri;


    @FXML
    MenuBar mainMenu;

    @FXML
    protected void openWorkBook(ActionEvent event) {


    }

    @FXML
    protected void openAboutPage(ActionEvent actionEvent) {
        HostServices hostServices = SheetBuddyApp.getAppHostServices();
        hostServices.showDocument(aboutPageUri);
    }
}