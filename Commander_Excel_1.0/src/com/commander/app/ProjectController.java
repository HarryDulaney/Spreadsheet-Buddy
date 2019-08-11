package com.commander.app;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * @author HG Dulaney IV
 */
public class ProjectController {

	private MainMenu mm;

	@FXML
	private Label Label1;

	@FXML
	protected void handleCSVFilter(ActionEvent event) throws Exception {

		Stage stage = new Stage();

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainMenu.class.getResource("/com/commander/app/view/CSVfilterWiz.fxml"));
		Parent root = (Parent) loader.load();

		CSVFilterController controller = loader.getController();
		controller.setMainMenu(MainMenu.getMainMenu());
		Scene scene = new Scene(root, 550, 600);
		// scene.getStylesheets().add("com/commander/app/view/ThemeOne.css");

		stage.setScene(scene);
		if (CSVFilterController.getIsReady()) {
			stage.show();
		}
	}

	public ProjectController() {

	}

	@FXML
	public void initialize() {

		Label1.setText("Hello, " + System.getProperty("user.name") + " and welcome to SuperCommander.");

	}

	public void setMainmenu(MainMenu mm) {
		this.mm = mm;

	}

}
