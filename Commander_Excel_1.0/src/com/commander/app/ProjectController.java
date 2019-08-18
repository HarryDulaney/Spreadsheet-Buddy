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

	private ObservableList<Command> displayList = null;

	private MainMenu mainMenu;

	private ProjectBean Current_Project = ProjectBean.getInstance();

	@FXML
	private Label NoCommandsLabel;

	@FXML
	private Label Label1;

	@FXML
	private TableView<Command> tableView;

	@FXML
	private TableColumn<Command, String> commName;

	public ProjectController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		NoCommandsLabel.setVisible(false);
		
		Label1.setText(Current_Project.getName());

		commName.setCellValueFactory(new PropertyValueFactory<Command, String>("commName"));

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
