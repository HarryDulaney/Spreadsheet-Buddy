package com.excelcommander.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import com.excelcommander.util.DialogHelper;
import com.excelcommander.util.SpreadSheetUtils;
import javafx.application.HostServices;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.apache.metamodel.util.FileResource;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.atp.WorkdayCalculator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.spreadsheet.SpreadsheetView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import com.excelcommander.model.Project;
import com.excelcommander.util.WindowUtils;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.excelcommander.service.ProjectService;

/**
 * @author HGDIV
 */

/**
 * MenuController controls and handles events for the toolbars and dropdown File menu
 */
@Controller
public class MenuController extends ParentController {

    public static final String ROOT_VIEW = "/fxml/RootRoot.fxml";
    public static final String START_VIEW = "/fxml/FreshSSView.fxml";


    Logger logger = LoggerFactory.getLogger(MenuController.class);


    private ApplicationContext ctx;


    private static Project project;
    private HostServices hostServices;
    ProjectService projectService;
    private static Workbook currentWorkbook;


    @FXML
    protected StackPane stackPane;

    @FXML
    protected TabPane tabPane;

    @FXML
    protected Tab tab;

    @FXML
    protected SpreadsheetView ssView;

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

        File filePath = DialogHelper.showFilePrompt("New Workbook", ".xlsx", true);
        FileResource fileResource = new FileResource(filePath);
        try {
            SpreadSheetUtils.createBlankWorkbook(fileResource);
            project.setFileResource(fileResource);

        } catch (Exception e) {
            DialogHelper.showErrorAlert("A file with this name may already exist");

        }

        if (fileResource.isExists()) {

            try {
                WindowUtils.replaceFxmlOnWindow(stackPane,SpreadSheetController.SSCONTROLLER_TAG,stage,null);
            } catch (Exception e) {
                e.printStackTrace();
            }


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

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Something Went Wrong");
            alert.setHeaderText("Could not open the file");
            alert.setContentText("Sorry we were not able to open the selected file, please try again");
            alert.showAndWait();

        }

    }

    /**
     *
     * @param event File menu import -> from Excel file clicked
     *
     */
    @FXML
    private void handleImportFromExcel(ActionEvent event) {

        Workbook wb = null;

        FileChooser fchooser = new FileChooser();
        fchooser.setTitle("Import from Excel workbook: ");
        fchooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".xlsx", "*.xlsx"));
        File excelFile = fchooser.showOpenDialog(new Stage(StageStyle.UTILITY));

        try {
            wb = SpreadSheetUtils.loadFromFile(excelFile);
            setCurrentWorkbook(wb);
        }catch (Exception e){
            System.out.println("Problem loading from file");

        }
        HashMap<String, Object> params = new HashMap<>();
        params.put(SpreadSheetController.SSCONTROLLER_TAG, wb);

        try {
            WindowUtils.replaceFxmlOnWindow(stackPane,SpreadSheetController.FRESH_SS_VIEW,stage,params);
        } catch (Exception e) {
            e.printStackTrace();
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


        projectService.findByProjectName(projectName,event -> {

            try {
                setProject((Project) event.getSource().getValue());
            }catch (Exception ex){
                ex.printStackTrace();
            }


                },null);


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

    public static Workbook getCurrentWorkbook() {
        return currentWorkbook;
    }

    public static void setCurrentWorkbook(Workbook currentWorkbook) {
        MenuController.currentWorkbook = currentWorkbook;
    }



}