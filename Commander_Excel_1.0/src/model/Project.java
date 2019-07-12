package model;

import java.util.ArrayList;

import control.MainMenuController;

import java.io.File;

import javafx.stage.DirectoryChooser;

public class Project implements Cloneable,Comparable<Project>{
	
	private File file;
	private ArrayList<task> projectTasks = new ArrayList<>();
	
	public Project(){
		
	}
	public Project(File projectPath) {
		
		this.file = projectPath;
	
	}

	public File getProjectPathFile() {
		return file;
	}
	public void setProjectPathFile(File filePath) {
		this.file = filePath;
		
	}
	

	public ArrayList<task> getProjectTasks() {
		return projectTasks;
	}
	public void setProjectTasks(ArrayList<task> projectTasks) {
		
		this.projectTasks = projectTasks;
	}
	@Override
	public int compareTo(Project project) {
		
		if(project.getProjectPathFile() != this.getProjectPathFile()) {
			return -1;
		}
		
		return 0;
	}

	 
	
	
	
	
	
	

}
