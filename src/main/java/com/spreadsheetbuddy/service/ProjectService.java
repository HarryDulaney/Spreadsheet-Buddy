package com.spreadsheetbuddy.service;
import com.spreadsheetbuddy.model.Project;

import java.util.List;

public interface ProjectService {

    Project getProjectById(String id);

    void delete(Project project);

    void insertProject(Project project);



}
