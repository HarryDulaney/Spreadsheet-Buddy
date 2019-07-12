package control;

import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Harry Dulaney
 */

public class PromptBox extends Application {

	private String userInput;
	public String message;
	public Boolean hasInput;
	public String buttonText;

	public PromptBox() {

		this.hasInput = false;
		this.message = "Default Message";
		this.buttonText = "Finish";
	}

	public PromptBox(String message, Boolean hasInput) {
		this.message = message;
		this.hasInput = hasInput;
		this.buttonText = "Finish";
	}
	public PromptBox(String message, String buttonText, Boolean hasInput) {
		this.message = message;
		this.buttonText = buttonText;
		this.hasInput = hasInput;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {

		stage.setTitle("Information Requested");

		VBox vbox = new VBox(30);
		VBox.setVgrow(vbox, Priority.ALWAYS);

		Label label = new Label();
		label.setText(message);

		TextField textfld = new TextField();
		textfld.setMaxWidth(100);

		Button finishButton = new Button(buttonText);
		finishButton.setOnAction(e -> {

			try {

				setUserInput(textfld.getText());

				if (this.getUserInput() == null)
					throw new IOException("Field can not be left blank");
				
				stage.close();

			} catch (IOException ex) {
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText(ex.getMessage());
				
				
			}

		});

		vbox.setAlignment(Pos.CENTER);

		if (hasInput) {
			vbox.getChildren().addAll(label, textfld, finishButton);
		} else {
			vbox.getChildren().addAll(label);

		}

		Scene scene = new Scene(vbox, 600, 400);
		stage.setScene(scene);
		stage.showAndWait();

	}

	public String getUserInput() {
		return userInput;
	}

	public void setUserInput(String inputted) {
		userInput = inputted;
	}

}
