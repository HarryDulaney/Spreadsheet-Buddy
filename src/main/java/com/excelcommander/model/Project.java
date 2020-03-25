package com.excelcommander.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Project identifies the user and is created only after explicitly saving a project
 */
@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String projectName;
	private String lastKnowWorkbookPath;

	public Project() {

	}

	public Project(String projectName) {
		this.projectName = projectName;

	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getLastKnowWorkbookPath() {
		return lastKnowWorkbookPath;
	}

	public void setLastKnowWorkbookPath(String lastKnowWorkbookPath) {
		this.lastKnowWorkbookPath = lastKnowWorkbookPath;
	}

}