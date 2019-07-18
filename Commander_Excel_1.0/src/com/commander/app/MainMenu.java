package com.commander.app;

import java.io.IOException;

import com.commander.app.model.CommanderTask;
import com.commander.app.model.Project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Harry Gingles Dulaney IV
 */

public class MainMenu extends Application {

	private static MainMenu mm;
	private BorderPane rootPane;
	private AnchorPane childPane;
	private Stage primaryStage;
	private static Project currentProject;
	ObservableList<Project> projectData = FXCollections.observableArrayList();

	public MainMenu() {
		MainMenu.mm = this;

	}

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Welcome to Super Commander for MS Excel");

		initRootLayerShow();

		try {
			showStartMenu();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void initRootLayerShow() {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainMenu.class.getResource("/com/commander/app/view/RootRoot.fxml"));
			BorderPane rootPane = (BorderPane) loader.load();
			setRootPane(rootPane);
			Scene scene = new Scene(rootPane);
			setUserAgentStylesheet(STYLESHEET_CASPIAN);
			this.primaryStage.setScene(scene);
			getPrimaryStage().show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void showStartMenu() throws IOException {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainMenu.class.getResource("/com/commander/app/view/RootLayout.fxml"));
			AnchorPane childPane = (AnchorPane) loader.load();
			/**
			 * Set modular BorderPane to project logged in com.commander.app.control
			 */
			rootPane.setCenter(childPane);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void showProject() throws IOException {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainMenu.class.getResource("/com/commander/app/view/UserView.fxml"));
			
			

			AnchorPane childPane = (AnchorPane) loader.load();
			setChildPane(childPane);

			rootPane.setCenter(childPane);

			// Give the controller access to the mainMenu application instance.
			ProjectController controller = loader.getController();
			controller.setMainmenu(mm);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void showWorkBookWizard() throws IOException {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainMenu.class.getResource("/com/commander/app/view/workbookWizard.fxml"));
			AnchorPane wizPane = (AnchorPane) loader.load();

			Wizardcontroller controller = loader.getController();
			controller.setMainmenu(mm);
			/**
			 * Fills rootLayout center BorderPane with the task dialog wizard
			 */
			BorderPane bp = (BorderPane) getSubPane(getChildPane().getScene());

			bp.setCenter(wizPane);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Stage getPrimaryStage() {

		return primaryStage;
	}

	public BorderPane getRootPane() {
		return rootPane;

	}

	public Node getSubPane(Scene scene) {

		Node bp = scene.lookup("#subChildPane");
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

	/**
	 * @param rootPane the rootPane to set
	 */
	public void setRootPane(BorderPane rootPane) {
		this.rootPane = rootPane;
	}

	/**
	 * @return the childPane
	 */
	public AnchorPane getChildPane() {
		return childPane;
	}

	/**
	 * @param childPane the childPane to set
	 */
	public void setChildPane(AnchorPane childPane) {
		this.childPane = childPane;
	}

	public static MainMenu getMainMenu() {
		return MainMenu.mm;
	}
}
