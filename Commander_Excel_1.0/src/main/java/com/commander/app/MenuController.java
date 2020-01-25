package main.java.com.commander.app;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedList;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.ObjectMapper;

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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.com.commander.app.model.ExcelAccessObject;
import main.java.com.commander.app.model.ProjectBean;
import main.java.com.commander.app.model.ProjectBean.DataAccessObject;
import main.java.com.commander.app.model.SuperCommand;
import main.java.com.commander.app.model.utils.DuplicateChecker;

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
	 * Handles event action for the "Save Project" button on the Drop-down menu
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleSaveProject(ActionEvent event) {

		saveProject();

		PHelper.showWarningAlert("You must first open or create a new project in order to perform this action");

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
					+ ProjectBean.getInstance().getDirectoryLoc().toString());
			alert.showAndWait();

			try {
				mainmenu.showProjectWindow();
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
	 * Creates the new project.
	 */
	public void createNewProject() {

		String projectName = PHelper.showInputPrompt("Creating New Project",
				"Please enter a name for your new project now", "Create New Project");

		File projectFile = PHelper.showFilePrompt("Create New SuperCommander Project", ".json");

		if (projectFile != null) {

			LinkedList<SuperCommand> commands = new LinkedList<>();
			SuperCommand sc = new SuperCommand();
			sc.setName("Start By Creating or trying some super-commands");

			ProjectBean.getInstance().setSooperCommands(commands);
			ProjectBean.getInstance().setName(projectName);
			ProjectBean.getInstance().setDirectoryPath(projectFile);
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
	 * Handles comparing two .xlsx spreadsheets for duplicate entries. (This is a
	 * individual task)
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleCompareDuplicates(ActionEvent event) {

		File fileOne = PHelper.showFilePrompt("Choose the file containing the first spreadsheet", ".xlsx");

		if (fileOne != null) {

			File fileTwo = PHelper.showFilePrompt(
					"Choose the file containing the second spreadsheet to compare to the first.", ".xlsx");
			if (fileTwo != null) {

				String colName = PHelper.showInputPrompt("Task data requested...",
						"Please enter the header for the column to check for duplicate values "
								+ "For Example: Email or Client ID Number: ",
						"Compare For Duplicates Task ");

				if (colName != null) {

					DuplicateChecker dupCheck = new DuplicateChecker(fileOne, fileTwo, colName);
					ArrayList<String> dupes = null;

					try {
						dupes = dupCheck.checkForDuplicates();

					} catch (Exception e) {
						e.printStackTrace();
					}

					Workbook workbook = new XSSFWorkbook();

					Sheet sheet = workbook.createSheet("Duplicates");

					Row headerRow = sheet.createRow(0);

					headerRow.createCell(0).setCellValue("Duplicate " + dupCheck.getColumnToCheck() + " 's");

					int j = 1;
					for (int i = 0; i < dupes.size(); i++) {

						Row row = sheet.createRow(j++);

						row.createCell(0).setCellValue(dupes.get(i));
					}

					sheet.autoSizeColumn(0);

					ExcelAccessObject.saveWorkbook(workbook);

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
	 * Handles the task for filtering user specified entries from a CSV spreadsheet.
	 * (Individual Task)
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

			saveProject();

			
			ProjectBean.getInstance().closeProject();
			

			Platform.exit();

		} else if (result == ButtonType.NO) {

			// DO nothing.... GO Back
		}
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

		ProjectController.showSpreadTableView();

	}

	/**
	 * Saves the
	 *
	 * @throws FileNotFoundException the file not found exception
	 */
	public static void saveProject() {

		if (ProjectBean.getInstance().getDirectoryLoc() != null) {

			DataAccessObject.writeProjectJson(ProjectBean.getInstance().getDirectoryLoc(), ProjectBean.getInstance());

		} else {
			DataAccessObject.writeProjectJson(new File(System.getProperty("java.io.tmpdir")),
					ProjectBean.getInstance());

		}

	}

	/**
	 * Converts user and project info back from XML format.
	 *
	 * @param file the file
	 */
	public void openProject(File file) {

		if(DataAccessObject.readProjectJson(file)) {
			
		}else {
			PHelper.showErrorAlert("Please double check you are attempting to open the correct file and try again");
		}
		
		
	}
	
	 

	/**
	 * s Initialize.
	 */
	@FXML
	public void initialize() {

		try {
			PropertyUtils.getProperty(MainMenu.class, "mainmenu");
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public MainMenu getMainmenu() {
		return mainmenu;
	}

	public void setMainmenu(MainMenu mainmenu) {
		this.mainmenu = mainmenu;
	}

	

	}

