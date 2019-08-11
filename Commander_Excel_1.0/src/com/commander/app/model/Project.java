package com.commander.app.model;

import java.io.File;
import java.util.ArrayList;

import InProcess.abstractTask;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author H.G. Dulaney IV
 */

public class Project {

	private static String projectName;
	private final SimpleStringProperty userID;
	private static ArrayList<abstractTask> projectTasks = new ArrayList<>();
	private ObservableList<abstractTask> observableTasks;
	private static File projectDirectory;

	/**
	 * Project default constructor
	 * 
	 * @param userID
	 * @param projectFolder
	 */
	public Project() {

		this(null, null, null);

	}

	public Project(String userid, String projectName, File filePath) {

		this.userID = new SimpleStringProperty(userid);
		Project.projectName = projectName;

	}

	public String projectFolderProperty() {
		return projectName;
	}

	public static void addTaskstoProject(abstractTask task) {
		Project.projectTasks.add(task);
	}

	public String getProjectName() {
		return Project.projectName;
	}

	/**
	 * @return the ArrayList of tasks for the current Project
	 * @see Project
	 */

	public ArrayList<abstractTask> getProjectTasks() {
		return projectTasks;
	}

	public void addProjectTask(abstractTask task) {

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

	public ObservableList<abstractTask> getObservableTasks() {
		return observableTasks;
	}

	public void setObservableTasks(ArrayList<abstractTask> projectTasks) {

		ObservableList<abstractTask> convertedTasks = FXCollections.observableArrayList();

		convertedTasks.addAll(projectTasks);

		this.observableTasks = convertedTasks;
	}

	public File getProjectFilepath() {
		return projectDirectory;
	}

	public void setProjectFilepath(File projectFilepath) {
		Project.projectDirectory = projectFilepath;
	}

}
