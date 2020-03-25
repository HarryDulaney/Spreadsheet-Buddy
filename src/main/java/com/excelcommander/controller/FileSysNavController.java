package com.excelcommander.controller;

import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.util.HashMap;

@Controller
public class FileSysNavController extends ParentController {

    JFXListCell<Label> cells = new JFXListCell<>();
    ObservableList<JFXListCell<Label>> obList = FXCollections.observableArrayList();
    JFXListView<JFXListCell<Label>>listView;


    @FXML
    protected ScrollPane scrollPaneProjectNav;
    @FXML
    protected AnchorPane anchorPaneFileNav;

    @FXML
    protected void handleOpenSelectedProject(ActionEvent event) {
    }
    @Override
    public <T> void init(Stage stage, HashMap<String, T> parameters) {
        super.init(stage, parameters);



        initListView();

    }

    private void initListView() {

        listView.setItems(obList);




    }


    @Override
    protected void onClose() {

    }
}
