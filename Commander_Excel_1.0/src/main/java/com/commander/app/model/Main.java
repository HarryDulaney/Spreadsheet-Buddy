package main.java.com.commander.app.model;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author HGDIV
 */

public class Main extends Application {

	private Stage primaryStage;

	private final static String ROOT_MENU = "/main/java/com/commander/app/view/StartMenu.fxml";

	/**
	 * Application main entry point.
	 *
	 * @param primaryStage the primary stage
	 */
	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Welcome to Super Commander for MS Excel");

		try {
			initStartFrame();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void initStartFrame() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(Main.class.getResource(ROOT_MENU));

		AnchorPane pane = (AnchorPane) loader.load();		

		Scene scene = new Scene(pane);

		primaryStage.setScene(scene);
		primaryStage.show();

	}

	/**
	 * Initialize the root frame which contains the Drop Down MenuBar for the GUI.
	 */

	public static void main(String[] args) {
		launch(args);
	}

}