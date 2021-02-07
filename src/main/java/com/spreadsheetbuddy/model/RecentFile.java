//package com.spreadsheetbuddy.model;
//
//import javax.persistence.*;
//import java.io.File;
//
///**
// * The type Recent files.
// */
//@Entity
//@Table(name = "recent_files_table")
//public class RecentFile {
//    final static String RECENT_FILE_COLUMN = "file_path1";
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int id;
//
//
//    private String projectId;
//
//    @Column(name = RECENT_FILE_COLUMN)
//
//    private String recentFileAbsolutePath;
//
//    public RecentFile() {
//        ownerId = System.getProperty("user.name");
//    }
//
//
//    public String getRecentFilepath() {
//        return recentFileAbsolutePath;
//    }
//
//    public void setRecentFilepath(String recentFileAbsolutePath) {
//        this.recentFileAbsolutePath = recentFileAbsolutePath;
//    }
//
//    @Transient
//    public File getAsFile(String referenceId) throws IllegalAccessException {
//        if (this.ownerId.equals(referenceId)) {
//            return new File(recentFileAbsolutePath);
//        } else {
//            throw new IllegalAccessException("This feature can only be accessed by the original owner.");
//        }
//    }
//}
//
//
//
