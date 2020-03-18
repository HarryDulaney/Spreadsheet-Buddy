package com.commander.app;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * @author HG Dulaney IV
 */

public class ProjectController {

	private MainMenu mainMenu;
	

	@FXML
	private Label Label1;

	public ProjectController() {

	}

	@FXML
	public void initialize() {

		

	}

	public void setMainmenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;

	}

}
