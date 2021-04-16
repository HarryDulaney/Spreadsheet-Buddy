package com.spreadsheetbuddy.model;

import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Project model holds references to a user and any dependencies and resources
 * they interact with through the course of running this application.
 */
public class Project {
    Logger logger = LoggerFactory.getLogger(Project.class);

    private final String projectId;
    private String mostRecentFile;
    private List<String> recentFiles;
    private String workingDirectoryPath;
    private static Preferences userPreferences;
    private static final String DEFAULT_WD = "DEFAULT_WORKING_DIRECTORY";

    public Project() {
        projectId = "SSbuddy.id." + System.getProperty("user.name") + "." + this.hashCode();
    }

    public Project(String projectId, List<String> recentFiles) {
        this.projectId = projectId;
        this.recentFiles = recentFiles;
        loadPreferences();
    }

    /**
     * Persist User's Preferences to memory
     */
    private void setPreferences() {
        userPreferences = Preferences.userNodeForPackage(Project.class);
        userPreferences.put("DEFAULT_WORKING_DIRECTORY", workingDirectoryPath);
        try {
            userPreferences.flush();
            userPreferences.sync();
        } catch (BackingStoreException bse) {
            System.console().printf(bse.toString());
        }

    }

    /**
     * Load Preferences into User (Handled internally by ParentController)
     */
    private void loadPreferences() {
        userPreferences = Preferences.userNodeForPackage(Project.class);
        workingDirectoryPath = userPreferences.get(DEFAULT_WD, System.getProperty("user.home"));
        logger.info("workingDirectoryPath set: " + workingDirectoryPath);

    }

    public void persistPreferences() {
        setPreferences();
    }


    public String getMostRecentFile() {
        return mostRecentFile;

    }

    public void setMostRecentFile(String openWorkbookFilePath) {
        this.mostRecentFile = openWorkbookFilePath;
        recentFiles.add(mostRecentFile);
    }

    public String getProjectId() {
        return this.projectId;
    }


    public List<String> getRecentFiles() {
        return recentFiles;
    }

    public void setRecentFiles(List<String> recentFiles) {
        this.recentFiles = recentFiles;
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
