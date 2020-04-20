package com.excelcommander;

import static com.excelcommander.controller.MenuController.*;

import com.excelcommander.controller.MenuController;
import com.excelcommander.model.Project;
import com.excelcommander.service.ProjectService;
import com.excelcommander.util.DialogHelper;
import com.excelcommander.util.WindowUtils;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;


import java.util.HashMap;

/**
 * @author HGDIV
 */
@SpringBootApplication
public class ExcelCommanderApplication extends Application {

    Logger log = LoggerFactory.getLogger(ExcelCommanderApplication.class);

    private static Project project;
    private static ConfigurableApplicationContext ctx;
    ProjectService projectService;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        ctx = SpringApplication.run(ExcelCommanderApplication.class);
        projectService = ctx.getBean(ProjectService.class);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            initStandAlone();
        } catch (Exception e) {
            e.printStackTrace();
        }

        StackPane stackPane = new StackPane();
        DialogHelper.inputDialog(stackPane, "Welcome to ExcelCommander", "Would you like to create a new project?\n or open and existing one?",
                /*New Project*/ () -> {
                    String projectName = DialogHelper.showInputPrompt("Create A New Project", "Please enter a name for the project you would like to create", "New Project");
                    if (!projectName.isEmpty()) {
                        project = new Project(projectName);
                        try {
                            projectService.save(project, e -> {
                                project = (Project) e.getSource().getValue();
                                project.setOpen();
                            }, null);
                            HashMap<String,Object> params = new HashMap<>();
                            params.put(MESSAGE,"NEW_PROJECT");

                            try {
                                WindowUtils.open(primaryStage, MenuController.ROOT_FXML, project.getProjectName(), params);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    /*Open Project*/
                }, () -> {
                    String nameOfProject = DialogHelper.showInputPrompt("Open Project", "Please enter the name for the project you would like to open", "Welcome back to ExcelCommander");
                    if (!nameOfProject.isEmpty()) {
                        try {
                            projectService.findByProjectName(nameOfProject, e -> {
                                project = (Project) e.getSource().getValue();
                                project.setOpen();
                                HashMap<String,Object> params = new HashMap<>();
                                params.put(MESSAGE,"OPEN_PROJECT");

                                try {
                                    WindowUtils.open(primaryStage, MenuController.ROOT_FXML, project.getProjectName(), params);
                                } catch (Exception ex) {
                                    DialogHelper.showSimpleAlert("Sorry I couldn't find your project in the database," +
                                            "Perhaps try a altering the name, and try again, or create a new project ;)", Alert.AlertType.ERROR);
                                    ex.printStackTrace();
                                }
                            }, null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                    /*Run as Standalone*/
                }, () -> {
                    try {
                        projectService.standAloneMode(event -> {

                        },null);
                    } catch (Exception e) {
                        e.printStackTrace();
                        DialogHelper.showSimpleAlert("Sorry I couldn't find your project in the database," +
                                "Perhaps try a altering the name, and try again, or create a new project ;)", Alert.AlertType.ERROR);
                    }
                    HashMap<String,Object> params = new HashMap<>();
                    params.put(MESSAGE,"STAND_ALONE");


                    try {
                        WindowUtils.open(primaryStage, MenuController.ROOT_FXML, projectService.STAND_ALONE_MODE, params);
                    } catch (Exception ex) {
                        DialogHelper.showSimpleAlert("Sorry I couldn't find your project in the database," +
                                "Perhaps try a altering the name, and try again, or create a new project ;)", Alert.AlertType.ERROR);
                    }


                });
    }

    private void initStandAlone() throws Exception {
        Project project = new Project("STAND_ALONE_MODE");
        projectService.save(project,e ->{
            log.info(((Project)(e.getSource().getValue())).getProjectName() + " initialized for use.");
        },null);

    }


    @Bean
    public static ConfigurableApplicationContext getCtx() {
        return ctx;
    }

    @Bean
    HostServices initHostServices() {
        return this.getHostServices();
    }


}
