package com.commander.app;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.commander.app.model.JsoupObj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class LeftPane1Controller {

	@FXML
	private TextField attributeOne;

	@FXML
	private TextField attributeTwo;

	@FXML
	private TextField valueOne;

	@FXML
	private TextField valueTwo;

	@FXML
	private TextField userTextInput;

	@FXML
	private RadioButton userOutXlsx;

	@FXML
	private RadioButton userOutCSV;

	@FXML
	private Button OpenNewScrape;

	@FXML
	private TextField url;

	private static int initCounter = 1;

	@FXML
	private Label label3;

	@FXML
	public void initialize() {

		if (initCounter == 1) {
			label3.setText("Choose an input source from below:");

		}
		if (initCounter == 2) {

		}

		initCounter++;
	}

	@FXML
	protected void handleOpenFile(ActionEvent event) {

	}

	@FXML
	protected void handleOpenScrapper(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainMenu.class.getResource("/com/commander/app/view/WebScrapeDefine.fxml"));
		AnchorPane anchorpane = loader.load();

		BorderPane borderpane = (BorderPane) getNestedPane(MainMenu.getRootPane().getScene());
		borderpane.setCenter(anchorpane);

	}

	@FXML
	protected void handleSubmitScrapper(ActionEvent event) {

		String strURL = url.getText();

		if (strURL != null) {

			JsoupObj scrapper = new JsoupObj();

			scrapper.setSourceUrl(strURL.toString());

			String checkTitle = "";
			try {
				checkTitle = scrapper.initData();
			} catch (Exception e) {

				Alert alrt = new Alert(AlertType.ERROR);
				alrt.setContentText("Be sure to double check the URL and to input authentication information "
						+ "if the webpage requires it.");
				alrt.showAndWait();
			}

			if (checkTitle != "") {
				Alert alt = new Alert(AlertType.CONFIRMATION);
				alt.setContentText("We retrieved a document with the title: " + checkTitle + " from: " + strURL
						+ " press OK to continue or CANCEL to try a different URL.");

				alt.showAndWait();

				if (alt.getResult() == ButtonType.CANCEL) {

					OpenNewScrape.fire();

				} else if (alt.getResult() == ButtonType.OK) {

					// continue with defining the supercommand

				}

			}
		}
	}

	public Node getNestedPane(Scene scene) {
		Node bp = scene.lookup("#bPane");
		return bp;
	}

}
