package com.commander.app;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import com.commander.app.model.Main;
import com.commander.app.model.ProjectBean;
import com.commander.app.model.ProjectBean.JsonAccessObject;
import com.commander.app.model.utils.PHelper;
import com.commander.app.model.SuperCommand;

/**
 * The Controller for the Main Menu
 *
 * @author HG Dulaney IV
 */

public class DropDownMenuController implements Initializable {

	@FXML
	private Menu buildMenu;

	@FXML
	private MenuItem projectSaveMenuItem;

	@FXML
	private BorderPane borderpane;

	@FXML
	private ComboBox<String> comboBox;

	@FXML
	private VBox sidePaneVbox;

	@FXML
	private Label label1;

	@FXML
	private Label label2;

	@FXML
	private ImageView arrowImage;

	@FXML
	private TableView<SuperCommand> tableView;

	@FXML
	private TableColumn<SuperCommand, String> commandName;

	@FXML
	private Label commandN;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	/**
	 * Handles event action for the "Save Project" button on the Drop-down menu
	 *
	 * @throws FileNotFoundException the file not found exception
	 *
	 * @param event button push
	 */
	@FXML
	protected void handleSaveProject(ActionEvent event) {

		try {
			saveProject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.print("Error on saveProject() @DropDownMenuController.handleSaveProject()");

		}

	}

	protected void saveProject() throws IOException {

		if (ProjectBean.getInstance().getDirectoryFile() != null) {

			JsonAccessObject.writeProjectJson();

		} else {
			PHelper.showErrorAlert("No directory file location has been set for this project!");

		}

	}

	/**
	 * Handles creating new project.
	 * 
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	protected void handleNewProject(ActionEvent event) {

		if (createNewProject()) {
			try {
				saveProject();

			} catch (IOException ioe) {
				ioe.printStackTrace();
				System.out.print("Error on saveProject() @DropDownMenuController.handleNewProject()");
			}

			Alert alert = new Alert(AlertType.INFORMATION);

			alert.setContentText("You succesfully created a new project named: \n" + ProjectBean.getInstance().getName()
					+ "\n with project files located at: \n"
					+ ProjectBean.getInstance().getDirectoryFile().getAbsolutePath());
			alert.showAndWait();

			projectSaveMenuItem.setDisable(false);

		} else {
			PHelper.showErrorAlert("Something went wrong. Please try creating a project folder "
					+ "in a location that doesn't require admin permissions.");

		}

	}

	/**
	 * Handles creating the new project.
	 */
	protected boolean createNewProject() {

		String name = PHelper.showInputPrompt("First create a new project!",
				"Assign a unique name to your project now: ", "Name your project.");
		if (name != null) {
			ProjectBean.getInstance().setName(name);
			System.out.println(ProjectBean.getInstance().getName());

			/*
			 * PHelper.showInfoAlert( "Next, please select a folder where we will create " +
			 * "your project directory.\n The purpose of " +
			 * "a dedicated directory is that it gives Super-Commander a " +
			 * "central location from which to read and write Excel files. ", true);
			 */

			DirectoryChooser dc = new DirectoryChooser();
			dc.setTitle("Where would you like to save your project files?");
			File projectFile = dc.showDialog(Main.getMain().getStage());

			if (projectFile != null) {
				String prep = projectFile.getAbsolutePath() + "\\" + ProjectBean.getInstance().getName();
				File readyFile = new File(prep);

				boolean wroteDir = readyFile.mkdir();
				if (wroteDir) {
					ProjectBean.getInstance().setDirectoryFile(readyFile);
					return true;

				}

			}
		}
		return false;

	}

	/**
	 * Handles the about super commander link which re-directs to SuperCommander
	 * GitHub page.
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleAboutSuperCommander(ActionEvent event) {

		try {
			URI url = new URI("https://github.com/Nothingrhymeswithorange/SuperCommander_1.0/blob/master/README.md");

			Desktop.getDesktop().browse(url);

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("We were unable to open the weblink to the about me document you requested");
			alert.show();
		}

	}

	/**
	 * Handles the command to exit the application.
	 *
	 * @param event the event
	 */
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
			if (ProjectBean.getInstance().getName() != null && ProjectBean.getInstance().getDirectoryFile() != null) {

				try {
					saveProject();

				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}

			ProjectBean.getInstance().closeProject();
			Platform.exit();

		} else if (result == ButtonType.NO) {

			// DO nothing.... GO Back
		}
	}

	/**
	 * Converts user and project info back from XML format.
	 *
	 * @param file the file
	 */
	public void openProject(File file) {

		if (JsonAccessObject.readProjectJson(file)) {

		} else {
			PHelper.showErrorAlert("Please double check you are attempting to open the correct file and try again");
		}

	}

	/**
	 * Handles opening a saved user project.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	protected void handleOpenProject(ActionEvent event) throws IOException {

		File toOpen = PHelper.showFilePrompt("Choose the project you would like to open", ",json");

		if (toOpen != null) {

			try {
				openProject(toOpen);
				projectSaveMenuItem.setDisable(false);

			} catch (Exception e) {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Sorry we were not able to open the selected file, please try again");
				alert.showAndWait();

			}

		}
	}

	/**
	 * Handles opening a .xslx or .csv file in a spreadsheet editable view.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	protected void handleOpenSpreadsheet(ActionEvent event) throws IOException {

	}

	@FXML
	protected void handleOpenFile(ActionEvent event) throws IOException {

		File userFile = PHelper.showFilePrompt("Choose which file you would like to oepn", ".csv", ".xlsx");
		/*
		 * currentCommand.setFileIn(userFile);
		 * 
		 * DropDownMenuController.saveProject();
		 * 
		 * commandN.setText(currentCommand.getName()); commandN.setVisible(true);
		 */

	}

	public BorderPane getBp() {
		return borderpane;
	}

	/*
	 * Below are independent spreadsheet tasks. These will be taken out and put in
	 * there own program as they do not let the user define the operations
	 * explicitly
	 */
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Handles consolidate workbooks.
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleConsolidateWorkbooks(ActionEvent event) {

	}

	

	/**
	 * Handle launch web scraper.
	 *
	 * @throws Exception the exception
	 */
	@FXML
	protected void handleLaunchWebScrapper() throws Exception {

		Stage stage = new Stage();

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/com/commander/app/view/WebScrapeDefine.fxml"));
		Parent root = (Parent) loader.load();

		Scene scene = new Scene(root, 550, 600);
		scene.getStylesheets().add("/com/commander/app/view/CommanderStyle1.css");
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	protected void handleOpenScrapper(ActionEvent event) throws IOException {

		comboBox.setVisible(true);

	}

}