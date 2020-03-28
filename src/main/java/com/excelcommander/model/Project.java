package com.excelcommander.model;

import org.apache.metamodel.util.FileResource;

import javax.persistence.*;

/**
 * Project is the primary User object
 */
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String projectName;
    @Column(name = "file_resource")
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

    public FileResource getFileResource() {
        return fileResource;
    }

    public void setFileResource(FileResource fileResource) {
        this.fileResource = fileResource;
    }


}