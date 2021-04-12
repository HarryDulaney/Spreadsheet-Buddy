package com.spreadsheetbuddy.service;


import com.spreadsheetbuddy.dao.ProjectDao;
import com.spreadsheetbuddy.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectDao projectDao;


    @Override
    public Project getProjectById(String id) {
        return projectDao.getProjectById(id);
    }

    @Override
    public void delete(Project project) {
        projectDao.deleteProject(project);
    }


    @Override
    public void insertProject(Project project) {
        projectDao.insertProject(project);
    }


}