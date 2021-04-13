package com.spreadsheetbuddy.service;


import com.spreadsheetbuddy.dao.ProjectDao;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Service("fileService")
public class FileServiceImpl implements FileService {


    @Autowired
    ProjectDao projectDao;


    @Override
    public XSSFWorkbook getWorkbook(File file) throws IOException, InvalidFormatException {
        return new XSSFWorkbook(file);

    }

    @Override
    public List<String> getRecentFiles(String id) {
        return projectDao.getRecentFiles(id);
    }

    @Override
    public void setRecentFile(String file, String id) {
        projectDao.insertRecentFile(file, id);

    }

}

