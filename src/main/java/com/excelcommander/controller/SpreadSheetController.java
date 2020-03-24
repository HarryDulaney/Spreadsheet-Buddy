package com.excelcommander.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import javafx.stage.Stage;

/**
 * @author HGDIV
 */
@Controller
public class SpreadSheetController extends ParentController {


	private ApplicationContext ctx;
	

	public SpreadSheetController() {

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
