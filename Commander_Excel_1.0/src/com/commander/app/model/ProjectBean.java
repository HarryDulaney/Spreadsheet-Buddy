package com.commander.app.model;

import java.io.File;
import java.util.ArrayList;

public class ProjectBean {

	private static ProjectBean instance;
	private ArrayList<Command> sooperCommands;
	private String projectName;
	private File xmlFilePath;

	private ProjectBean() {
	}

	private ProjectBean(String projectName, File xmlFilePath, ArrayList<Command> sooperCommands) {
		this.projectName = projectName;
		this.xmlFilePath = xmlFilePath;
		this.sooperCommands = sooperCommands;

	}

	public static ProjectBean getInstance(String projectName, File xmlFilePath, ArrayList<Command> sooperCommands) {
		if (instance == null) {

			instance = new ProjectBean(projectName, xmlFilePath, sooperCommands);
		}

		return instance;

	}

	public ArrayList<Command> getSooperCommands() {
		return this.sooperCommands;
	}

	public void setSooperCommands(ArrayList<Command> sooperCommands) {
		this.sooperCommands = sooperCommands;
	}

	public String getName() {
		return this.projectName;
	}

	public void setName(String projectName) {
		this.projectName = projectName;
	}

	public File getProjectFile() {
		return this.xmlFilePath;
	}

	public void setProjectFile(File xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}

	public void addCommand(Command command) {

		this.sooperCommands.add(command);

	}

	public void closeProject() {
		this.projectName = "";
		this.sooperCommands.clear();
		this.xmlFilePath = null;
		instance = null;

	}
	public static ProjectBean getInstance() {
		
		return instance;
		
	}

}
