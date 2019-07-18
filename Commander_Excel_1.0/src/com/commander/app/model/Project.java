package com.commander.app.model;

import java.util.ArrayList;

import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Harry Gingles Dulaney IV
 */

public class Project implements Cloneable, Comparable<Project> {

	private final SimpleStringProperty curProjFolder;
	private final SimpleStringProperty userID;
	private static ArrayList<CommanderTask> projectTasks;
	private ObservableList<CommanderTask> observableTasks;

	/**
	 * Project default constructor
	 * 
	 * @param userID
	 * @param projectFolder
	 */
	public Project() {

		this(null, null);
		Project.projectTasks = new ArrayList<>();

	}

	public Project(String userid, String curProjFold) {

		this.userID = new SimpleStringProperty(userid);
		this.curProjFolder = new SimpleStringProperty(curProjFold);
		Project.projectTasks = new ArrayList<>();

	}

	public SimpleStringProperty projectFolderProperty() {
		return curProjFolder;
	}

	public void setProjectFolder(String projectFolder) {
		this.curProjFolder.set(projectFolder);
	}
	public static void addTaskstoProject(CommanderTask task) {
		Project.projectTasks.add(task);
	}
	

	/**
	 * @return the root directory folder assigned or create by the user
	 */
	public String getProjectFolder() {
		return curProjFolder.get();
	}

	/**
	 * @return the ArrayList of tasks for the current Project
	 * @see Project
	 */

	public ArrayList<CommanderTask> getProjectTasks() {
		return projectTasks;
	}

	public void addProjectTask(CommanderTask task) {

		projectTasks.add(task);

	}

	public SimpleStringProperty userIDProperty() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID.set(userID);
	}

	public String getUserID() {
		return userID.get();

	}

	@Override
	public int compareTo(Project project) {

		String compareString = project.getProjectFolder();

		if (this.getProjectFolder() != compareString) {
			return -1;
		}

		return 0;
	}

	public ObservableList<CommanderTask> getObservableTasks() {
		return observableTasks;
	}

	public void setObservableTasks(ArrayList<CommanderTask> projectTasks) {

		ObservableList<CommanderTask> convertedTasks = FXCollections.observableArrayList();

		convertedTasks.addAll(projectTasks);

		this.observableTasks = convertedTasks;
	}

}
