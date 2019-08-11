package com.commander.app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import InProcess.WorkBookmaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author H.G. Dulaney IV
 */
public class WizController {

	private MainMenu mm;

	@FXML
	private Label header;

	@FXML
	private Label subheader;

	@FXML
	private TextField textField1;

	@FXML
	private TextField textField2;

	@FXML
	protected void handleCancelTaskWiz(ActionEvent event) throws Exception {

		MainMenu.getMainMenu().showProject();
	}

	@FXML
	protected void handleSubmitNewWB(ActionEvent event) throws IOException {

	}

	public WizController() {

	}

	@FXML
	public void initialize() throws FilloException {

		Fillo fillo = new Fillo();
		Connection connection = null;
		try {
			connection = fillo.getConnection("C:\\Test.xlsx");
		} catch (FilloException e) {
			
			e.printStackTrace();
		}
		String strQuery = "Select * from Sheet1 where ID=100 and name='John'";
		Recordset recordset = null;
		try {
			recordset = connection.executeQuery(strQuery);
		} catch (FilloException e) {
			
			e.printStackTrace();
		}

		while (recordset.next()) {
			try {
				System.out.println(recordset.getField("Details"));
			} catch (FilloException e) {
		
				e.printStackTrace();
			}
		}

		recordset.close();
		connection.close();

		this.mm = MainMenu.getMainMenu();

	}

	public MainMenu getMainMenu() {
		return MainMenu.getMainMenu();
	}

	public void setMainmenu(MainMenu mm) {
		this.mm = MainMenu.getMainMenu();

	}

}
