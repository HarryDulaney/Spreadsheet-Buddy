package com.commander.app.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import InProcess.abstractTask;

/**
 * 
 * @author HG Dulaney IV
 *
 */
@XmlRootElement(name = "Project")
public class ProjectListWrapper {
	
	private Project project;
	private List<abstractTask> tasks;
	
	
	@XmlElement(name="Project")
	public Project getProject(){
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	@XmlElement(name="task")
	public List<abstractTask> getTasks(){
		return tasks;
		
	
	}
	public void setTasks(List<abstractTask> tasks) {
		this.tasks = tasks;}
	}

