package com.commander.app.model;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Project {

	private	LinkedList<SuperCommand> sooperCommands;
	private String projectName;
	private File xmlFilePath;

	public Project() {

	}

	public Project(String projectName, File xmlFilePath, LinkedList<SuperCommand> sooperCommands) {
		this.projectName = projectName;
		this.xmlFilePath = xmlFilePath;
		this.sooperCommands = sooperCommands;
	}

	@XmlElement
	public LinkedList<SuperCommand> getSooperCommands() {
		return sooperCommands;
	}

	public void setSooperCommands(LinkedList<SuperCommand> sooperCommands) {
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