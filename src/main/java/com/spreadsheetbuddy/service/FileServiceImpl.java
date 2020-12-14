package com.spreadsheetbuddy.service;


import org.springframework.stereotype.Component;
import java.io.File;

@Component
public class FileServiceImpl implements FileService {
    @Override
    public File getTempFile() {
        return new File(System.getProperties().getProperty("java.io.tmpdir"));
    }
}

