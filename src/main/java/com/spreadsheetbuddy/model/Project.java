package com.spreadsheetbuddy.model;

import org.apache.poi.ss.usermodel.Workbook;
import javax.persistence.Transient;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Project model holds references to a user and any dependencies and resources
 * they interact with through the course of running this application.
 */
@Entity
public class Project {
    @Id
    private String ownerId;

    private String currentWorkbookFile;

    @Transient
    private Workbook workbook;


    public Project(String ownerId) {
        this.ownerId = ownerId;
    }

    public Project() {

    }

    public String getCurrentWorkbookFile() {
        return currentWorkbookFile;
    }

    public void setCurrentWorkbookFile(String currentWorkbookFile) {
        this.currentWorkbookFile = currentWorkbookFile;

    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }


    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Id
    public String getOwnerId() {
        return ownerId;
    }
}
