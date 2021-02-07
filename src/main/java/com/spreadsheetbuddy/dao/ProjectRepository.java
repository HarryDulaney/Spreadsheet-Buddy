package com.spreadsheetbuddy.dao;

import com.spreadsheetbuddy.model.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, String> {
    Project getProjectByProjectId(String projectId);

    void deleteByProjectId(String projectId);

    Boolean existsByProjectId(String projectId);

}

