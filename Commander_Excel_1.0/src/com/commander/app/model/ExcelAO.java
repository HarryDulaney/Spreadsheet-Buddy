package com.commander.app.model;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

@XmlRootElement
public class ExcelAO extends MyTask{
	/**
	 * Like a DB object this is an "Excel Access Object" which contains my
	 * implementation of the Fillo API
	 */

	/*
	 * WHERE CLAUSE EXAMPLES:
	 * 
	 * This is an enhancement in Fillo-1.11, now you can mention multiple conditions
	 * in a query as shown below. Recordset recordset=connection.
	 * executeQuery("Select * from Sheet1 where column1=value1 and column2=value2 and column3=value3"
	 * );
	 * 
	 * WHERE METHOD EXAMPLES:
	 * 
	 * Recordset
	 * recordset=connection.executeQuery("Select * from Sheet1").where("ID=100").
	 * where("name='John'");
	 * 
	 * USING THE LIKE OPERATOR Recordset recordset=connection.
	 * executeQuery("Select * from Sheet1 where Name like 'Cod%'");
	 * 
	 * //Now you can set table start row and column System.setProperty("ROW",
	 * "5");//Table start row System.setProperty("COLUMN", "3");//Table start column
	 * Fillo fillo=new Fillo(); Connection connection=fillo.getConnection(strFile);
	 * 
	 * 
	 */
	private Connection connection;
	private File sourceFile;
	private String strQuery;
	private File outputFile;
	private List<String> sheets;

	public ExcelAO() {

	}

	public ExcelAO(File sourceFile) {
		this.sourceFile = sourceFile;
	}

	public static void handleSelectMatcher(File source, String sheetName, String rowID, String rowVal, String colID,
			String colVal, String field) {

		Recordset recordset = null;
		Connection connection = null;

		try {
			Fillo fillo = new Fillo();
			connection = fillo.getConnection(source.getAbsolutePath());

		} catch (FilloException e) {

			ExcelAO.showAlert("Fillo Exception: Trying to connect to File source.", e);

		}

		try {
			String strQuery = "Select * from " + sheetName + " where " + rowID + " = " + rowVal + " and " + colID
					+ " = " + "\'" + colVal + "\'";

			recordset = connection.executeQuery(strQuery);

		} catch (FilloException e) {

			showAlert("Fillo Exception: Trying to executeQuery on \"strQuery\"", e);

		}

		try {
			while (recordset.next()) {
				System.out.println(recordset.getField(field));
			}
		} catch (FilloException e) {
			showAlert("Fillo Exception: on recordset return loop.", e);

		}

		recordset.close();
		connection.close();
	}

	public static void handleUpdate(File source, String sheetName, String field, String fieldDetail, String rowID,
			String rowVal, String colID, String colVal) {

		Fillo fillo = new Fillo();
		Connection connection = null;

		try {
			connection = fillo.getConnection(source.getAbsolutePath());
		} catch (FilloException e) {

			ExcelAO.showAlert("Fillo Exception: Trying to connect to File source.", e);
		}
		String strQuery = "Update " + sheetName + " Set " + field + " = " + "\'" + "fieldDetail" + "\'" + " where "
				+ rowID + " = " + rowVal + " and " + colID + " = " + "\'" + colVal + "\'";

		try {
			connection.executeUpdate(strQuery);
		} catch (FilloException e) {

			showAlert("Fillo Exception: Trying to executeQuery on \"strQuery\"", e);

		}

		connection.close();

	}

	public static void handleInsert(File source, String sheetName) {

		Fillo fillo = new Fillo();
		Connection connection = null;

		try {
			connection = fillo.getConnection("C:\\Test.xlsx");
		} catch (FilloException e) { 
			ExcelAO.showAlert("Fillo Exception: Trying to connect to File source.", e);

		}
		String strQuery = "INSERT INTO " + sheetName + "(Name,Country) VALUES('Peter','UK')";

		try {
			connection.executeUpdate(strQuery);
		} catch (FilloException e) {

			showAlert("Fillo Exception: Trying to executeQuery on \"strQuery\"", e);

		}

		connection.close();

	}

	public static void showAlert(String contentText, Exception e) {

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

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public File getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(File sourceFile) {
		this.sourceFile = sourceFile;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}


}
