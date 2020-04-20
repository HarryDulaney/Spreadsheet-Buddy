package com.excelcommander.service;

import com.excelcommander.model.WorkbookModel;
import com.excelcommander.util.SpreadSheetUtils;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;
import org.controlsfx.control.spreadsheet.SpreadsheetView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.text.Format;

/**
 * @author HGDIV
 */


public class CellServiceImpl extends ParentService implements CellService {

    final Logger logger = LoggerFactory.getLogger(CellServiceImpl.class);




    private CellServiceImpl() {
    }

    @Override
    public javafx.concurrent.Service<String> getStyledCell(Cell cell, EventHandler<WorkerStateEvent> onSuccess, EventHandler<WorkerStateEvent> beforeStart) throws Exception {
        return null;
    }

    @Override
    public javafx.concurrent.Service<Cell> getXSSFCell(SpreadsheetCell spreadsheetCell,
                                                       EventHandler<WorkerStateEvent> onSuccess,
                                                       EventHandler<WorkerStateEvent> beforeStart) throws Exception {
        return null;
    }

    @Override
    public CellType getCellType(Cell cell) {

        return null;
    }

    @Override
    public String getStyledDateCell(Cell cell) {
        return null;
    }


}
