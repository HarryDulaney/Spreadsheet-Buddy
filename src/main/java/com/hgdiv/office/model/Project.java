package com.hgdiv.office.model;

import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Project {

	private ArrayList<Command> sooperCommands;

	private String projectName;
	private File xmlFilePath;

	public Project() {

	}

	public Project(String projectName, File xmlFilePath) {
		this.projectName = projectName;
		this.xmlFilePath = xmlFilePath;

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

		try {
			if (sooperCommands == null) {
				sooperCommands = new ArrayList<>();
			}
			sooperCommands.add(command);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}