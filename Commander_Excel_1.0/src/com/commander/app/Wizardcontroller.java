package com.commander.app;

import com.commander.app.model.WorkBookmaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

/**
 * @author Harry Gingles Dulaney IV
 */
public class Wizardcontroller {

	@FXML
	public TextField workbookNameInput;

	@FXML
	private TextField spreadsheetNameInput;

	private MainMenu mainmenu;

	@FXML
	protected void completeActionButton(ActionEvent event) {

		if (!workbookNameInput.getText().isEmpty() || !spreadsheetNameInput.getText().isEmpty()) {
			
			WorkBookmaker wbook = new WorkBookmaker(workbookNameInput.getText().toString(),spreadsheetNameInput.getText().toString());
			setWorkBooktoPject(wbook);
			
			try {
				wbook.executeTask();

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			Alert a = new Alert(AlertType.WARNING);
			a.setHeaderText("Something went wrong");
			a.setContentText("Please fill in all required text fields");
			a.showAndWait();
		}

	}

	/**
	 * @return the mainmenu
	 */


	public void setMainmenu(MainMenu mainmenu) {
		this.mainmenu = mainmenu;

	}

	public void setWorkBooktoPject(WorkBookmaker wbmaker) {

		MainMenu.getCurrentProject().addProjectTask(wbmaker);
		
	}

	
		
	


}
