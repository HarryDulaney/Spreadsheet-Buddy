package com.excelcommander.controller;

import java.util.HashMap;

import javafx.geometry.Insets;
import org.controlsfx.control.spreadsheet.SpreadsheetView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.excelcommander.util.SpreadSheetUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * @author HGDIV
 */
@Controller
public class ProjectController extends ParentController {
	
	@FXML
	SpreadsheetView ssView;

	@FXML
	private Label Label1;
	private ApplicationContext ctx;

	public ProjectController() {

	}
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		super.init(stage, parameters);

	}



	@Override
	protected void onClose() {
		// TODO Auto-generated method stub
		
	}
	@Autowired
	void setApplicationContext(ApplicationContext ctx) {
		this.ctx = ctx;
	}

}
