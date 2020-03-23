package com.excelcommander.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.excelcommander.model.Project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author HGDIV
 */
/**
 * This controller still needs implementations for the event handlers that bind
 * to the root menu heading task bar ie.... Save ProjectXml, Close ProjectXml
 */
public class MenuController {

	private Project project;
	private MainMenu mainmenu;

	@FXML
	protected void handleSaveProject(ActionEvent event) {

		if (MainMenu.getCurrent() != null) {

			try {
				saveProject();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		} else {
			Alert alrt = new Alert(AlertType.WARNING);
			alrt.setHeaderText("Please create a projectXml first before saving it");
			alrt.setContentText(
					"You must first open or create a new projectXml in order to perform the save projectXml action");
			alrt.show();

		}

	}

	@FXML
	protected void handleNewProject(ActionEvent event) throws IOException {

		createNewProject();

		if (MainMenu.getCurrent() != null) {

			saveProject();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Create New Project");
			alert.setHeaderText("Success!");
			alert.setContentText("You're new project named: " + project.getName() + " was saved at: "
					+ project.getProjectFile().toString());
			alert.showAndWait();

			openProject(MainMenu.getCurrent().getProjectFile());

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Something Went Wrong");
			alert.setContentText("Please try again to create a new project");
			alert.showAndWait();
		}

	}

	@FXML
	protected void handleOpenProject(ActionEvent event) throws IOException {

		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose the project you would like to open");
		chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".xml", "*.xml"));
		File toOpen = chooser.showOpenDialog(new Stage(StageStyle.UTILITY));

		try {
			openProject(toOpen);

		} catch (Exception e) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Something Went Wrong");
			alert.setHeaderText("Could not open the file");
			alert.setContentText("Sorry we were not able to open the selected file, please try again");
			alert.showAndWait();

		}

	}


	@FXML
	protected void handleAboutSuperCommander(ActionEvent event) {

		try {
			URI url = new URI("https://github.com/harrydulaney/commander-excel/");

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

		System.exit(0);
	}

	@FXML
	protected void handleBlankWorkbook(ActionEvent event) {

		FileChooser fchooser = new FileChooser();
		fchooser.setTitle("Name and save your \".xlsx\" excel workbook: ");
		fchooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".xlsx", "*.xlsx"));
		File filePath = fchooser.showSaveDialog(new Stage(StageStyle.UTILITY));

		if (filePath != null) {

			if (filePath.toString().contains(".xlsx")) {
				try {
					FileOutputStream fOs = new FileOutputStream(filePath);
					Workbook wb = new XSSFWorkbook();
					wb.createSheet("Default");
					wb.write(fOs);
					wb.close();

					Alert a = new Alert(AlertType.CONFIRMATION);
					a.setHeaderText("Successful Operation");
					a.setContentText("You created a new workbook with one spreadsheet");
					a.show();

				} catch (Exception e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("I'm sorry something went wrong");
					alert.show();
					e.printStackTrace();
				}

			}
		}
	}

	private void createNewProject() {

		TextInputDialog textDialog = new TextInputDialog();
		textDialog.setHeaderText("Create New SuperCommander Project");
		textDialog.setContentText("Please enter a name for your new project now");
		textDialog.setTitle("Create New Project");
		textDialog.showAndWait();

		String projectName = textDialog.getResult();

		FileChooser chooser = new FileChooser();
		chooser.setTitle("Create New SuperCommander Project");
		chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".xml", "*.xml"));
		File projectFile = chooser.showSaveDialog(new Stage(StageStyle.UTILITY));

		project = new Project(projectName, projectFile);

		MainMenu.setCurrent(project);

	}

	private void saveProject() throws FileNotFoundException {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);

			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			OutputStream output = new FileOutputStream(MainMenu.getCurrent().getProjectFile());
			jaxbMarshaller.marshal(MainMenu.getCurrent(), output);

		} catch (JAXBException e) {
			System.out.print("Something went wrong with JAXB content and marshaller");
			e.printStackTrace();
		}
	}

	private void openProject(File toOpen) {

		JAXBContext jaxbContext;

		try {
			jaxbContext = JAXBContext.newInstance(Project.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			Project project = (Project) jaxbUnmarshaller.unmarshal(toOpen);
			this.project = project;
			MainMenu.setCurrent(project);

			mainmenu.showProject();

		} catch (Exception e) {

			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Cannot open project");
			alert.setHeaderText("Something Went Wrong");
			alert.setContentText("Please double check you are attempting to open the correct file and try again");
			alert.showAndWait();

		}
	}

	@FXML
	public void initialize() {

	}

	public MainMenu getMainmenu() {
		return mainmenu;
	}

	public void setMainmenu(MainMenu mainmenu) {
		this.mainmenu = mainmenu;
	}

}