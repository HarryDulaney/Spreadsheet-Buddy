package com.commander.app;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.commander.app.model.Command;
import com.commander.app.model.ProjectBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author HG Dulaney IV
 */

public class ProjectController implements Initializable {

	private MainMenu mainMenu;

	@FXML
	private Label NoCommandsLabel;

	@FXML
	private Label label1;

	@FXML
	private TableView<Command> tableView;

	@FXML
	private TableColumn<Command, String> commandName;

	public ProjectController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if (ProjectBean.getInstance().getSooperCommands().isEmpty()) {
			NoCommandsLabel.setVisible(true);
		} else {
			NoCommandsLabel.setVisible(false);

		}
		ObservableList<Command> commandList = FXCollections
				.observableArrayList(ProjectBean.getInstance().getSooperCommands());

		commandName.setCellValueFactory(new PropertyValueFactory<Command, String>("commandName"));

		tableView.setItems(commandList);

		label1.setText(ProjectBean.getInstance().getName());

	}

	/*
	 * private List<Command>commandList parseCommandList() { }
	 */

	@FXML
	protected void handleNewSuperCommand(ActionEvent event) {

	}

	public void setMainmenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;

	}

}
