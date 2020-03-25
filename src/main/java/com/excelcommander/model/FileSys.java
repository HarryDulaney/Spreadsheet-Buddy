package com.excelcommander.model;

import org.apache.metamodel.util.FileResource;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Embeddable
public class FileSys {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private FileResource fileResource;

    public FileSys(){

    }

    public FileResource getFileResource() {
        return fileResource;
    }

    public void setFileResource(FileResource fileResource) {
        this.fileResource = fileResource;
    }

}
