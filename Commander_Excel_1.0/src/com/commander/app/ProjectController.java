package com.commander.app;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.stage.Stage;

/**
 * @author Harry Gingles Dulaney IV
 */
public class ProjectController {

	private MainMenu mm;

	@FXML
	protected void handleCSVFilter(ActionEvent event) throws Exception {
		
		CSVFilterController controller = new CSVFilterController();
		controller.start();
		

	}

	public ProjectController() {
	

	}

	public void setMainmenu(MainMenu mm) {
		this.mm = MainMenu.getMainMenu();

	}

}
