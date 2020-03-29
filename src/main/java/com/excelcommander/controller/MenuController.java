package com.excelcommander.controller;

import com.excelcommander.model.Project;
import com.excelcommander.service.ProjectService;
import com.excelcommander.util.DialogHelper;
import com.excelcommander.util.SpreadSheetUtils;
import com.excelcommander.util.WindowUtils;
import com.jfoenix.controls.JFXToolbar;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.metamodel.util.FileResource;
import org.apache.poi.ss.usermodel.Workbook;
import org.controlsfx.control.PlusMinusSlider;
import org.controlsfx.control.spreadsheet.SpreadsheetView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author HGDIV
 * <p>
 * MenuController controls and handles events for the Main File Menu and other drop-down menus
 * Is registered as a Controller in with Spring
 */
@Controller
public class MenuController extends ParentController {

    public static final String ROOT_FXML = "/fxml/Root_View.fxml";
    private static final String PROJECT_SEARCH = "/fxml/FileSysNavWindow.fxml";

    Logger logger = LoggerFactory.getLogger(MenuController.class);


    private ApplicationContext ctx;
    private Project project;
    private HostServices hostServices;
    ProjectService projectService;


    @FXML
    private SpreadsheetView ssView;
    @FXML
    private Tab tab1;
    @FXML
    protected AnchorPane fillPane;

    @FXML
    private TabPane tabPane;

    @FXML
    private RadioMenuItem mainToolbarRadioButton;

    @FXML
    private JFXToolbar jfxToolbar;

    @FXML
    protected PlusMinusSlider zoomSlider;


    @Override
    public <T> void init(Stage stage, HashMap<String, T> parameters) {
        super.init(stage, parameters);

    }


    @FXML
    private void handleSaveWorkbook(ActionEvent event) throws FileNotFoundException {

        DialogHelper.showAlert(
                "You must first open or create a new projectXml in order to perform the save projectXml action",
                "Please create a projectXml first before saving it", "Warning", AlertType.WARNING);
    }

    @FXML
    private void handleNewWorkbook(ActionEvent event) {

        File filePath = DialogHelper.showSaveFilePrompt("New Workbook", ".xlsx", "new-workbook", StageStyle.UTILITY);
        FileResource fileResource = new FileResource(filePath);
        try {
            SpreadSheetUtils.createBlankWorkbook(fileResource);
            project.setFileResource(fileResource);
            DialogHelper.showSimpleAlert("You created a new Workbook and added it to your project", AlertType.CONFIRMATION);

            tab1.setText("New Sheet");

        } catch (Exception e) {
            DialogHelper.showSimpleAlert("A file with this name may already exist", AlertType.ERROR);
        }

    }

    /**
     * @param event ActionEvent triggers open project.
     *              An application modal window opens with JFXListView
     *              populated with local project names
     */
    @SuppressWarnings("unchecked")
    @FXML
    private void handleOpenProject(ActionEvent event) {

        final String prjWindowTitle = "Choose the project you would like to open";
        HashMap<String, List<?>> params = new HashMap<>();

        projectService.findAll(event1 -> {

            List<Project> projectList = (List<Project>) event1.getSource().getValue();

            params.put("project_list", projectList);

            try {
                WindowUtils.open(PROJECT_SEARCH, prjWindowTitle, new HashMap<>(params), Modality.APPLICATION_MODAL);
            } catch (Exception ex) {
                ex.printStackTrace();
                DialogHelper.showAndWaitAlert("Something went wrong opening the window"
                        , "Could not open resource", "My Bad", AlertType.ERROR);
            }

        }, null);

    }

    /**
     * @param event File menu import -> from Excel file clicked
     */
    @FXML
    private void handleImportFromExcel(ActionEvent event) {
        HashMap<String, Object> params = new HashMap<>();
        File excelFile = DialogHelper.showFilePrompt("Import from Excel workbook: ", ".xlsx");
        try {
            Workbook wb = SpreadSheetUtils.loadFromFile(excelFile);
            WindowUtils.renderNewSheet(ssView, wb, tab1);

        } catch (Exception e) {
            System.out.println("Problem loading from file");
        }

    }


    @FXML
    private void handleAboutSuperCommander(ActionEvent event) {

        String uri = "https://github.com/harrydulaney/commander-excel/";
        hostServices.showDocument(uri);

    }

    @FXML
    protected void handleExitCommander(ActionEvent event) {

        Platform.exit();
    }

    private void saveProject() throws Exception {
        projectService.save(project,

                event -> {

                }

                , event -> {

                });

    }


    @Override
    protected void onClose() {
        try {
            projectService.save(project, null, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Autowired
    public void setAppCtx(ConfigurableApplicationContext ctx) {

        this.ctx = ctx.getParent();
    }


    @Autowired
    private void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    @Autowired
    protected void setProject(Project project) {
        this.project = project;
    }


}