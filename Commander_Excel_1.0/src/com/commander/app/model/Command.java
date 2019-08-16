package com.commander.app.model;

import java.io.File;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javafx.beans.property.SimpleStringProperty;

@XmlRootElement
public class Command {
	
	private final SimpleStringProperty displayName = new SimpleStringProperty("");
	private String commName;
	private int comm_id;
	private File commFileIn;
	private File commfileOut;
	
	public Command() {
		setName("Command_Name");
		
	}

	@XmlElement(name = "commandName")
	public String getName() {
		return commName;
	}

	public void setName(String name) {
		this.commName = name;
		displayName.set(name);
		
	}
	public SimpleStringProperty getDisplayName() {
		
		return displayName;
	}

	@XmlElement(name = "command_id")
	public int getId() {
		return comm_id;
	}

	public void setId(int id) {
		this.comm_id = id;
	}

	@XmlElement(name = "commandFileIn")
	public File getFileIn() {
		return commFileIn;
	}

	public void setFileIn(File fileIn) {
		this.commFileIn = fileIn;
	}

	@XmlElement(name = "commandFileOut")
	public File getFileOut() {
		return commfileOut;
	}

	public void setFileOut(File commfileOut) {
		this.commfileOut = commfileOut;
	}
	

}
