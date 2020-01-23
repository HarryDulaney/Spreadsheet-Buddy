package com.commander.app;

import java.io.File;

import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PHelper {

	public static File showFileFilePrompt(String title, String fileExtension1, String fileExtention2) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose File To pull data from");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".csv", "*.csv"),
				new FileChooser.ExtensionFilter(".xlsx", "*.xlsx"));
		return fileChooser.showOpenDialog(new Stage(StageStyle.UTILITY));

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

}
