package com.spreadsheetbuddy.controller;

import javafx.fxml.FXML;
import net.rgielen.fxweaver.core.FxmlView;
import org.controlsfx.control.spreadsheet.SpreadsheetView;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 * MenuController controls and handles events for the File MenuBar
 * </p>
 */
@Component
@FxmlView("/fxml/ssheet.fxml")
public class SpreadsheetController {

    @FXML
    SpreadsheetView ssView;

//    @FXML
//    public void initialize() {}


    }
