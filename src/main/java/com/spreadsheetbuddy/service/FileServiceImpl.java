package com.spreadsheetbuddy.service;


import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Service("fileService")
public class FileServiceImpl implements FileService {

    @Override
    public File getTempFile() {
        return new File(System.getProperties().getProperty("java.io.tmpdir"));

    }

    @Override
    public List<File> getRecentFiles(String id) {
        return null;
    }

}

