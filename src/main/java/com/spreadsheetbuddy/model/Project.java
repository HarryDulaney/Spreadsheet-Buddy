package com.spreadsheetbuddy.model;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;

import javax.persistence.*;
import java.util.Objects;

/**
 * Project model holds references to a user and any dependencies and resources
 * they interact with through the course of running this application.
 */
@Entity
@Table(name = "project")
public class Project {

    @Id
    @Column(name = "project_id")
    private final String projectId;


    @Transient
    private String openFile;

//    @OneToMany(mappedBy = "ownerId")
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
//    private List<RecentFile> recentFiles;

    public Project() {
        projectId = "SSbuddy.id." + System.getProperty("user.name");
    }

    public Project(String projectId, String openFile) {
        this.projectId = projectId;
        this.openFile = openFile;

    }
//    public List<RecentFile> getRecentFiles() {
//        return recentFiles;
//    }
//
//    public void setRecentFiles(List<RecentFile> recentFiles) {
//        this.recentFiles = recentFiles;
//    }
//

    public String getOpenFile() {
        return openFile;

    }

    public void setOpenFile(String openWorkbookFilePath) {
        this.openFile = openWorkbookFilePath;
    }

    public String getProjectId() {
        return this.projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return projectId.equals(project.projectId) &&
                openFile.equals(project.openFile);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.projectId);
        hash = 79 * hash + Objects.hashCode(this.openFile);
        return hash;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Project { ");
        sb.append("id=").append(projectId);
        sb.append("openFile(most recent known)=").append(FilenameUtils.getName(openFile));
        sb.append(" }");
        return sb.toString();
    }


}
