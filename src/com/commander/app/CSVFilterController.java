package com.commander.app;

import java.io.File;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.TextField;
import com.commander.app.model.CSVfilter;

/**
 * @author H.G. Dulaney IV
 */
public class CSVFilterController {

	private CSVfilter csVfilter;
	private File file;
	private MainMenu m;

	@FXML
	private TextField textfield1;

	@FXML
	private TextField textfield2;

	@FXML
	private TextField textfield3;

	@FXML
	private TextField textfield4;

	@FXML
	private TextField textfield5;

	@FXML
	protected void handleSubmitCSVfilter(ActionEvent event) throws Exception {

		ArrayList<String> list = new ArrayList<>();

		System.out.println(list.size());

		if (textfield1.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Something went wrong");
			alert.setContentText("The only field you cannot leave blank is header one.");
			alert.show();

		} else {
			list.add(textfield1.getText());

			if (textfield2.getText().isEmpty()) {

				list.trimToSize();

				csVfilter.extractToXLSX(list);

			} else {

				list.add(textfield2.getText());

				if (textfield3.getText().isEmpty()) {
					list.trimToSize();

					csVfilter.extractToXLSX(list);
				} else {
					list.add(textfield3.getText());

					if (textfield4.getText().isEmpty()) {
						list.trimToSize();

						csVfilter.extractToXLSX(list);

					} else {
						list.add(textfield4.getText());
						{

							if (textfield5.getText().isEmpty()) {
								list.trimToSize();

								csVfilter.extractToXLSX(list);
							} else {

								list.add(textfield5.getText());

								list.trimToSize();

								csVfilter.extractToXLSX(list);
							}

						}

					}

				}

			}

		}

	}

	public CSVFilterController() {

	}

	@FXML
	public void initialize() {

		try {
			start();
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public void start() throws Exception {

		FileChooser chooser = new FileChooser();
		chooser.setTitle("Select the CSV file you want to work with");
		chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".csv", "*.csv"));
		// chooser.setInitialDirectory(MainMenu.getCurrentProject().getProjectFilepath());
		file = chooser.showOpenDialog(new Stage(StageStyle.UTILITY));

		if (file != null) {
			CSVfilter csvfilter = new CSVfilter(file);
			this.csVfilter = csvfilter;

		}

	}

	public void setMainMenu(MainMenu m) {
		this.m = m;
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

}
