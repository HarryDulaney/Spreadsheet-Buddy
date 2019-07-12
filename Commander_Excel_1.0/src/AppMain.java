

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AppMain extends Application {

	private Stage primaryStage;
	private BorderPane rootPane;

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("SuperCommader_1.0 MS_Excel");

		initRootLayout();

		showMainMenu();
	}

	public void initRootLayout() {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppMain.class.getResource("main/RootLayout.fxml"));
			rootPane = (BorderPane) loader.load();

			primaryStage.setScene(new Scene(rootPane));
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showMainMenu() {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppMain.class.getResource("main/ProjectView"));
			AnchorPane projectView = (AnchorPane) loader.load();

			rootPane.setCenter(projectView);

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	public Stage getPrimaryStage() {

		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
