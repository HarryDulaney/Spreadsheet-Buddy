package com.commander.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.util.ArrayList;

import com.commander.app.model.CommanderTask;
import com.commander.app.model.Project;

/**
 * @author Harry Gingles Dulaney IV
 */
public class ProjectController {

	public ObservableList<CommanderTask> projectTasks = FXCollections.observableArrayList();

	@FXML
	private Label projectNameShowing;

	@FXML
	private Label projectUserID;

	@FXML
	private TableView<CommanderTask> projectTable;

	@FXML
	private TableColumn<CommanderTask, String> taskNameColumn;

	private MainMenu mainmenu;

	@FXML
	protected void handleCreateWorkbook(ActionEvent event) throws IOException {
		
		MainMenu.getMainMenu().showWorkBookWizard();

		

	}

	public ProjectController() {

	
	}

	@FXML
	private void initialize() {

		setProjectTasks(MainMenu.getCurrentProject().getProjectTasks());

		taskNameColumn.setCellValueFactory(cellData -> cellData.getValue().taskNameProperty());

		showProjectData(MainMenu.getCurrentProject());
	}

	private void showProjectData(Project project) {

		if (project != null) {
			projectNameShowing.setText(project.getProjectFolder());
			projectUserID.setText(project.getUserID());
		} else {
			projectNameShowing.setText("");
			projectUserID.setText("");
		}

	}

	public void setMainmenu(MainMenu mainmenu) {
		this.mainmenu = mainmenu;

		projectTable.setItems(projectTasks);
	}


	public void setProjectNameShowing(Label projectNameShowing) {
		this.projectNameShowing = projectNameShowing;
	}

	public ObservableList<CommanderTask> setProjectTasks(ArrayList<CommanderTask> tasks) {

		projectTasks.addAll(tasks);
		return projectTasks;

	}

}
