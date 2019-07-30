package com.commander.app;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBContext.*;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.commander.app.model.Project;
import com.commander.app.model.ProjectListWrapper;

import javafx.fxml.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * @author Harry Gingles Dulaney IV
 */
/**
 * This controller still needs implementations for the event handlers that bind
 * to the root menu heading task bar ie.... Save Project, Close Project
 */
public class MainMenuController {

	@FXML
	protected void handleSaveProjectAction(ActionEvent event) {

		try {
			JAXBContext context = JAXBContext.newInstance(ProjectListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			ProjectListWrapper wrapper = new ProjectListWrapper();
			wrapper.setProjects(MainMenu.getCurrentProject());

			m.marshal(wrapper, Project.getProjectFilepath());

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	@FXML
	protected void handleCreateProjectAction(ActionEvent event) throws IOException {

		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("Select a location to create a new project folder");

		File nPfolder = chooser.showDialog(new Stage());

		TextInputDialog tId = new TextInputDialog();
		tId.setHeaderText("Name your project");
		tId.setContentText("Enter a name for the new project now");
		tId.setTitle("Create New Project");
		tId.showAndWait();

		String fPstring = nPfolder.getAbsolutePath() + "\\" + tId.getResult();
		File filePath = new File(fPstring);

		if (filePath.mkdir()) {

			Project prj = new Project(System.getProperty("user.name"), tId.getResult(), filePath);
			MainMenu.setCurrentProject(prj);

			MainMenu.getMainMenu().showProject();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Create New Project");
			alert.setHeaderText("Success!");
			alert.setContentText("You're new project named: " + tId.getResult() + " is located at " + fPstring);
			alert.showAndWait();

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

		// Project project = new
		// Project(System.getProperty("user.name"),Project.getProjectName(),Project.getProjectFilepath());

		// MainMenu.setCurrentProject(prj);

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

		MainMenu.getMainMenu().showWizard("/com/commander/app/view/WizardDialog.fxml");
	}

}