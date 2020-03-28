package com.excelcommander.repository;

import com.excelcommander.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
	
    Project findByProjectName(String projectName);
    Project findById(long id);

    List<Project> findAllByProjectNameAndId(String projectName,Long projectId);

	
}
