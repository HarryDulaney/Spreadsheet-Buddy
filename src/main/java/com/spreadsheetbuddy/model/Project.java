package com.spreadsheetbuddy.model;

import java.util.List;
import java.util.Objects;

/**
 * Project model holds references to a user and any dependencies and resources
 * they interact with through the course of running this application.
 */
public class Project {

    private final String projectId;
    private String mostRecentFile;
    private List<String> recentFiles;


    public Project() {
        projectId = "SSbuddy.id." + System.getProperty("user.name");
    }

    public Project(String projectId) {
        this.projectId = projectId;
    }

    public Project(String projectId, String recentFile) {
        this.projectId = projectId;
        this.mostRecentFile = recentFile;

    }

    public String getMostRecentFile() {
        return mostRecentFile;

    }

    public void setMostRecentFile(String openWorkbookFilePath) {
        this.mostRecentFile = openWorkbookFilePath;
    }

    public String getProjectId() {
        return this.projectId;
    }

    public List<String> getRecentFiles() {
        return recentFiles;
    }

    public Project setRecentFiles(List<String> recentFiles) {
        this.recentFiles = recentFiles;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return projectId.equals(project.projectId) &&
                mostRecentFile.equals(project.mostRecentFile);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.projectId);
        hash = 79 * hash + Objects.hashCode(this.mostRecentFile);
        return hash;
    }


    @Override
    public String toString() {
        return "Project [ projectId=" + projectId + ", recentFile=" + mostRecentFile + "]";
    }


}
