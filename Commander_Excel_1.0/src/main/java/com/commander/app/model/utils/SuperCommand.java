package com.commander.app.model.utils;

import java.io.File;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * 
 * A SuperCommand object is that defines an automated operation on one or many
 * Workbooks and Sheets.
 * 
 * 
 * @author Harry Dulaney IV
 */
public class SuperCommand {

	private String name;
	
	private TreeMap<File,String> files;

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
		return name;
	}

	public void setName(String name) {
		this.name = name;

	}

	public void addTask(SSTask task) {

		orderedTasks.add(task);

	}

	public void removeLastTask(SSTask task) {
		orderedTasks.removeLast();
	}

	public TreeMap<File,String> getFiles() {
		return files;
	}

	public void setFiles(TreeMap<File,String> files) {
		this.files = files;
	}

}
