package com.commander.app.model;


import javafx.beans.property.SimpleStringProperty;


import java.io.IOException;


/*
 * After choosing the task the user wants to add to their excel project, they 
 * are prompted to input required info for the task to be executed.
 */

/**
 * @author Harry Gingles Dulaney IV
 */
public abstract class abstractTask {
	/**
	 * String reference to task
	 */
	protected static SimpleStringProperty taskName;
	
	

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
		abstractTask.taskName.set(taskName);
	}

	public String getTaskName() {
		return taskName.get();
	}


}
