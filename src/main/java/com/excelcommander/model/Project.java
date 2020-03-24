package com.excelcommander.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Project {
	
	private String projectName;
	private String backupFile;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;



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



	public String getBackupFile() {
		return backupFile;
	}

	public void setBackupFile(String backupFile) {
		this.backupFile = backupFile;
	}
}