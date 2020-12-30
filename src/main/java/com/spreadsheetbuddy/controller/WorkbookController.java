package com.spreadsheetbuddy.controller;

import com.spreadsheetbuddy.model.Project;
import com.spreadsheetbuddy.util.WbUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.controlsfx.control.spreadsheet.GridBase;
import org.controlsfx.control.spreadsheet.SpreadsheetView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    protected int numSheets;
    private final Logger logger = LoggerFactory.getLogger(WorkbookController.class);
    private final FxWeaver fxWeaver;
    private XSSFWorkbook currentWorkbook;

    /* References the default sheetview */
    private final FxControllerAndView<SpreadsheetController, SpreadsheetView> sheetControlView;
    private final List<FxControllerAndView<SpreadsheetController, SpreadsheetView>> sheetControls = new ArrayList<>();

    @Value("${sheet-buddy.start.title}")
    String defaultTitle;
    @FXML
    protected TabPane tabPane;
    @FXML
    protected Tab tab1;

    VBox rootNode;

    @Autowired
    public WorkbookController(FxWeaver fxWeaver,
                              @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") FxControllerAndView<SpreadsheetController,
                                      SpreadsheetView> sheetControlView) {
        this.fxWeaver = fxWeaver;
        this.sheetControlView = sheetControlView;
        sheetControls.add(sheetControlView);
    }

    @FXML
    public void initialize() {
        /* Initialize blank workbook */
        currentWorkbook = XSSFWorkbookFactory.createWorkbook();
        sheetControlView.getController().poiSheet = currentWorkbook.createSheet(tab1.getText());
        sheetControlView.getController().sheetNumber = 1;
    }

    protected void updateWorkbookView(XSSFWorkbook workbook) {
        try {
            currentWorkbook.close();
            currentWorkbook = workbook;
            numSheets = currentWorkbook.getNumberOfSheets();
            List<GridBase> contents = WbUtil.mapWorkbookGrid(workbook);
            sheetControls.clear();
            tabPane.getTabs().clear();

            for (int i = 0; i < contents.size(); i++) {

                Tab tab = new Tab(currentWorkbook.getSheetName(i));
                SpreadsheetController.turnOffDefaultInit = true;

                FxControllerAndView<SpreadsheetController, SpreadsheetView> sheetControllerView =
                        fxWeaver.load(SpreadsheetController.class);

                sheetControls.add(sheetControllerView);
                int finalI = i;
                sheetControllerView.getView().ifPresent(ss -> {
                    ss.setGrid(contents.get(finalI));
                });
                tab.setContent(sheetControllerView.getController().ssView);
                this.tabPane.getTabs().add(tab);

            }
            this.rootNode.getChildren().removeIf(node -> node instanceof TabPane);
            rootNode.getChildren().add(tabPane);

        } catch (IOException ioException) {
            ioException.printStackTrace();
            logger.error("Exception thrown while trying to close the current Workbook. -> " + ioException.getMessage());

        }

    }


    protected XSSFWorkbook getCurrentWorkbook() {
        return currentWorkbook;
    }

    /**
     * Adds a new blank spreadsheet tab to the workbook view
     * @param rootNode VBox containing the currently visible TabPane.
     */
    protected void addSpreadsheet(VBox rootNode) {
        String tabTitle = defaultTitle.concat(String.valueOf(numSheets + 1));
        Tab tab = new Tab(tabTitle);

        FxControllerAndView<SpreadsheetController, SpreadsheetView> sheetControlView =
                fxWeaver.load(SpreadsheetController.class);
        tab.setContent(sheetControlView.getController().ssView);

        sheetControls.add(sheetControlView);

        numSheets += 1;
        System.out.println("Number of current sheets is " + numSheets);
        sheetControlView.getController().sheetNumber = numSheets;

        this.tabPane.getTabs().add(tab);
        rootNode.getChildren().set(rootNode.getChildren().size() - 1, tabPane);

    }

    void close() throws IOException {
        //Perform close logic
        //TODO: Update and Save the workbooks content's and add it to a Recentfiles instance
        currentWorkbook.close();
    }
}
