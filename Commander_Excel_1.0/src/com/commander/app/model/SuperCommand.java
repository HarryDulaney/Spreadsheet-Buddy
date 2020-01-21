package com.commander.app.model;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.commander.app.model.tasks.SpreadSheetTask;
import com.commander.app.utils.Scrapper.JsoupObj;

@XmlRootElement
public class SuperCommand {

	private String superCommandName;
	private File commFileIn;
	private File commfileOut;
	private JsoupObj scrapeFunc;
	private LinkedList<SpreadSheetTask> orderedTasks;

	public SuperCommand() {

	}

	@XmlElement
	public LinkedList<SpreadSheetTask> getOrderedTasks() {
		return orderedTasks;
	}

	public void setOrderedTasks(LinkedList<SpreadSheetTask> orderedTasks ) {
		this.orderedTasks = orderedTasks;
	}

	@XmlElement(name = "SuperCommandName")
	public String getSuperCommandName() {
		return superCommandName;
	}

	public void setSuperCommandName(String name) {
		this.superCommandName = name;

	}

	@XmlElement(name = "MainFileIn")
	public File getFileIn() {
		return commFileIn;
	}

	public void setFileIn(File fileIn) {
		this.commFileIn = fileIn;
	}

	@XmlElement(name = "MainFileOut")
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

	public void addTask(SpreadSheetTask task) {

		orderedTasks.add(task);

	}
	public void removeLastTask(SpreadSheetTask task) {
		orderedTasks.removeLast();
	}

}
