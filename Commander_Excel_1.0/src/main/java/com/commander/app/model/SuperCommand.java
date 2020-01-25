package main.java.com.commander.app.model;

import java.io.File;
import java.util.LinkedList;
import main.java.com.commander.app.model.utils.SpreadSheetTask;
import main.java.com.commander.app.utils.Scrapper.JsoupObj;

/**
 * 
 * A SuperCommand object is that defines an automated operation on one or many
 * Workbooks and Sheets.
 * 
 * 
 * @author Harry Dulaney IV
 */
public class SuperCommand {

	private String superCommandName;
	private File commFileIn;
	private File commfileOut;
	private JsoupObj scrapeFunc;
	private LinkedList<SpreadSheetTask> orderedTasks;

	public SuperCommand() {

	}

	public LinkedList<SpreadSheetTask> getOrderedTasks() {
		return orderedTasks;
	}

	public void setOrderedTasks(LinkedList<SpreadSheetTask> orderedTasks) {
		this.orderedTasks = orderedTasks;
	}

	public String getSuperCommandName() {
		return superCommandName;
	}

	public void setSuperCommandName(String name) {
		this.superCommandName = name;

	}

	public File getFileIn() {
		return commFileIn;
	}

	public void setFileIn(File fileIn) {
		this.commFileIn = fileIn;
	}

	public File getFileOut() {
		return commfileOut;
	}

	public void setFileOut(File commfileOut) {
		this.commfileOut = commfileOut;
	}

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
