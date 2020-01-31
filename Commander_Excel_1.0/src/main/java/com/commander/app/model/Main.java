package main.java.com.commander.app.model;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.com.commander.app.model.utils.PHelper;

/**
 * @author HGDIV
 */

public class Main extends Application {
	
	private Stage primaryStage;

	private final static String ROOT_MENU = "/main/java/com/commander/app/view/StartMenu.fxml";

	private static Main main;

	/**
	 * Application main entry point.
	 *
	 * @param primaryStage the primary stage
	 */
	@Override
	public void start(Stage primaryStage) {
		main = this;
		
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Welcome to Super Commander for MS Excel");

		try {
			initStartFrame();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Initialize the root frame and tool-bar
	 * Initialize its controller 
	 * 
	 */
	public void initStartFrame() throws IOException {

		FXMLLoader loader = new FXMLLoader(Main.class.getResource(ROOT_MENU));
	
		AnchorPane pane = (AnchorPane) loader.load();

		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.show();
		
//		PHelper.showInfoAlert("Welcome! Start by creating a new project from the File menu", true);
	
	}


	public static void main(String[] args) {
		launch(args);
	}
	public Stage getStage() {
		return primaryStage;
	}
	public static Main getMain() {
		return main;
	}
}