package com.excelcommander.service;

import java.io.File;

import com.excelcommander.model.Project;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import org.apache.metamodel.util.FileResource;

public interface ProjectService extends IBaseService<Project> {
    String STAND_ALONE_MODE = "STAND_ALONE_MODE";

    Service<File[]> getDirectoryFiles(FileResource fileResource, EventHandler<WorkerStateEvent> onSuccess,
                                      EventHandler<WorkerStateEvent> beforeStart);

    Service<Project> findByProjectName(String projectName, EventHandler<WorkerStateEvent> onSuccess,
                                       EventHandler<WorkerStateEvent> beforeStart) throws Exception;

    Service<Boolean> standAloneMode(EventHandler<WorkerStateEvent> onSuccess,
                                    EventHandler<WorkerStateEvent> beforeStart) throws Exception;

    Project activeProject();

    void setActiveProject(Project project);


    void onClose();
}
