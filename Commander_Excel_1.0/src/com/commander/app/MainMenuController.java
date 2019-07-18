package com.commander.app;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import com.commander.app.model.Project;
import com.commander.app.model.PromptBox;
import javafx.fxml.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import com.commander.app.MainMenu.*;
import com.commander.app.MainMenu.*;


/**
 * @author Harry Gingles Dulaney IV
 */

public class MainMenuController {
	
	
	@FXML
	protected void handleSaveProjectAction(ActionEvent event) {

	}

	@FXML
	protected void handleCreateProjectAction(ActionEvent event) throws IOException {

		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("Select a location for your new project folder");

		File nPfolder = chooser.showDialog(new Stage());

		PromptBox pBox = new PromptBox("Type a name for your project folder below and then press Finish", true);
		pBox.start(new Stage());

		String fPstring = nPfolder.getAbsolutePath() + "\\" + pBox.getUserInput();
		File filePath = new File(fPstring);

		if (filePath.mkdir()) {

			Project prj = new Project(System.getProperty("user.name"), fPstring);
			MainMenu.setCurrentProject(prj);
	
			MainMenu.getMainMenu().showProject();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("You created a new project folder at: " + fPstring);
			alert.show();

		} else {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Something Went Wrong");
			alert2.setContentText("We were unable to create your project folder at " + fPstring);
			alert2.show();
		}

	}

	

	@FXML
	protected void handleOpenProject(ActionEvent event) throws IOException {

		DirectoryChooser directoryChooser = new DirectoryChooser();
		File file = new File(System.getProperty("user.home"));
		directoryChooser.setInitialDirectory(file);
		File toOpen = directoryChooser.showDialog(new Stage());

		String str = toOpen.toString();
		
		Project prj = new Project(System.getProperty("user.name"), str);

		MainMenu.setCurrentProject(prj);

		try {
			MainMenu.getMainMenu().showProject();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	protected void handleAboutSuperCommander(ActionEvent event) {

		try {
			URI url = new URI("https://github.com/Nothingrhymeswithorange/SuperCommander_1.0/blob/master/README.md");

			Desktop.getDesktop().browse(url);

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Something went wrong");
			alert.setContentText("We were unable to open the weblink to the about me document you requested");
			alert.show();
		}

	}

	@FXML
	protected void handleExitCommander(ActionEvent event) {

		MainMenu.setCurrentProject(new Project());

		System.exit(0);
	}

	@FXML
	protected void handleCreateWorkbookAction(ActionEvent event) throws IOException {
		
				MainMenu.getMainMenu().showWorkBookWizard();
	}



}