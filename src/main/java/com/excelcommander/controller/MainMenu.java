package com.excelcommander.controller;

import java.io.IOException;

import com.excelcommander.model.Project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author HGDIV 
 */

public class MainMenu extends Application {

	private AnchorPane rootPane;
	private Stage primaryStage;
	private static Project current;
	private static MainMenu mm;

	/**
	 * TODO: Replace with DI 
	 */

	public MainMenu() {

		mm = this;

	}

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Welcome to Super Commander for MS Excel");

		initRootLayerShow();

		try {

			showStart();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void initRootLayerShow() {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainMenu.class.getResource("/fxml/RootRoot.fxml"));
			rootPane = loader.load();

			Scene scene = new Scene(rootPane, 800, 600);

			// scene.getStylesheets().add("/view/ThemeOne.css");

			this.primaryStage.setScene(scene);
			getPrimaryStage().show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void showStart() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainMenu.class.getResource("/fxml/StartBlankView.fxml"));
		AnchorPane anchorpane = (AnchorPane) loader.load();
		MenuController controller = loader.getController();
		controller.setMainmenu(this);

		BorderPane borderpane = (BorderPane) getSubPaneOne(rootPane.getScene());
		borderpane.setCenter(anchorpane);

	}

	public void showProject() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainMenu.class.getResource("/fxml/UserView.fxml"));
		AnchorPane anchorpane = (AnchorPane) loader.load();

		ProjectController controller = loader.getController();
		controller.setMainmenu(this);

		BorderPane borderpane = (BorderPane) getSubPaneOne(rootPane.getScene());
		borderpane.setCenter(anchorpane);

	}

	public Stage getPrimaryStage() {

		return primaryStage;
	}

	public Node getSubPaneTwo(Scene scene) {

		Node bp = scene.lookup("#startBlank");
		return bp;

	}

	public Node getSubPaneOne(Scene scene) {
		Node bp = scene.lookup("#borderpane");
		return bp;
	}

	public static Project getCurrent() {
		return current;
	}

	public static void setCurrent(Project current) {
		MainMenu.current = current;
	}

	public static MainMenu getMainMenu() {

		return MainMenu.mm;
	}

}
