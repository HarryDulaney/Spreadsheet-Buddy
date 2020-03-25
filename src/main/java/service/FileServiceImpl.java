package service;

import com.excelcommander.model.FileSys;
import com.excelcommander.model.Project;
import com.excelcommander.repository.ProjectRepository;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import org.apache.metamodel.util.FileResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.File;


@Service("fileService")
public class FileServiceImpl extends AbstractFileService<ProjectRepository> implements FileService {

    final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    private ProjectRepository repository;


    @Autowired
    private FileServiceImpl(ProjectRepository repository) {
        super(repository);
        this.repository = repository;
    }


    @Override
    public javafx.concurrent.Service<File[]> getDirectoryFiles(FileSys fileSys,EventHandler<WorkerStateEvent> onSuccess,
                                                               EventHandler<WorkerStateEvent> beforeStart) {
        return createService(new Task<File[]>() {
            protected File[] call() {
                final File file = fileSys.getFileResource().getFile();

                return file.listFiles();

            }
        }, onSuccess, beforeStart);

    }


}


