package com.spreadsheetbuddy.controller;

import com.spreadsheetbuddy.SheetBuddyApp;
import com.spreadsheetbuddy.view.MenuView;
import com.spreadsheetbuddy.view.SSheetView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import java.lang.management.PlatformLoggingMXBean;

@FXMLController
public class ViewController {
    /**
     * The SpreadSheetView View Object
     */
    private SSheetView sSheetView;
    /**
     * The MenuBar View Object
     */
    private MenuView menuView;

    @FXML
    protected VBox rootNode;

    @Autowired
    public void setSSheetView(SSheetView sSheetView) {
        this.sSheetView = sSheetView;
    }


    @Autowired
    public void setMenuView(MenuView menuView) {
        this.menuView = menuView;
    }


//    @Override
//    public void onApplicationEvent(SheetBuddyApp.StageReadyEvent event) {
//        rootNode.getChildren().add(sSheetView.getView());
//        rootNode.setFillWidth(true);
//    }
}

