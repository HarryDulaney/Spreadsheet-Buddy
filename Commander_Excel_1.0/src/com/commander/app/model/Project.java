package com.commander.app.model;

import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Project {

	private ArrayList<SuperCommand> sooperCommands;
	private String projectName;
	private File xmlFilePath;

	public Project() {

	}

	public Project(String projectName, File xmlFilePath, ArrayList<SuperCommand> sooperCommands) {
		this.projectName = projectName;
		this.xmlFilePath = xmlFilePath;
		this.sooperCommands = sooperCommands;
	}

	@XmlElement
	public ArrayList<SuperCommand> getSooperCommands() {
		return sooperCommands;
	}

	public void setSooperCommands(ArrayList<SuperCommand> sooperCommands) {
		this.sooperCommands = sooperCommands;
	}

	@XmlElement(name = "projectName")
	public String getName() {
		return projectName;
	}

	public void setName(String projectName) {
		this.projectName = projectName;
	}

	@XmlElement(name = "xmlFilePath")
	public File getProjectFile() {
		return xmlFilePath;
	}

	public void setProjectFile(File xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}

	public void addSuperCommand(SuperCommand command) {

		sooperCommands.add(command);

	}

}