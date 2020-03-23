package com.excelcommander.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
