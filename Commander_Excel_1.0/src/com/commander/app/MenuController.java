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

import com.codoid.products.exception.FilloException;
import com.commander.app.model.Project;
import com.commander.app.model.ProjectBean;
import com.commander.app.model.SuperCommand;
import com.commander.app.model.tasks.DuplicateChecker;
import com.gembox.spreadsheet.ExcelFile;
import com.gembox.spreadsheet.ExcelWorksheet;
import com.gembox.spreadsheet.SpreadsheetInfo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



/**
 * The Controller for the Main Menu
 *
 * @author HG Dulaney IV
 */

public class MenuController {

	private MainMenu mainmenu;

	@FXML
	private Menu buildMenu;

	@FXML
	private MenuItem projectSaveMenuItem;

	/**
	 * Handles saving project.
	 *
	 * @param event the event
	 */
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
			alrt.setContentText("You must first open or create a new project in order to perform this action");
			alrt.show();

		}

	}

	/**
	 * Handles creating new project.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	protected void handleNewProject(ActionEvent event) throws IOException {

		createNewProject();

		if (ProjectBean.getInstance() != null) {

			saveProject();

			buildMenu.setDisable(false);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("You're new project named: " + ProjectBean.getInstance().getName() + " was saved at: "
					+ ProjectBean.getInstance().getProjectFile().toString());
			alert.showAndWait();

			try {
				mainmenu.showProject();
				projectSaveMenuItem.setDisable(false);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Please try again to create a new project");
			alert.showAndWait();
		}

	}

	/**
	 * Handles consolidate workbooks.
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleConsolidateWorkbooks(ActionEvent event) {

	}
	
	/**
	 * 	This is an individual task a user can run on a .xlsx spreadsheet file
	 *
	 * @param excelfile 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void saveToWorkbook(ExcelFile excelfile) throws IOException {

		FileChooser fchooser = new FileChooser();
		fchooser.setTitle("Save your new workbook: ");
		fchooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".xlsx", "*.xlsx"));
		fchooser.setInitialFileName("New_Excel_Workbook");
		File filePath = fchooser.showSaveDialog(new Stage(StageStyle.UTILITY));

		if (filePath != null) {

			excelfile.save(filePath.getAbsolutePath());

		}
	}

	/**
	 * Handles comparing two .xlsx spreadsheets for duplicate entries. (This is a individual task)
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleCompareDuplicates(ActionEvent event) {
		
		SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");

		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose the file containing the first spreadsheet");
		chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".xlsx", "*.xlsx"));
		File fileOne = chooser.showOpenDialog(new Stage(StageStyle.UTILITY));

		if (fileOne != null) {

			FileChooser chooser2 = new FileChooser();
			chooser2.setTitle("Choose the file containing the second spreadsheet to compare to the first.");
			chooser2.getExtensionFilters().add(new FileChooser.ExtensionFilter(".xlsx", "*.xlsx"));
			File fileTwo = chooser2.showOpenDialog(new Stage(StageStyle.UTILITY));

			if (fileTwo != null) {

				TextInputDialog dialog = new TextInputDialog();
				dialog.setTitle("Compare For Duplicates Task ");
				dialog.setHeaderText("Task data requested...");
				dialog.setContentText("Please enter the header for the column to check for duplicate values "
						+ "For Example: Email or Client ID Number: ");
				dialog.showAndWait();

				if (dialog.getResult() != null) {
					
					DuplicateChecker dupCheck = new DuplicateChecker(fileOne, fileTwo, dialog.getResult());

					ArrayList<String> dupes = null;
					try {
						dupes = dupCheck.checkForDuplicates();
					} catch (FilloException e) {
						e.printStackTrace();
					}
					ExcelFile excelfile = new ExcelFile();

					ExcelWorksheet worksheet = excelfile.addWorksheet("Duplicates");

					worksheet.getCell(0, 0).setValue("Duplicate" + dupCheck.getColumnToCheck() + " 's");

					for (int i = 0, j = 1; i < dupes.size(); i++, j++) {

						worksheet.getCell(j, 0).setValue(dupes.get(i));

					}
					
					try {
						saveToWorkbook(excelfile);
					} catch (IOException e) {
						System.out.println("Problem running saveToWorkbook.");
						e.printStackTrace();
					}
					

				}
			}
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

		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose the project you would like to open");
		chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".xml", "*.xml"));
		File toOpen = chooser.showOpenDialog(new Stage(StageStyle.UTILITY));

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
	 * Handles the task for filtering user specified entries 
	 * from a CSV spreadsheet. (Individual Task)
	 *
	 * @param event the event
	 * @throws Exception the exception
	 */
	@FXML
	protected void handleCSVFilter(ActionEvent event) throws Exception {

		Stage stage = new Stage();

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainMenu.class.getResource("/com/commander/app/view/CSVfilterWiz.fxml"));
		Parent root = (Parent) loader.load();

		CSVFilterController controller = loader.getController();
		controller.setMainMenu(mainmenu);
		Scene scene = new Scene(root, 550, 600);

		scene.getStylesheets().add("/com/commander/app/view/CommanderStyle1.css");

		stage.setScene(scene);
		if (controller.getFile() != null) {
			stage.show();
		}
	}

	/**
	 * Handles the about super commander link which re-directs to SuperCommander  GitHub page.
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

		if (ProjectBean.getInstance() == null) {
			Platform.exit();
		} else {

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
				if (ProjectBean.getInstance() != null) {
					ProjectBean.getInstance().closeProject();
				}

				Platform.exit();

			} else if (result == ButtonType.NO) {

				// DO nothing.... GO Back
			}
		}
	}

	/**
	 * Handle launch web scrapper.
	 *
	 * @throws Exception the exception
	 */
	@FXML
	protected void handleLaunchWebScrapper() throws Exception {

		Stage stage = new Stage();

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainMenu.class.getResource("/com/commander/app/view/WebScrapeDefine.fxml"));
		Parent root = (Parent) loader.load();

		ProjectController.setInitCounter(1);

		Scene scene = new Scene(root, 550, 600);
		scene.getStylesheets().add("/com/commander/app/view/CommanderStyle1.css");
		stage.setScene(scene);
		stage.show();

	}

	/**
	 * Handle open spreadsheet.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	protected void handleOpenSpreadsheet(ActionEvent event) throws IOException {

		FXMLLoader loadSheet = new FXMLLoader();
		loadSheet.setLocation(MainMenu.class.getResource("/com/commander/app/view/GemBoxView.fxml"));
		AnchorPane anPane = (AnchorPane) loadSheet.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(anPane));

		stage.show();

	}

	/**
	 * Creates the new project.
	 */
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

		if (projectFile != null) {

			ArrayList<SuperCommand> commands = new ArrayList<>();

			ProjectBean.getInstance(projectName, projectFile, commands);
		}
	}

	/**
	 * Uses JAXB to convert the project instance to XML format for storage. 
	 *
	 * @throws FileNotFoundException the file not found exception
	 */
	public static void saveProject() throws FileNotFoundException {

		if (ProjectBean.getInstance() != null) {

			try {

				JAXBContext context = JAXBContext.newInstance(Project.class);

				Marshaller jaxbMarshaller = context.createMarshaller();

				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

				Project project = new Project(ProjectBean.getInstance().getName(),
						ProjectBean.getInstance().getProjectFile(), ProjectBean.getInstance().getSooperCommands());

				OutputStream output = new FileOutputStream(project.getProjectFile());
				jaxbMarshaller.marshal(project, output);

			} catch (JAXBException e) {
				e.printStackTrace();

			}
		}
	}

	/**
	 * Converts user and project info back from XML format.
	 *
	 * @param file the file
	 */
	public void openProject(File file) {

		JAXBContext context;

		try {
			context = JAXBContext.newInstance(Project.class);
			Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();

			Project project = (Project) jaxbUnmarshaller.unmarshal(file);

			ProjectBean.getInstance(project.getName(), project.getProjectFile(), project.getSooperCommands());

			mainmenu.showProject();

		} catch (Exception e) {

			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Please double check you are attempting to open the correct file and try again");
			alert.showAndWait();

		}
	}

	/**s
	 * Initialize.
	 */
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