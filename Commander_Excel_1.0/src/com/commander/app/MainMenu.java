package com.commander.app;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import com.commander.app.model.Project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * @author HG Dulaney IV
 */

public class MainMenu extends Application {

	private static MainMenu mm;
	private AnchorPane rootPane;
	private Stage primaryStage;
	private static Project currentProject;
	private Stage wizardStage;
	ObservableList<Project> projectData = FXCollections.observableArrayList();
	ObservableList<String> taskList = FXCollections.observableArrayList();

	public MainMenu() {
		MainMenu.mm = this;

	}

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Welcome to Super Commander for MS Excel");

		initRootLayerShow();

		try {
			showProject();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void initRootLayerShow() {

		File filePath = new File("C:\\Desktop");
		Project project = new Project(System.getProperty("user.name"), "TestProject", filePath);

		setCurrentProject(project);

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainMenu.class.getResource("/com/commander/app/view/RootRoot.fxml"));
			rootPane = loader.load();

			Scene scene = new Scene(rootPane, 800, 600);
			this.primaryStage.setScene(scene);
			getPrimaryStage().show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void showProject() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainMenu.class.getResource("/com/commander/app/view/UserView.fxml"));
		AnchorPane anchorpane = (AnchorPane) loader.load();

		ProjectController controller = loader.getController();
		controller.setMainmenu(mm);

		BorderPane borderpane = (BorderPane) getSubPaneOne(rootPane.getScene());
		borderpane.setCenter(anchorpane);

	}

	public Stage getPrimaryStage() {

		return primaryStage;
	}

	// public BorderPane getRootPane() {
	// return this.rootPane;

	// }

	public Node getSubPaneTwo(Scene scene) {

		Node bp = scene.lookup("#subChildPane");
		return bp;

	}

	public Node getSubPaneOne(Scene scene) {

		Node bp = scene.lookup("#borderpane");
		return bp;
	}

	public ObservableList<Project> getProjectData() {
		return projectData;
	}

	/**
	 * @return the currentProject
	 */
	public static Project getCurrentProject() {
		return currentProject;
	}

	/**
	 * @param currentProject the currentProject to set
	 */
	public static void setCurrentProject(Project currentProject) {
		MainMenu.currentProject = currentProject;
	}

	public static MainMenu getMainMenu() {
		return MainMenu.mm;
	}

	public Stage getWizardStage() {
		return wizardStage;
	}

}
