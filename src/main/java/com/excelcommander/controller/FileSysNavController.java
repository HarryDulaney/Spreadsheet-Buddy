package com.excelcommander.controller;

import com.excelcommander.model.Project;
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

		List<Project> projectList;
		if (parameters != null) {
			projectList = (List<Project>) parameters.get("project_list");
			initListView(projectList);
		}



	}

	private void initListView(List<Project> projectList) {
		JFXListView<Label> listView = new JFXListView<>();

		for (Project project : projectList) {
				listView.getItems().add(new Label(project.getProjectName()));

		}
		listView.autosize();

		anchorPaneFileNav.getChildren().add(listView);

	}

	@Override
	protected void onClose() {

	}
}
