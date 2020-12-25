package com.spreadsheetbuddy.controller;

import com.spreadsheetbuddy.model.Project;
import com.spreadsheetbuddy.util.SsUtil;
import com.spreadsheetbuddy.util.WbUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.controlsfx.control.spreadsheet.Grid;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>
 * WorkbookController controls and encapsulates logic for handling workbooks/spreadsheets/tabs
 * </p>
 *
 * @author Harry Dulaney
 */
@Component
@FxmlView("/fxml/workbookview.fxml")
public class WorkbookController {
    private final Logger logger = LoggerFactory.getLogger(WorkbookController.class);
    private final FxWeaver fxWeaver;

    private static Project project;
    private XSSFWorkbook poiWorkbook;
    private FxControllerAndView<WorkbookController, TabPane> sheetControlView;
    private XSSFSheet poiSheet;

    @Value("${sheet-buddy.start.title}")
    String defaultTitle;
    @FXML
    protected TabPane tabPane;
    @FXML
    protected Tab tab1;

    @Autowired
    public WorkbookController(FxWeaver fxWeaver,
                              @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") FxControllerAndView<WorkbookController,
                                      TabPane> sheetControlView) {
        this.fxWeaver = fxWeaver;
        this.sheetControlView = sheetControlView;
    }

    @FXML
    public void initialize() {

        /* Initialize blank workbook */
        poiWorkbook = XSSFWorkbookFactory.createWorkbook();

        poiWorkbook.createSheet(tab1.getText());
        poiSheet = poiWorkbook.getSheet(tab1.getText());

    }


//    public LinkedList<XSSFSheet> getSheets() {
//        return sheets;
//    }
//
//    public void setSheets(LinkedList<XSSFSheet> sheets) {
//        this.sheets = sheets;
//    }
//

//    public XSSFWorkbook getPoiWorkbook() {
//        return poiWorkbook;
//    }

    /**
     * Sets the reference for the current workbook to operate on and reads it / binds it
     * into the view.
     *
     * @param poiWorkbook The XSSFWorkbook to use to display in the view.
     */
    public void setPoiWorkbook(XSSFWorkbook poiWorkbook) {
        this.poiWorkbook = poiWorkbook;
        //TODO: Read in the workbook and update the view


    }

//    public XSSFSheet getPoiSheet() {
//        return poiSheet;
//    }


    protected XSSFWorkbook getPoiWorkbook() {
        return poiWorkbook;
    }

    public XSSFSheet getPoiSheet() {
        return poiSheet;
    }

    public void setPoiSheet(XSSFSheet poiSheet) {
        this.poiSheet = poiSheet;
    }


    void setProject(Project project) {
        WorkbookController.project = project;
    }

    void addSpreadsheet() {
        String tabName = defaultTitle;
        ObservableList<Tab> tabs = tabPane.getTabs();
        for (Tab tab : tabs) {
            if (tab.getId().contains(defaultTitle)) {
                if (!tab.getId().contains("1")) {
                    tabName += "1";
                    break;
                }
            }
        }
        Tab tab = new Tab(tabName);

//        tab.setContent();
    }
}
