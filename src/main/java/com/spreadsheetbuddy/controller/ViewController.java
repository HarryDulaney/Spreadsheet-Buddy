package com.spreadsheetbuddy.controller;

import com.spreadsheetbuddy.model.SettingsWrapper;
import com.spreadsheetbuddy.model.Project;
import com.spreadsheetbuddy.service.FileService;
import com.spreadsheetbuddy.service.ProjectService;
import com.spreadsheetbuddy.util.CellFormatUtil;
import com.spreadsheetbuddy.util.DialogUtil;
import com.spreadsheetbuddy.util.RecentFilesUtil;
import com.spreadsheetbuddy.util.WbUtil;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.PlusMinusSlider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * <p>
 * {@code ViewController.class } acts as the primary controller for application during runtime.
 * </p>
 * <p>
 * It is responsible for handling ActionEvents from the Main Menu and delegates events to the current SpreadSheetView
 * through a FXControllerView wrapped reference to the {@code WorkbookController.class }.
 * </p>
 *
 * @author Harry Dulaney
 */
@Component
@FxmlView("/fxml/main.fxml")
public class ViewController {

    private final Logger logger = LoggerFactory.getLogger(ViewController.class);
    private final FxWeaver fxWeaver;
    private static Project project;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    private FileService fileService;
    private ProjectService projectService;

    @FXML
    protected Spinner<String> cellTypeSpinner;

    @FXML
    protected Menu recentFilesMenu;


    @Value("${sheet-buddy.about.page}")
    private String aboutPageUri;

    @Value("${sheet-buddy.issues.page}")
    private String issuesPageUri;


    @FXML
    VBox rootNode;

    @FXML
    MenuBar mainMenu;

    private final FxControllerAndView<WorkbookController, TabPane> workbookControlView;

    @Autowired
    public ViewController(FxWeaver fxWeaver,
                          @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") FxControllerAndView<WorkbookController,
                                  TabPane> workbookControlView) {
        this.fxWeaver = fxWeaver;
        this.workbookControlView = workbookControlView;

    }


    @FXML
    public void initialize() {
        /* Initialize the Project */
        project = new Project();

        if (projectService.exists(project.getProjectId())) {
            project = projectService.getProject(project.getProjectId());
//            SettingsWrapper.INSTANCE.setTurnOffDefaultSpreadsheet(true);
        } else {
            projectService.save(project);
            SettingsWrapper.INSTANCE.setTurnOffDefaultSpreadsheet(false);
        }
        workbookControlView.getController().rootNode = this.rootNode;

        /* Initialize recent files */
        recentFilesMenu = RecentFilesUtil.initRecentFileMenu(recentFilesMenu); //TODO: Recentfiles configuration

        SpinnerValueFactory.ListSpinnerValueFactory<String> spinnerValueFactory =
                new SpinnerValueFactory.ListSpinnerValueFactory<>(CellFormatUtil.getSupportedCellFormats());
        cellTypeSpinner.setValueFactory(spinnerValueFactory);

    }

    /*************************************** UI ActionEvent Handling *****************************************/
    @FXML
    protected void openAboutPage(ActionEvent actionEvent) {
        fxWeaver.getBean(HostServices.class).showDocument(aboutPageUri);
    }

    @FXML
    protected void openWorkBook(ActionEvent event) {
        File wbFile = DialogUtil.showFilePrompt("Choose the workbook to open", ".xlsx");

        if (Objects.nonNull(wbFile)) {
            logger.info(".xlsx file picked to open -> " + wbFile.getName());

            try {
                XSSFWorkbook workbook = fileService.getWorkbook(wbFile);
                workbookControlView.getController().updateWorkbookView(workbook);

                project.setOpenFile(wbFile.getAbsolutePath());

            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error opening the Excel WorkBook from file: " + wbFile.getName() + " Exception is " +
                        "instanceOf: " + e.toString());
                DialogUtil.showSimpleAlert("Error opening the Excel WorkBook from file: " + wbFile.getName(), Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    protected void exitRequested(ActionEvent actionEvent) {
        try {
            workbookControlView.getController().close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        projectService.save(project);

        try {
            fxWeaver.getBean(Application.class).stop();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Error exiting when calling JavaFxApplication.stop() method");
            DialogUtil.showAlert("Something went wrong: " + ex.getLocalizedMessage(), "Background Process " +
                            "Running Error",
                    "Alert!", Alert.AlertType.ERROR);
        }
    }

    /**
     * Example of using FxWeaver to get a registered JavaFx Bean
     *
     * @param actionEvent fire event from MenuItem
     */
    @FXML
    protected void openIssuesPage(ActionEvent actionEvent) {
        fxWeaver.getBean(HostServices.class).showDocument(issuesPageUri);
    }

    @FXML
    protected void createNewWorkbook(ActionEvent actionEvent) {
        File f = DialogUtil.showSaveFilePrompt("Create New Workbook", ".xlsx", "",
                StageStyle.UTILITY);
        if (Objects.nonNull(f)) {
            try {
                Workbook wb = WbUtil.createBlankWorkbook(f);
                project.setOpenFile(f.getAbsolutePath());
            } catch (Exception exc) {
                exc.printStackTrace();
                DialogUtil.showSimpleAlert(exc.getLocalizedMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    protected void closeWorkbook(ActionEvent actionEvent) {
        // TODO: Define

    }

    @FXML
    protected void saveWorkbook(ActionEvent actionEvent) {
        // TODO: Define
    }

    @FXML
    protected void saveWorkbookAs(ActionEvent actionEvent) {
        // TODO: Define

    }

    @FXML
    protected void openPreferences(ActionEvent actionEvent) {
        FxControllerAndView<SettingsController, AnchorPane> settingsControlView =
                fxWeaver.load(SettingsController.class);
        settingsControlView.getController().show();

    }

    @FXML
    protected void newSpreadsheet(ActionEvent actionEvent) {
        System.out.println("adding new ssView");
        workbookControlView.getController().addSpreadsheet(rootNode);

    }

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }
}