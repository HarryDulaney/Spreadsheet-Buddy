package com.commander.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.spreadsheet.SpreadsheetView;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.commander.app.model.ProjectBean;
import com.commander.app.model.SuperCommand;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.codoid.products.fillo.Connection;



/**
 * @author HG Dulaney IV
 */

public class ProjectController implements Initializable {

	private MainMenu mainMenu;
	private static int initCounter = 1;
	private ObservableList<String> cBox = FXCollections.observableArrayList("CSS Link Selectors", "HTML Elements");
	private ObservableList<XSSFCell> ssList = FXCollections.observableArrayList();
	private SuperCommand currentCommand;

	@FXML
	private Label commandN;
	
	@FXML
	private static TableView <ObservableList<XSSFCell>> spreadTableView;
	

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

	public ProjectController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if (ProjectBean.getInstance().getSooperCommands().isEmpty()) {
			label1.setVisible(true);
			arrowImage.setVisible(true);
		} else {
			label1.setVisible(false);
			arrowImage.setVisible(false);

		}

		ObservableList<SuperCommand> commandList = FXCollections
				.observableArrayList(ProjectBean.getInstance().getSooperCommands());

		commandName.setCellValueFactory(new PropertyValueFactory<SuperCommand, String>("commandName"));

		tableView.setItems(commandList);

		label1.setText(ProjectBean.getInstance().getName());

		comboBox.setItems(cBox);

		comboBox.setVisible(false);

		comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> selected, String oldItem, String newItem) {
				if (newItem != null) {

					switch (newItem) {

					case "CSS Link Selectors":
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(
								MainMenu.class.getResource("/com/commander/app/utils/Scrapper/WebScrapeLinkView.fxml"));
						AnchorPane anchorpane = new AnchorPane();
						try {
							anchorpane = (AnchorPane) loader.load();
						} catch (IOException e) {
							e.printStackTrace();
						}

						BorderPane borderpane = (BorderPane) getNestedPane(MainMenu.getRootPane().getScene());
						borderpane.setCenter(anchorpane);
						break;

					case "HTML Elements":
						FXMLLoader loadr = new FXMLLoader();
						loadr.setLocation(
								MainMenu.class.getResource("/com/commander/app/utils/Scrapper/WebScrapeDefine.fxml"));
						AnchorPane anchorp = new AnchorPane();
						try {
							anchorpane = (AnchorPane) loadr.load();
						} catch (IOException e) {
							e.printStackTrace();
						}

						BorderPane bpane = (BorderPane) getNestedPane(MainMenu.getRootPane().getScene());
						bpane.setCenter(anchorp);
						break;

					}
				}
			}
		});

		System.out.println("BuildPaneController initCounter value is: " + initCounter);

		if (initCounter == 1) {

		}
		if (initCounter == 2) {

		}

		initCounter++;
		
			

	}

	@FXML
	protected void handleNewSuperCommand(ActionEvent event) throws IOException {

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("SuperCommand Builder");
		dialog.setHeaderText("Defining the attributes of the new SuperCommand");
		dialog.setContentText("What would you you like to name the new SuperCommand, this will be "
				+ "how you recall the command in the future.");
		dialog.showAndWait();

		currentCommand = new SuperCommand();

		currentCommand.setSuperCommandName(dialog.getResult());

		ProjectBean.getInstance().addCommand(currentCommand);

		label2.setVisible(false);
		arrowImage.setVisible(false);
		sidePaneVbox.setVisible(true);

	}

	@FXML
	protected void handleOpenFile(ActionEvent event) throws IOException {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose File To pull data from");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".csv", "*.csv"),
				new FileChooser.ExtensionFilter(".xlsx", "*.xlsx"));
		File userFile = fileChooser.showOpenDialog(new Stage(StageStyle.TRANSPARENT));

		currentCommand.setFileIn(userFile);

		try {
			MenuController.saveProject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		commandN.setText(currentCommand.getSuperCommandName());
		commandN.setVisible(true);

	}

	@FXML
	protected void handleOpenScrapper(ActionEvent event) throws IOException {

		comboBox.setVisible(true);

	}

	public static int getInitCounter() {
		return initCounter;
	}

	public static void setInitCounter(int initCounter) {
		ProjectController.initCounter = initCounter;
	}

	public void setMainmenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;

	}

	public Node getNestedPane(Scene scene) {
		Node bp = scene.lookup("#bPane");
		return bp;
	}	
	
	
	public static void showSpreadTableView() {
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose File To pull data from");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".csv", "*.csv"),
				new FileChooser.ExtensionFilter(".xlsx", "*.xlsx"));
		File userFile = fileChooser.showOpenDialog(new Stage(StageStyle.TRANSPARENT));
		FileInputStream fis = null;
		Connection connection = null;
		
		try {
			 fis = new FileInputStream(userFile);
		
					
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Fillo fillo = new Fillo();
		try {
			 connection = fillo.getConnection(userFile.getAbsolutePath());
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
		spreadTableView.setVisible(true);
		
		
		
	}

}
