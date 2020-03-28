package com.excelcommander.controller;

import com.excelcommander.model.Project;
import com.excelcommander.model.WorkbookCE;
import com.excelcommander.service.ProjectService;
import com.excelcommander.util.DialogHelper;
import com.excelcommander.util.SpreadSheetUtils;
import com.excelcommander.util.WindowUtils;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.metamodel.util.FileResource;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author HGDIV
 */

/**
 * MenuController controls and handles events for the toolbars and dropdown File menu
 */
@Controller
public class MenuController extends ParentController {

    public static final String ROOT_FXML = "/fxml/Root_View.fxml";

    Logger logger = LoggerFactory.getLogger(MenuController.class);


    private ApplicationContext ctx;
    private static Project project;
    private HostServices hostServices;
    ProjectService projectService;


    @FXML
    protected Pane fillPane;

    @FXML
    protected TabPane tabPane;

    @FXML
    protected RadioMenuItem mainToolbarRadioButton;


    @Override
    public <T> void init(Stage stage, HashMap<String, T> parameters) {
        super.init(stage, parameters);

    }


    @FXML
    private void handleSaveWorkbook(ActionEvent event) throws FileNotFoundException {


        Alert alrt = new Alert(AlertType.WARNING);
        alrt.setHeaderText("Please create a projectXml first before saving it");
        alrt.setContentText(
                "You must first open or create a new projectXml in order to perform the save projectXml action");
        alrt.show();


    }

    @FXML
    private void handleNewWorkbook(ActionEvent event) {

        File filePath = DialogHelper.showSaveFilePrompt("New Workbook", ".xlsx", "new-workbook", StageStyle.UTILITY);
        FileResource fileResource = new FileResource(filePath);
        try {
            SpreadSheetUtils.createBlankWorkbook(fileResource);
            project.setFileResource(fileResource);

        } catch (Exception e) {
            DialogHelper.showSimpleAlert("A file with this name may already exist", AlertType.ERROR);
        }

    }

    @FXML
    private void handleOpenProject(ActionEvent event) throws IOException {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose the project you would like to open");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".xml", "*.xml"));
        File toOpen = chooser.showOpenDialog(new Stage(StageStyle.UTILITY));

        try {
            openProject("project");

        } catch (Exception e) {
            DialogHelper.showAndWaitAlert("Sorry we were not able to open the selected file, please try again"
                    , "Could not open the file", "Something Went Wrong", AlertType.ERROR);

        }
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
            params.put(TabPaneController.WORKBOOK, wb);

            try {
                WindowUtils.replaceFxmlOnWindow(fillPane, TabPaneController.TABPANE_FXML, stage, params);
            } catch (Exception e) {
                e.printStackTrace();
            }
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

    @FXML
    protected void handleBlankWorkbook(ActionEvent event) {


    }

    private void createNewProject() {


    }

    private void saveProject() {

    }

    private void openProject(String projectName) {


        projectService.findByProjectName(projectName, event -> {

            try {
                setProject((Project) event.getSource().getValue());
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        }, null);


        try {

//			showProject();

        } catch (Exception e) {

            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Cannot open project");
            alert.setHeaderText("Something Went Wrong");
            alert.setContentText("Please double check you are attempting to open the correct file and try again");
            alert.showAndWait();

        }
    }


    @Override
    protected void onClose() {
        // TODO Auto-generated method stub

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
    public static void setProject(Project project) {
        MenuController.project = project;
    }


}