package com.excelcommander.service;

import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetView;
import org.hibernate.jdbc.Work;


public interface CellService {

    Service<String> getStyledCell(Cell cell, EventHandler<WorkerStateEvent> onSuccess,
                                        EventHandler<WorkerStateEvent> beforeStart) throws Exception;



    Service<Cell> getXSSFCell(SpreadsheetCell spreadsheetCell, EventHandler<WorkerStateEvent> onSuccess,
                              EventHandler<WorkerStateEvent> beforeStart) throws Exception;

    CellType getCellType(Cell cell);
    String getStyledDateCell(Cell cell);

}
