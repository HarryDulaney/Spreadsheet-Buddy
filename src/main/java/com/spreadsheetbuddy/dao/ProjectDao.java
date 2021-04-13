package com.spreadsheetbuddy.dao;

import com.spreadsheetbuddy.model.Project;

import java.util.List;

public interface ProjectDao {
    void insertProject(Project project);

    void deleteProject(Project project);

    Project getProjectById(String id);

    List<String> getRecentFiles(String id);

    void insertRecentFile(String file, String id);

    Boolean projectExistsById(String id);

}
