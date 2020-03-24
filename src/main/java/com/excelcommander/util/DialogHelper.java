package com.excelcommander.util;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * This is a class of static methods to utilize for Alerts, Pop-ups and UI
 * Dialogs.
 * 
 * @author HGDIV
 *
 */
public final class DialogHelper {

	public static void defaultDialog(StackPane root, String title, String body, DialogAction action) {

		JFXDialogLayout content = new JFXDialogLayout();
		content.setHeading(new Text(title));
		content.setBody(new Text(body));

		JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);

		JFXButton okButton = new JFXButton("Ok");
		okButton.setOnAction(event -> {
			dialog.close();
			action.onAction();
				
		});
		content.setActions(okButton);

		dialog.show();
	}

	public static void inputDialog(StackPane root, String title, String body, DialogAction action) {

		JFXDialogLayout content = new JFXDialogLayout();
		content.setHeading(new Text(title));
		content.setBody(new Text(body));

		JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);

		JFXButton yesButton = new JFXButton("Yes");
		yesButton.setOnAction(event -> {
			dialog.close();
			action.onAction();
		});
		content.setActions(yesButton);

		JFXButton noButton = new JFXButton("No");
		noButton.setOnAction(event -> {
			dialog.close();
		});
		content.setActions(noButton);

		dialog.show();
	}

	public static File showFilePrompt(String title, String fileExtension1, String fileExtention2) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(title);
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".csv", "*".concat(fileExtension1)),
				new FileChooser.ExtensionFilter(".xlsx", "*".concat(fileExtention2)));
		return fileChooser.showOpenDialog(new Stage(StageStyle.UTILITY));

	}

	public static File showFilePrompt(String title, String fileExtension, Boolean initFileName) {

		FileChooser fchooser = new FileChooser();
		fchooser.setTitle(title);
		fchooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(fileExtension, "*".concat(fileExtension)));

		if (initFileName) {
			fchooser.setInitialFileName("New_Excel_Workbook");
		}

		return fchooser.showSaveDialog(new Stage(StageStyle.UTILITY));
	}

	public static File showFilePrompt(String title, String fileExtension) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(title);
		fileChooser.getExtensionFilters()
				.addAll(new FileChooser.ExtensionFilter(fileExtension, "*".concat(fileExtension)));
		return fileChooser.showOpenDialog(new Stage(StageStyle.TRANSPARENT));

	}

	public static String showInputPrompt(String header, String content, String title) {

		TextInputDialog textDialog = new TextInputDialog();
		textDialog.setHeaderText(header);
		textDialog.setContentText(content);
		textDialog.setTitle(title);
		textDialog.showAndWait();

		return textDialog.getResult();
	}

	public static void showWarningAlert(String content) {
		Alert alrt = new Alert(AlertType.WARNING);
		alrt.setContentText(content);
		alrt.show();

	}

	public static void showErrorAlert(String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(content);
		alert.showAndWait();

	}

	public static void showInfoAlert(String content,String header,String title,boolean wait) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.setTitle(title);
		alert.showAndWait();
		

	}

	public static void showInfoAlert(String content, boolean wait) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(content);
		if (wait) {
			alert.showAndWait();
		} else {
			alert.show();
		}

	}

	public static void showExceptionAlert(String contentText, Exception e) {

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Exception Dialog");
		alert.setHeaderText("Something went wrong");
		alert.setContentText(contentText);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("The exception stacktrace was:");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		alert.getDialogPane().setExpandableContent(expContent);

	}

}

