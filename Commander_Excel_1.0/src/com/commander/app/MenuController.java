package com.commander.app;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.dialog.ExceptionDialog;

import com.commander.app.model.Command;
import com.commander.app.model.Project;
import com.commander.app.model.ProjectBean;

import javafx.fxml.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.security.jca.GetInstance;

/**
 * @author HG Dulaney IV
 */
/**
 * This controller still needs implementations for the event handlers that bind
 * to the root menu heading task bar ie.... Save ProjectXml, Close ProjectXml
 */
public class MenuController {
	
	
	private ProjectBean Current_Project;
	private MainMenu mainmenu;

	@FXML
	protected void handleSaveProject(ActionEvent event) {

		if (ProjectBean.getInstance() != null) {

			try {
				saveProject();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		} else {
			Alert alrt = new Alert(AlertType.WARNING);
			alrt.setHeaderText("Hey you can't do that!");
			alrt.setContentText("You must first open or create a new project in order to perform this action");
			alrt.show();

		}

	}

	@FXML
	protected void handleNewProject(ActionEvent event) throws IOException {

		createNewProject();

		if (ProjectBean.getInstance() != null) {

			saveProject();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Create New Project");
			alert.setHeaderText("Success!");
			alert.setContentText("You're new project named: " + Current_Project.getName() + " was saved at: "
					+ Current_Project.getProjectFile().toString());
			alert.showAndWait();
			
			try {
				mainmenu.showProject();
			} catch (Exception e) {
				e.printStackTrace();
			}

		

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

		if (toOpen != null) {

			try {
				openProject(toOpen);

			} catch (Exception e) {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Something Went Wrong");
				alert.setHeaderText("Could not open the file you selected");
				alert.setContentText("Sorry we were not able to open the selected file, please try again");
				alert.showAndWait();

			}

		}
	}

	@FXML
	protected void handleCSVFilter(ActionEvent event) throws Exception {

		Stage stage = new Stage();

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainMenu.class.getResource("/com/commander/app/view/CSVfilterWiz.fxml"));
		Parent root = (Parent) loader.load();

		CSVFilterController controller = loader.getController();
		controller.setMainMenu(mainmenu);
		Scene scene = new Scene(root, 550, 600);
		// scene.getStylesheets().add("com/commander/app/view/style/ThemeOne.css");

		stage.setScene(scene);
		if (controller.getFile() != null) {
			stage.show();
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

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm Action");
		alert.setHeaderText("Are you sure you want to do that?");
		alert.setContentText(
				"Are you sure you want to exit the project build? Hit YES if you wish to exit, your project will save automa"
						+ "tically. Hit NO if you wish to continue working.");

		alert.getButtonTypes().removeAll(ButtonType.CANCEL, ButtonType.OK);
		alert.getButtonTypes().add(ButtonType.YES);
		alert.getButtonTypes().add(ButtonType.NO);
		alert.showAndWait();

		ButtonType result = alert.getResult();
		if (result == ButtonType.YES) {

			try {
				saveProject();
			} catch (FileNotFoundException e) {
				System.out.println("Error Saving the Project in:   MenuController.handleExitCommander");
				e.printStackTrace();
			}

			ProjectBean.getInstance().closeProject();
			Platform.exit();

		} else if (result == ButtonType.NO) {

			// DO nothing.... GO Back
		}

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

	public void createNewProject() {

		TextInputDialog textDialog = new TextInputDialog();
		textDialog.setHeaderText("Create New SuperCommander Project");
		textDialog.setContentText("Please enter a name for your new project now");
		textDialog.setTitle("Create New Project");
		textDialog.showAndWait();

		String projectName = textDialog.getResult();

		FileChooser chooser = new FileChooser();
		chooser.setTitle("Create New SuperCommander Project");
		chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".xml", "*.xml"));
		chooser.setInitialFileName(projectName);
		File projectFile = chooser.showSaveDialog(new Stage(StageStyle.UTILITY));
		
		ArrayList<Command> commands = new ArrayList<>();
		commands.add(new Command());

	    Current_Project = ProjectBean.getInstance(projectName, projectFile, commands);

	}

	public void saveProject() throws FileNotFoundException {

		if (Current_Project != null) {

			try {

				JAXBContext context = JAXBContext.newInstance(Project.class);

				Marshaller jaxbMarshaller = context.createMarshaller();

				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

				Project project = new Project(Current_Project.getName(), Current_Project.getProjectFile(),
						Current_Project.getSooperCommands());

				OutputStream output = new FileOutputStream(project.getProjectFile());
				jaxbMarshaller.marshal(project, output);

			} catch (JAXBException e) {
				e.printStackTrace();

			}
		}
	}

	public void openProject(File toOpen) {

		JAXBContext context;

		try {
			context = JAXBContext.newInstance(Project.class);
			Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();

			Project project = (Project) jaxbUnmarshaller.unmarshal(toOpen);

			ProjectBean.getInstance(project.getName(), project.getProjectFile(),project.getSooperCommands());

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