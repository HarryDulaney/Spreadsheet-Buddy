package com.excelcommander.model;

import org.apache.metamodel.util.FileResource;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;
import java.util.List;

/**
 * Project is the primary User object
 * An instantiated project is by default Open (true)
 */

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String projectName;
    private boolean open;

    @Embedded
    private WorkbookModel workbookModel;



    public Project() {
        this.open = true;
    }

    public Project(String projectName) {
        this.projectName = projectName;
        this.open = true;

    }

    public String getProjectName() {
        return projectName;
    }


    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public WorkbookModel getWorkbookModel() {
        return workbookModel;
    }

    public void setWorkbookModel(WorkbookModel workbookModel) {
        this.workbookModel = workbookModel;
    }




}