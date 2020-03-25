package com.excelcommander.repository;

import com.excelcommander.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long > {
	
    Project findByProjectName(String projectName);
    Project findById(long id);

	
}
