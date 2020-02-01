package main.java.com.commander.app.model;

import java.io.File;
import java.util.LinkedList;
import main.java.com.commander.app.model.utils.SSTask;

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
	private LinkedList<SSTask> orderedTasks;

	public SuperCommand() {
		super();

	}

	public LinkedList<SSTask> getOrderedTasks() {
		return orderedTasks;
	}

	public void setOrderedTasks(LinkedList<SSTask> orderedTasks) {
		this.orderedTasks = orderedTasks;
	}

	public String getName() {
		return superCommandName;
	}

	public void setName(String name) {
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

	public void addTask(SSTask task) {

		orderedTasks.add(task);

	}

	public void removeLastTask(SSTask task) {
		orderedTasks.removeLast();
	}

}
