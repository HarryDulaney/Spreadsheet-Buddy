package com.commander.app;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.swing.text.DocumentFilter.FilterBypass;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import com.commander.app.model.CSVfilter;
import com.commander.app.model.CSVfilter.*;


/**
 * @author Harry Gingles Dulaney IV
 */
public class CSVFilterController {
	
	private CSVfilter csVfilter;
	public ArrayList<String> list = new ArrayList<>();
	


	@FXML
	private TextField textField1;

	@FXML
	private TextField textField2;

	@FXML
	private TextField textField3;


	@FXML
	private Label Label1;

	@FXML
	private Label Label2;

	@FXML
	private Label Label3;
	
	
	@FXML
	protected void handleSubmitCSVfilter(ActionEvent event) throws Exception {
		
		FileChooser choose = new FileChooser();
		choose.setTitle("Select the location to save your CSV filter result via .xlsx file");
		File file = choose.showOpenDialog(new Stage(StageStyle.UTILITY));
		String path = file.getPath();
		
		
		getFilter().extractToXLSX(path,list);
		
		

	}

	public CSVFilterController() {
		
	}

	
	@FXML
	public void initialize() {
		
	try {
		CSVfilter csvfilter = new CSVfilter();
		setFilter(csvfilter);
	} catch (Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Exception Dialog");
		alert.setHeaderText("Something went wrong");
		alert.setContentText("AN ERROR OCCURED");
}
		
		
		
		Label Label1 = new Label("Column One Header");
		Label Label2 = new Label("Column Two Header");
		Label Label3 = new Label("Column Three Header");
		Label Label4 = new Label("Column Four Header");

		
		
	}

	public void start() throws Exception {
		
		Stage stage = new Stage();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/commander/app/view/CSVfilterWiz.fxml"));
		try {
			Parent root = (Parent) loader.load();

			stage.setScene(new Scene(root, 500, 400));
			stage.showAndWait();
			
			

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("Something went wrong");
			alert.setContentText("Cannot load CSV Wizard");

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

			// Set expandable Exception into the dialog pane.
			alert.getDialogPane().setExpandableContent(expContent);

			alert.showAndWait();

		}

	}

	/**
	 * @return the tasker
	 */
	public CSVfilter getFilter() {
		return csVfilter;
	}

	/**
	 * @param tasker the tasker to set
	 */
	public void setFilter(CSVfilter tasker) {
		this.csVfilter = tasker;
	}

}
