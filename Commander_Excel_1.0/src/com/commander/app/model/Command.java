package com.commander.app.model;

import java.io.File;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Command {

	private String commandName;
	private int comm_id;
	private File commFileIn;
	private File commfileOut;
	private JsoupObj scrapeFunc;

	public Command() {

	}

	@XmlElement(name = "commandName")
	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String name) {
		this.commandName = name;

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
	
	@XmlElement
	public JsoupObj getScrapeFunc() {
		return scrapeFunc;
	}

	public void setScrapeFunc(JsoupObj scrapeFunc) {
		this.scrapeFunc = scrapeFunc;
	}

}
