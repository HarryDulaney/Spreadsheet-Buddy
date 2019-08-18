package com.commander.app.model;

import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Project {

	private ArrayList<Command> sooperCommands = new ArrayList<>();
	private String projectName;
	private File xmlFilePath;

	public Project() {

	}

	public Project(String projectName, File xmlFilePath) {
		this.projectName = projectName;
		this.xmlFilePath = xmlFilePath;

	}

	public Project(String projectName, File xmlFilePath, ArrayList<Command> sooperCommands) {
		this.projectName = projectName;
		this.xmlFilePath = xmlFilePath;
		this.sooperCommands = sooperCommands;
	}

	@XmlElement
	public ArrayList<Command> getSooperCommands() {
		return sooperCommands;
	}

	public void setSooperCommands(ArrayList<Command> sooperCommands) {
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

	public void addCommand(Command command) {

		sooperCommands.add(command);

	}

}