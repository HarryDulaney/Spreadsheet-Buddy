package com.spreadsheetbuddy.service;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Service("fileService")
public class FileServiceImpl implements FileService {


    @Override
    public XSSFWorkbook getWorkbook(File file) throws IOException, InvalidFormatException {
        return new XSSFWorkbook(file);

    }

    @Override
    public List<File> getRecentFiles(String id) {
        return null;
    }

}

