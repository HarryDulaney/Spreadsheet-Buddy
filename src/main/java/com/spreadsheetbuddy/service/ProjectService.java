package com.spreadsheetbuddy.service;

import com.spreadsheetbuddy.model.Project;

import java.util.List;

public interface ProjectService {

    Project getProject(String id);
    List<String> getRecentFiles(String id);
    void save(Project project);
    void delete(Project project);
    Boolean exists(String id);

}
