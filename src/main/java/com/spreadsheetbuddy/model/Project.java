package com.spreadsheetbuddy.model;

import javafx.scene.paint.Color;

import java.util.List;
import java.util.Objects;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Project model holds references to a user and any dependencies and resources
 * they interact with through the course of running this application.
 */
public class Project {

    private final String projectId;
    private String mostRecentFile;
    private List<String> recentFiles;
    private String workingDirectoryPath;
    private Preferences userPreferences;

    public Project() {
        projectId = "SSbuddy.id." + System.getProperty("user.name") + "." + this.hashCode();
        loadPreferences();
    }

    /**
     * Persist User's Preferences to memory
     */
    private void setPreferences() {
        userPreferences = Preferences.userNodeForPackage(getClass());
        userPreferences.put("DEFAULT_WORKING_DIRECTORY", workingDirectoryPath);

        try {
            userPreferences.sync();
        } catch (BackingStoreException bse) {
            System.console().printf(bse.toString());
        }

    }

    /**
     * Load Preferences into User (Handled internally by ParentController)
     */
    private void loadPreferences() {
        userPreferences = Preferences.userNodeForPackage(getClass());
        this.setWorkingDirectoryPath(userPreferences.get("DEFAULT_WORKING_DIRECTORY", System.getProperty("user.dir")));

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

    public String getWorkingDirectoryPath() {
        return workingDirectoryPath;
    }

    public void setWorkingDirectoryPath(String workingDirectoryPath) {
        this.workingDirectoryPath = workingDirectoryPath;
        setPreferences();
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
        hash = 7779 * hash + Objects.hashCode(this.projectId);
        return hash;
    }


    @Override
    public String toString() {
        return "Project [ projectId=" + projectId + ", recentFile=" + mostRecentFile + "]";
    }


}
