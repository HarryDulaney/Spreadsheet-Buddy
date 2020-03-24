package com.excelcommander.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excelcommander.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long >{
	
    Project findByProjectName(String projectName);

    Project findById(long id);
	
}
