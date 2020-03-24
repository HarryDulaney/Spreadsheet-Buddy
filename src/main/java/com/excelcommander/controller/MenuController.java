package com.excelcommander.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.application.HostServices;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.RadioMenuItem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.excelcommander.model.Project;
import com.excelcommander.util.WindowUtils;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author HGDIV
 */

/**
 * MenuController controls and handles events for the toolbars and dropdown File menu
 */
@Controller
public class MenuController extends ParentController {

    public static final String ROOT_VIEW = "/fxml/RootRoot.fxml";
    public static final String START_VIEW = "/fxml/StartBlankView.fxml";

    @FXML
    private ButtonBar mainButtonBar;

    @FXML
    protected RadioMenuItem mainToolbarRadioButton;

    private ApplicationContext ctx;
    private Project project;
    private HostServices hostServices;

    @FXML
    private AnchorPane fillPane; //RootRoot AnchorPane to fill in scene.


    @Override
    public <T> void init(Stage stage, HashMap<String, T> parameters) {
        super.init(stage, parameters);

        try {
            WindowUtils.replaceFxmlOnWindow(fillPane, START_VIEW, stage, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        initButtons();


    }

    private void initButtons() {
        mainButtonBar.setVisible(false);
    }

    @FXML
    private void handleSaveProject(ActionEvent event) throws FileNotFoundException {


        Alert alrt = new Alert(AlertType.WARNING);
        alrt.setHeaderText("Please create a projectXml first before saving it");
        alrt.setContentText(
                "You must first open or create a new projectXml in order to perform the save projectXml action");
        alrt.show();


    }

    @FXML
    private void handleNewProject(ActionEvent event) throws IOException {
/*
		createNewProject();

		if (MainMenu.getCurrent() != null) {

			saveProject();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Create New Project");
			alert.setHeaderText("Success!");
			alert.setContentText("You're new project named: " + project.getName() + " was saved at: "
					+ project.getProjectFile().toString());
			alert.showAndWait();

			openProject(MainMenu.getCurrent().getProjectFile());

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Something Went Wrong");
			alert.setContentText("Please try again to create a new project");
			alert.showAndWait();
		}*/

    }

    @FXML
    private void handleOpenProject(ActionEvent event) throws IOException {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose the project you would like to open");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".xml", "*.xml"));
        File toOpen = chooser.showOpenDialog(new Stage(StageStyle.UTILITY));

        try {
            openProject(toOpen);

        } catch (Exception e) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Something Went Wrong");
            alert.setHeaderText("Could not open the file");
            alert.setContentText("Sorry we were not able to open the selected file, please try again");
            alert.showAndWait();

        }

    }

    @FXML
    private void handleImportFromExcel(ActionEvent event) {
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
    private void handleToggleMainToolbar(ActionEvent event) {

        if (mainButtonBar.isVisible()) {
            mainButtonBar.setVisible(false);
        } else {
            mainButtonBar.setVisible(true);
        }
    }

    @FXML
    protected void handleBlankWorkbook(ActionEvent event) {

        FileChooser fchooser = new FileChooser();
        fchooser.setTitle("Name and save your \".xlsx\" excel workbook: ");
        fchooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".xlsx", "*.xlsx"));
        File filePath = fchooser.showSaveDialog(new Stage(StageStyle.UTILITY));

        if (filePath != null) {

            if (filePath.toString().contains(".xlsx")) {
                try {
                    FileOutputStream fOs = new FileOutputStream(filePath);
                    Workbook wb = new XSSFWorkbook();
                    wb.createSheet("Default");
                    wb.write(fOs);
                    wb.close();

                    Alert a = new Alert(AlertType.CONFIRMATION);
                    a.setHeaderText("Successful Operation");
                    a.setContentText("You created a new workbook with one spreadsheet");
                    a.show();

                } catch (Exception e) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("I'm sorry something went wrong");
                    alert.show();
                    e.printStackTrace();
                }

            }
        }
    }

    private void createNewProject() {


    }

    private void saveProject() throws FileNotFoundException {

    }

    private void openProject(File toOpen) {

        JAXBContext jaxbContext;

        try {
            jaxbContext = JAXBContext.newInstance(Project.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Project project = (Project) jaxbUnmarshaller.unmarshal(toOpen);
            this.setProject(project);

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
    public void setAppCtx(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Autowired
    private void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }


}