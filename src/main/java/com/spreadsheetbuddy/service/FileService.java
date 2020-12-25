package com.spreadsheetbuddy.service;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.util.List;

public interface FileService {
    File getTempFile();
    List<File> getRecentFiles(String id);
}
