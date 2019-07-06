package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * @author Harry Dulaney
 */

public class MainMenu extends Application {
	
	public static void main(String[] args) {
		launch(args);
		
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		
		stage.setTitle("Welcome to Super Commander for MS Excel");
		
		stage.setScene(new Scene(root, 800, 600));
		stage.show();
		
	}

}
