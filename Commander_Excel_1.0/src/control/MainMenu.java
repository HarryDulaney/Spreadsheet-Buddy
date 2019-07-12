package control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * @author Harry Gingles Dulaney IV
 */

public class MainMenu extends Application {

	private final static String currentMach = System.getProperty("user.name");

	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Welcome to Super Commander for MS Excel");

		showMainMenu();

	}

	public void showMainMenu() {

		try {

			Parent root = FXMLLoader.load(getClass().getResource("/view/RootLayout.fxml"));
			this.primaryStage.setScene(new Scene(root));
			getPrimaryStage().show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);

	}

	public Stage getPrimaryStage() {

		return primaryStage;
	}

	/**
	 * @return the currentMach
	 */
	public static String getCurrentMach() {
		return currentMach;
	}

}
