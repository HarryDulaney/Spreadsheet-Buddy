package com.excelcommander.controller;

import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.utils.JFXUtilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class FileSysNavController extends ParentController {

	JFXListCell<Label> cell = new JFXListCell<>();
	ObservableList<JFXListCell<Label>> obList = FXCollections.observableArrayList();
	JFXListView<JFXListCell<Label>> listView;

	@FXML
	protected ScrollPane scrollPaneProjectNav;
	@FXML
	protected AnchorPane anchorPaneFileNav;

	@FXML
	protected void handleOpenSelectedProject(ActionEvent event) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		super.init(stage, parameters);

		List<String> fileList = new ArrayList<>();
		if (parameters != null) {
			fileList = (List<String>) parameters.get("fileList");
		}

		initListView(fileList);

	}

	private void initListView(List<String> fileNameList) {

		listView = new JFXListView<JFXListCell<Label>>();

		for (String str : fileNameList) {
			cell.setItem(new Label(str));
			obList.add(cell);
		}
		listView.setItems(obList);
		
		anchorPaneFileNav.getChildren().add(listView);

	}

	@Override
	protected void onClose() {

	}
}
