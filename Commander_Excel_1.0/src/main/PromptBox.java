package main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * @author Harry Dulaney
 *
 */

public class PromptBox extends Application {

	public String message;
	public Boolean hasInput;

	public PromptBox() {

		this.hasInput = false;
		this.message = "Default Message";

	}

	public PromptBox(String message, Boolean hasInput) {
		this.message = message;
		this.hasInput = hasInput;
	}
	
	
	@Override
	public void start(Stage stage) {
		
		hasInput = true;

		stage.setTitle("Information Requested");

		Button button = new Button("Complete and Save");
		
		

		Label label = new Label();
		label.setText(message);

		TextField textfld = new TextField();
		textfld.setMaxWidth(100);

		VBox vbox = new VBox(30);
		
		vbox.setAlignment(Pos.CENTER);
	
	
		
		if(hasInput) {
			vbox.getChildren().addAll(label,textfld, button);
		}else {
			vbox.getChildren().addAll(label);
			
		}

	

		Scene scene = new Scene(vbox, 600, 400);
		stage.setScene(scene);
		stage.show();
				
	}

	public static void main(String[] args) {
		launch(args);
	}

}
