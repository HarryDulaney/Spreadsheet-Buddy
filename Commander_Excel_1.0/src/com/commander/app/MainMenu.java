package com.commander.app;

import java.io.IOException;


import com.commander.app.model.Project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author H.G. Dulaney IV
 */

public class MainMenu extends Application {

	private static AnchorPane rootPane;

	private Stage primaryStage;
	
	private static MainMenu mm;

	
	public MainMenu() {

		mm = this;

	}
	
	/**
	 * Applications main entry point.
	 *
	 * @param primaryStage the primary stage
	 */
	@Override 
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Welcome to Super Commander for MS Excel");

		initRootLayerShow();

	}
	
	/**
	 * Initialize the root frame for the GUI. 
	 */
	public void initRootLayerShow() {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainMenu.class.getResource("/com/commander/app/view/RootRoot.fxml"));
			rootPane = loader.load();

			MenuController controller = loader.getController();
			controller.setMainmenu(this);

			Scene scene = new Scene(rootPane);
			scene.getStylesheets().add("/com/commander/app/view/Style/CommanderStyle1.css");

			this.primaryStage.setScene(scene);
			getPrimaryStage().show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Initializes the users project which fills in the root frame. 
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void showProject() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainMenu.class.getResource("/com/commander/app/view/UserView.fxml"));
		AnchorPane anchorpane = (AnchorPane) loader.load();

		ProjectController controller = loader.getController();
		controller.setMainmenu(this);

		BorderPane borderpane = (BorderPane) getNestedPane(rootPane.getScene());
		borderpane.setCenter(anchorpane);

	}

	/**
	 * Gets the primary stage.
	 *
	 * @return the primary stage
	 */
	public Stage getPrimaryStage() {

		return primaryStage;
	}

	/**
	 * Gets the sub pane two.
	 *
	 * @param scene the scene
	 * @return the sub pane two
	 */
	public Node getSubPaneTwo(Scene scene) {

		Node bp = scene.lookup("#startBlank");
		return bp;

	}

	/**
	 * Gets the nested pane.
	 *
	 * @param scene the scene
	 * @return the nested pane
	 */
	public Node getNestedPane(Scene scene) {
		Node bp = scene.lookup("#borderpane");
		return bp;
	}

	/**
	 * Gets the main menu.
	 *
	 * @return the main menu
	 */
	public static MainMenu getMainMenu() {

		return MainMenu.mm;
	}

	/**
	 * Gets the root pane.
	 *
	 * @return the root pane
	 */
	public static AnchorPane getRootPane() {
		return rootPane;
	}

}
