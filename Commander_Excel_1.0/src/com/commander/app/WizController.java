package com.commander.app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import com.commander.app.model.WorkBookmaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author Harry Gingles Dulaney IV
 */
public class WizController {

	private MainMenu mm;

	@FXML
	private Label header;

	@FXML
	private Label subheader;

	@FXML
	private TextField textField1;

	@FXML
	private TextField textField2;

	@FXML
	protected void handleCancelTaskWiz(ActionEvent event) throws Exception {
		
		MainMenu.getMainMenu().showProject();
	}

	@FXML
	protected void handleSubmitNewWB(ActionEvent event) throws IOException {

		if (!textField1.getText().isEmpty() && !textField2.getText().isEmpty()) {

			WorkBookmaker wbook = new WorkBookmaker(textField1.getText(), textField2.getText());
			setWorkBooktoPject(wbook);

			try {
				wbook.executeTask();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				this.mm = getMainMenu();

				mm.showProject();

			}

		} else {
			Alert a = new Alert(AlertType.WARNING);
			a.setHeaderText("Blank Input Detected");
			a.setContentText("Please fill in all required text fields");
			a.showAndWait();
		}

	}

	public WizController() {

	}

	@FXML
	public void initialize() {
		this.mm = MainMenu.getMainMenu();
	}

	private void setWorkBooktoPject(WorkBookmaker wb) {

		MainMenu.getCurrentProject().addProjectTask(wb);

	}

	public MainMenu getMainMenu() {
		return MainMenu.getMainMenu();
	}

	public void setMainmenu(MainMenu mm) {
		this.mm = MainMenu.getMainMenu();

	}

}
