package com.spreadsheetbuddy.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.File;

/**
 * The type Recent files.
 */
@Entity
public class RecentFiles {

    private String ownerId;

    private File workBookFile;

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Id
    public String getOwnerId() {
        return ownerId;
    }

    public File getWorkBookFile() {
        return workBookFile;
    }

    public void setWorkBookFile(File workBookFile) {
        this.workBookFile = workBookFile;
    }


}
