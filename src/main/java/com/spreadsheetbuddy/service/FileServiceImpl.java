package com.spreadsheetbuddy.service;


import javafx.concurrent.Task;
import org.springframework.stereotype.Service;

import java.io.File;

@Service("fileService")
public class FileServiceImpl implements FileService {

    @Override
    public File getTempFile() {
        return new File(System.getProperties().getProperty("java.io.tmpdir"));

    }

    @Override
    public File getFileByUserChoice() {
        return null;
    }
}

