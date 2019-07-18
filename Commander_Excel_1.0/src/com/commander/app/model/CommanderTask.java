package com.commander.app.model;


import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;

import org.controlsfx.dialog.Wizard;

/*
 * After choosing the task the user wants to add to their excel project, they 
 * are prompted to input required info for the task to be executed.
 */

/**
 * @author Harry Gingles Dulaney IV
 */
public abstract class CommanderTask {
	/**
	 * String reference to task
	 */
	protected static SimpleStringProperty taskName;

	protected void executeTask() throws IOException {
	};

	/**
	 * Standard input spreadsheet (or other source of input ie. website) assigned to
	 * the task and output assigned(usually a Excel spreadsheet/workbook name) when
	 * it is created by user via the wizard setup
	 */
	protected SimpleStringProperty p1, p2;;

	public SimpleStringProperty taskNameProperty() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		CommanderTask.taskName.set(taskName);
	}

	public String getTaskName() {
		return taskName.get();
	}

	public SimpleStringProperty p1DataProperty() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1.set(p1);
	}

	public String getP1() {
		return this.p1.get();
	}

	public SimpleStringProperty p2DataProperty() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2.set(p2);
	}

	public String getP2() {
		return this.p2.get();
	}

}
