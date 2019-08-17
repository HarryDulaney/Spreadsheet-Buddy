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

/**
 * @author HG Dulaney IV
 */

public class ProjectController implements Initializable {

	private ObservableList<Command> displayList;

	private MainMenu mainMenu;

	@FXML
	private Label NoCommandsLabel;

	@FXML
	private Label Label1;

	@FXML
	private TableView<Command> tableView;

	@FXML
	private TableColumn<Command, String> displayName;

	public ProjectController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Label1.setText(ProjectBean.getName());

		Command commandOne = new Command();
		commandOne.setId(123);
		commandOne.setName("UPDATE");
		commandOne.setFileIn(new File("C:\\Users\\JordanLightgate"));
		commandOne.setFileOut(new File("C:\\Users"));

		ProjectBean.addCommand(commandOne);

		displayList = FXCollections.observableArrayList(ProjectBean.getSooperCommands());
		displayName.setCellValueFactory(cellData -> cellData.getValue().getDisplayName());

	}

	@FXML
	protected void handleNewSuperCommand(ActionEvent event) {

	}

	public void setMainmenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;

		tableView.setItems(displayList);

	}

}
