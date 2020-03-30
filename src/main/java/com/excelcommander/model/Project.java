package com.excelcommander.model;

import org.apache.metamodel.util.FileResource;

import javax.persistence.*;
import java.util.List;

/**
 * Project is the primary User object
 * An instantiated project is by default Open (true)
 */

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String projectName;
    private boolean open;

    /**
     * The most recently loaded FileResource /
     * most important to have quickly available
     */
    @Column(name = "file_resource")
    private FileResource fileResource;

    @ElementCollection
    private List<FileResource> fileResourceList;

    public Project() {
        this.open = true;
    }

    public Project(String projectName) {
        this.projectName = projectName;
        this.open = true;

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
        if (this.fileResource.getFile() != null) {
            fileResourceList.add(fileResource);
        }
        this.fileResource = fileResource;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }



}