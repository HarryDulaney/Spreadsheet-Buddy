package com.commander.app.model;

import java.io.File;
import java.util.ArrayList;

public final class ProjectBean {

	private static ProjectBean instance;
	private static ArrayList<Command> sooperCommands = new ArrayList<>();
	private static String projectName;
	private static File xmlFilePath;

	private ProjectBean() {
	}

	private ProjectBean(String projectName, File xmlFilePath, ArrayList<Command> sooperCommands) {
		ProjectBean.projectName = projectName;
		ProjectBean.xmlFilePath = xmlFilePath;
		ProjectBean.sooperCommands = sooperCommands;

	}

	private ProjectBean(String projectName, File xmlFilePath) {
		ProjectBean.projectName = projectName;
		ProjectBean.xmlFilePath = xmlFilePath;
	}

	public static ProjectBean getInstance(String projectName, File xmlFilePath, ArrayList<Command> sooperCommands) {
		if (instance == null) {

			instance = new ProjectBean(projectName, xmlFilePath, sooperCommands);
		}

		return instance;

	}

	public static ProjectBean getInstance(String projectName, File xmlFilePath) {
		if (instance == null) {

			instance = new ProjectBean(projectName, xmlFilePath);
		}

		return instance;
	}

	public static ArrayList<Command> getSooperCommands() {
		return sooperCommands;
	}

	public void setSooperCommands(ArrayList<Command> sooperCommands) {
		ProjectBean.sooperCommands = sooperCommands;
	}

	public static String getName() {
		return projectName;
	}

	public void setName(String projectName) {
		ProjectBean.projectName = projectName;
	}

	public static File getProjectFile() {
		return xmlFilePath;
	}

	public static void setProjectFile(File xmlFilePath) {
		ProjectBean.xmlFilePath = xmlFilePath;
	}

	public static void addCommand(Command command) {

		ProjectBean.sooperCommands.add(command);

	}

	public void closeProject() {
		projectName = "";
		sooperCommands = null;
		xmlFilePath = null;

	}

	public static ProjectBean getInstance() {
		return instance;
	}

}
