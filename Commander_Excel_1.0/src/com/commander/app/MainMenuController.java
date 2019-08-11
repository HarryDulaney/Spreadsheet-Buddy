package com.commander.app;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBContext.*;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.codoid.products.exception.FilloException;
import com.commander.app.model.Project;
import com.commander.app.model.ProjectListWrapper;

import javafx.fxml.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
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

	private Recordset recordset;
	private Connection connection;

	@FXML
	protected void handleSaveProjectAction(ActionEvent event) {

		Project pject = MainMenu.getCurrentProject();

		try {
			JAXBContext context = JAXBContext.newInstance(ProjectListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			ProjectListWrapper wrapper = new ProjectListWrapper();
			wrapper.setProject(pject);

			if (!MainMenu.getCurrentProject().getProjectTasks().isEmpty()) {
				wrapper.setTasks(MainMenu.getCurrentProject().getProjectTasks());
			}

			m.marshal(wrapper, MainMenu.getCurrentProject().getProjectFilepath());

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
			// MainMenu.getMainMenu().showProject();
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

		// MainMenu.getMainMenu().showWizard("/com/commander/app/view/WizardDialog.fxml");
	}

	@FXML
	public void initialize() {

	}
	
	protected void handleUpdate(ActionEvent event) {
		Fillo fillo=new Fillo();
		Connection connection = this.connection;
		try {
			connection = fillo.getConnection("C:\\Test.xlsx");
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String strQuery="Update Sheet1 Set Country='US' where ID=100 and name='John'";
		 
		try {
			connection.executeUpdate(strQuery);
		} catch (FilloException e) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("Something went wrong");
			alert.setContentText("Cannot load CSV Wizard");

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String exceptionText = sw.toString();

			Label label = new Label("The exception stacktrace was:");

			TextArea textArea = new TextArea(exceptionText);
			textArea.setEditable(false);
			textArea.setWrapText(true);

			textArea.setMaxWidth(Double.MAX_VALUE);
			textArea.setMaxHeight(Double.MAX_VALUE);
			GridPane.setVgrow(textArea, Priority.ALWAYS);
			GridPane.setHgrow(textArea, Priority.ALWAYS);

			GridPane expContent = new GridPane();
			expContent.setMaxWidth(Double.MAX_VALUE);
			expContent.add(label, 0, 0);
			expContent.add(textArea, 0, 1);

			
			alert.getDialogPane().setExpandableContent(expContent);

		}
		 
		connection.close();

		
	}



	protected void handleSelect(ActionEvent event) {

		try {
			Fillo fillo = new Fillo();
			connection = fillo.getConnection("C:\\Test.xlsx");

		} catch (Exception e) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("Something went wrong");
			alert.setContentText("Cannot load CSV Wizard");

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String exceptionText = sw.toString();

			Label label = new Label("The exception stacktrace was:");

			TextArea textArea = new TextArea(exceptionText);
			textArea.setEditable(false);
			textArea.setWrapText(true);

			textArea.setMaxWidth(Double.MAX_VALUE);
			textArea.setMaxHeight(Double.MAX_VALUE);
			GridPane.setVgrow(textArea, Priority.ALWAYS);
			GridPane.setHgrow(textArea, Priority.ALWAYS);

			GridPane expContent = new GridPane();
			expContent.setMaxWidth(Double.MAX_VALUE);
			expContent.add(label, 0, 0);
			expContent.add(textArea, 0, 1);

			
			alert.getDialogPane().setExpandableContent(expContent);

		}
		try {
			String strQuery = "Select * from Sheet1 where ID=100 and name='John'";
			recordset = connection.executeQuery(strQuery);

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("Something went wrong");
			alert.setContentText("Cannot load CSV Wizard");

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String exceptionText = sw.toString();

			Label label = new Label("The exception stacktrace was:");

			TextArea textArea = new TextArea(exceptionText);
			textArea.setEditable(false);
			textArea.setWrapText(true);

			textArea.setMaxWidth(Double.MAX_VALUE);
			textArea.setMaxHeight(Double.MAX_VALUE);
			GridPane.setVgrow(textArea, Priority.ALWAYS);
			GridPane.setHgrow(textArea, Priority.ALWAYS);

			GridPane expContent = new GridPane();
			expContent.setMaxWidth(Double.MAX_VALUE);
			expContent.add(label, 0, 0);
			expContent.add(textArea, 0, 1);

			
			alert.getDialogPane().setExpandableContent(expContent);
		}

		try {
			while (recordset.next()) {
				System.out.println(recordset.getField("Details"));
			}
		} catch (FilloException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("Something went wrong");
			alert.setContentText("Cannot load CSV Wizard");

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String exceptionText = sw.toString();

			Label label = new Label("The exception stacktrace was:");

			TextArea textArea = new TextArea(exceptionText);
			textArea.setEditable(false);
			textArea.setWrapText(true);

			textArea.setMaxWidth(Double.MAX_VALUE);
			textArea.setMaxHeight(Double.MAX_VALUE);
			GridPane.setVgrow(textArea, Priority.ALWAYS);
			GridPane.setHgrow(textArea, Priority.ALWAYS);

			GridPane expContent = new GridPane();
			expContent.setMaxWidth(Double.MAX_VALUE);
			expContent.add(label, 0, 0);
			expContent.add(textArea, 0, 1);

			
			alert.getDialogPane().setExpandableContent(expContent);

		}

		recordset.close();
		connection.close();
	}
	protected void handleInsert(ActionEvent event) {
		Fillo fillo=new Fillo();
		
		Connection connection = this.connection;
		try {
			connection = fillo.getConnection("C:\\Test.xlsx");
		} catch (FilloException e) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("Something went wrong");
			alert.setContentText("Cannot load CSV Wizard");

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String exceptionText = sw.toString();

			Label label = new Label("The exception stacktrace was:");

			TextArea textArea = new TextArea(exceptionText);
			textArea.setEditable(false);
			textArea.setWrapText(true);

			textArea.setMaxWidth(Double.MAX_VALUE);
			textArea.setMaxHeight(Double.MAX_VALUE);
			GridPane.setVgrow(textArea, Priority.ALWAYS);
			GridPane.setHgrow(textArea, Priority.ALWAYS);

			GridPane expContent = new GridPane();
			expContent.setMaxWidth(Double.MAX_VALUE);
			expContent.add(label, 0, 0);
			expContent.add(textArea, 0, 1);

			
			alert.getDialogPane().setExpandableContent(expContent);
		}
		String strQuery="INSERT INTO sheet4(Name,Country) VALUES('Peter','UK')";

		try {
			connection.executeUpdate(strQuery);
		} catch (FilloException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("Something went wrong");
			alert.setContentText("Cannot load CSV Wizard");

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String exceptionText = sw.toString();

			Label label = new Label("The exception stacktrace was:");

			TextArea textArea = new TextArea(exceptionText);
			textArea.setEditable(false);
			textArea.setWrapText(true);

			textArea.setMaxWidth(Double.MAX_VALUE);
			textArea.setMaxHeight(Double.MAX_VALUE);
			GridPane.setVgrow(textArea, Priority.ALWAYS);
			GridPane.setHgrow(textArea, Priority.ALWAYS);

			GridPane expContent = new GridPane();
			expContent.setMaxWidth(Double.MAX_VALUE);
			expContent.add(label, 0, 0);
			expContent.add(textArea, 0, 1);

			
			alert.getDialogPane().setExpandableContent(expContent);
		}

		connection.close();
	}
}