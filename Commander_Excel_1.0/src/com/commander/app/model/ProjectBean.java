package com.commander.app.model;

import java.io.File;
import java.util.ArrayList;

public class ProjectBean {

	private static ProjectBean instance;
	private ArrayList<SuperCommand> sooperCommands;
	private String projectName;
	private File xmlFilePath;

	private ProjectBean() {
	}

	private ProjectBean(String projectName, File xmlFilePath, ArrayList<SuperCommand> sooperCommands) {
		this.projectName = projectName;
		this.xmlFilePath = xmlFilePath;
		this.sooperCommands = sooperCommands;

	}

	public static ProjectBean getInstance(String projectName, File xmlFilePath, ArrayList<SuperCommand> sooperCommands) {
		if (instance == null) {

			instance = new ProjectBean(projectName, xmlFilePath, sooperCommands);
		}

		return instance;

	}

	public ArrayList<SuperCommand> getSooperCommands() {
		return this.sooperCommands;
	}

	public void setSooperCommands(ArrayList<SuperCommand> sooperCommands) {
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

	public void addCommand(SuperCommand supercommand) {

		this.sooperCommands.add(supercommand);

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
