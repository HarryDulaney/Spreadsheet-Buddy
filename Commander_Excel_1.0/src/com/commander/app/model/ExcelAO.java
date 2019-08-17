package com.commander.app.model;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

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

public class ExcelAO {
	/**
	 * Like a DB object this is an "Excel Access Object" which contains the my
	 * implementation of the Fillo API
	 */
	
	
	/*WHERE CLAUSE EXAMPLES:
	 * 
	 * This is an enhancement in Fillo-1.11, now you can mention multiple conditions in a query as shown below.
	 * Recordset recordset=connection.executeQuery("Select * from Sheet1 where column1=value1 and column2=value2 and column3=value3");
	 * 
	 * WHERE METHOD EXAMPLES:
	 * 
	 * Recordset recordset=connection.executeQuery("Select * from Sheet1").where("ID=100").where("name='John'");
	 * 
	 * USING THE LIKE OPERATOR
	 * Recordset recordset=connection.executeQuery("Select * from Sheet1 where Name like 'Cod%'");
	 * 
	 * //Now you can set table start row and column
	 * System.setProperty("ROW", "5");//Table start row
	 * System.setProperty("COLUMN", "3");//Table start column
	 * Fillo fillo=new Fillo();
	 * Connection connection=fillo.getConnection(strFile);
	 * 
	 * 
	 */
	private Connection connection;
	private File sourceFile;
	private String strQuery;
	private File outputFile;

	public ExcelAO() {
		
	

	}

	public void handleSelect(String field,File source,Connection connection) {

		try {
			Fillo fillo = new Fillo();
			connection = fillo.getConnection(source.getAbsolutePath());

		} catch (Exception e) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("Something went wrong");
			alert.setContentText("File Error");

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
		try {
			String strQuery = "Select * from Sheet1 where ID=100 and name='John'";
			recordset = connection.executeQuery(strQuery);

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("Something went wrong");
			alert.setContentText("This is probably a syntax error in the query string");

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

		try {
			while (recordset.next()) {
				System.out.println(recordset.getField(field));
			}
		} catch (FilloException e) {
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

			alert.getDialogPane().setExpandableContent(expContent);

		}

		recordset.close();
		connection.close();
	}

	public void handleUpdate(File source) {
		Fillo fillo = new Fillo();
		try {
			connection = fillo.getConnection(source.getAbsolutePath());
		} catch (FilloException e) {
			e.printStackTrace();
		}
		String strQuery = "Update Sheet1 Set Country='US' where ID=100 and name='John'";
		//These queries will be customized via the method variable inputs

		try {
			connection.executeUpdate(strQuery);
		} catch (FilloException e) {

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

			alert.getDialogPane().setExpandableContent(expContent);

		}

		connection.close();

	}
	
	
	public void handleInsert(File source) {
		Fillo fillo=new Fillo();
		
		
		try {
			connection = fillo.getConnection("C:\\Test.xlsx");
		} catch (FilloException e) {

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

			
			alert.getDialogPane().setExpandableContent(expContent);
		}
		String strQuery="INSERT INTO sheet4(Name,Country) VALUES('Peter','UK')";

		try {
			connection.executeUpdate(strQuery);
		} catch (FilloException e) {
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

			
			alert.getDialogPane().setExpandableContent(expContent);
		}

		connection.close();
		
	}

	private Recordset recordset;

	/**
	 * @return the recordset
	 */
	public Recordset getRecordset() {
		return recordset;
	}

	/**
	 * @param recordset the recordset to set
	 */
	public void setRecordset(Recordset recordset) {
		this.recordset = recordset;
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * @param connection the connection to set
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * @return the sourceFile
	 */
	public File getSourceFile() {
		return sourceFile;
	}

	/**
	 * @param sourceFile the sourceFile to set
	 */
	public void setSourceFile(File sourceFile) {
		this.sourceFile = sourceFile;
	}

	/**
	 * @return the strQuery
	 */
	public String getStrQuery() {
		return strQuery;
	}

	/**
	 * @param strQuery the strQuery to set
	 */
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	/**
	 * @return the outputFile
	 */
	public File getOutputFile() {
		return outputFile;
	}

	/**
	 * @param outputFile the outputFile to set
	 */
	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}



}
