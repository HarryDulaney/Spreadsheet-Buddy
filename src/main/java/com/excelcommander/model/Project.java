package com.excelcommander.model;

import org.apache.metamodel.util.FileResource;

import javax.persistence.*;

/**
 * Project identifies the user and is created only after explicitly saving a project
 */
@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String projectName;

	private FileResource fileResource;

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
	

}