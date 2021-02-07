package com.spreadsheetbuddy.service;


import com.spreadsheetbuddy.model.Project;
import com.spreadsheetbuddy.dao.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public Project getProject(String projectId) {
        return repository.getProjectByProjectId(projectId);
    }

    @Override
    public List<String> getRecentFiles(String projectId) {
        return null;
    }

    @Override
    public void save(Project project) {
        repository.save(project);

    }

    @Override
    public void delete(Project project) {
        repository.delete(project);
    }

    @Override
    public Boolean exists(String projectId) {
        return repository.existsByProjectId(projectId);
    }
}
