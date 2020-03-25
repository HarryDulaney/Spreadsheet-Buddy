package service;

import java.io.File;

import com.excelcommander.model.FileSys;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public interface FileService {

    Service<File[]> getDirectoryFiles(FileSys fileSys, EventHandler<WorkerStateEvent> onSuccess,
                                      EventHandler<WorkerStateEvent> beforeStart);

    void onClose();
}
