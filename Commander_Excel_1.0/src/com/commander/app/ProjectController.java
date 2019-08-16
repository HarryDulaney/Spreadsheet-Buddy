package com.commander.app;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.CookieHandler;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.commander.app.model.Command;
import com.commander.app.model.Project;
import com.commander.app.model.ProjectBean;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * @author HG Dulaney IV
 */

public class ProjectController implements Initializable {

	private ObservableList<Command> displayList;

	private MainMenu mainMenu;

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
