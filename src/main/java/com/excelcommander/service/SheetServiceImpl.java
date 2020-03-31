package com.excelcommander.service;

import com.excelcommander.model.WorkbookModel;
import com.excelcommander.util.SpreadSheetUtils;
import com.excelcommander.util.WindowUtils;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author HGDIV
 */


//@Service("sheetService")
public class SheetServiceImpl extends ParentService implements SheetService {

    final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    private SheetServiceImpl() {

    }


}
