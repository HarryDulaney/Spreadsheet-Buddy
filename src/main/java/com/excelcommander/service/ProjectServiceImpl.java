package com.excelcommander.service;

import com.excelcommander.model.Project;
import com.excelcommander.repository.ProjectRepository;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import org.apache.metamodel.util.FileResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Service("projectService")
public class ProjectServiceImpl extends AbstractCrudService<Project, ProjectRepository> implements ProjectService {

    final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    private ProjectRepository repository;


    @Autowired
    private ProjectServiceImpl(ProjectRepository repository) {
        super(repository);
        this.setRepository(repository);
    }

    public ProjectRepository getRepository() {
        return repository;
    }


    public void setRepository(ProjectRepository repository) {
        this.repository = repository;
    }


    @Override
    public javafx.concurrent.Service<File[]> getDirectoryFiles(FileResource fileResource, EventHandler<WorkerStateEvent> onSuccess,
                                                               EventHandler<WorkerStateEvent> beforeStart) {
        return createService(new Task<File[]>() {
            protected File[] call() {
                final File file = fileResource.getFile();

                return file.listFiles();

            }
        }, onSuccess, beforeStart);

    }

    @Override
    public javafx.concurrent.Service<Project> save(Project project, EventHandler<WorkerStateEvent> onSuccess, EventHandler<WorkerStateEvent> beforeStart) throws Exception {
        return createService(new Task<Project>() {
            protected Project call() throws Exception {
                return repository.save(project);
            }
        }, onSuccess, beforeStart);
    }

    @Override
    public javafx.concurrent.Service<Project> findByProjectName(String projectName, EventHandler<WorkerStateEvent> onSuccess, EventHandler<WorkerStateEvent> beforeStart) {
        return createService(new Task<Project>() {
            protected Project call() throws Exception {
                return repository.findByProjectName(projectName);
            }
        }, onSuccess, beforeStart);
    }


    @Override
    public javafx.concurrent.Service<Void> delete(Project project, EventHandler<WorkerStateEvent> onSuccess,
                                                  EventHandler<WorkerStateEvent> beforeStart) throws Exception {
        return createService(new Task<Void>() {
            protected Void call() throws Exception {
                repository.delete(project);
                return null;
            }
        }, onSuccess, beforeStart);
    }

    @Override
    public javafx.concurrent.Service<List<Project>> findAll(EventHandler<WorkerStateEvent> onSuccess,
                                                            EventHandler<WorkerStateEvent> beforeStart) {

        return createService(new Task<List<Project>>() {
            protected List<Project> call() throws Exception {
    
                return repository.findAll();
            }
        }, onSuccess, beforeStart);
    }

    @Override
    public javafx.concurrent.Service<Project> findById(long id, EventHandler<WorkerStateEvent> onSuccess,
                                                       EventHandler<WorkerStateEvent> beforeStart) throws Exception {

        return createService(new Task<Project>() {
            protected Project call() throws Exception {
                return repository.findById(id);
            }
        }, onSuccess, beforeStart);
    }

}


