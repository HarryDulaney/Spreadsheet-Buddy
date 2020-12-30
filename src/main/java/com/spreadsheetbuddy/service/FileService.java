package com.spreadsheetbuddy.service;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FileService {
    XSSFWorkbook getWorkbook(File file) throws IOException, InvalidFormatException;

    List<File> getRecentFiles(String id);
}
