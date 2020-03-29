package com.excelcommander;

import com.excelcommander.controller.MenuController;
import com.excelcommander.model.Project;
import com.excelcommander.service.ProjectService;
import com.excelcommander.util.DialogHelper;
import com.excelcommander.util.WindowUtils;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;

@SpringBootApplication
public class ExcelCommanderApplication extends Application {

    private static ConfigurableApplicationContext ctx;
    ProjectService projectService;
    private Project project;
    private StackPane stackPane;


    @Value("${application.title.display}")
    protected String title;


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

        stackPane = new StackPane();
        DialogHelper.inputDialog(stackPane, "Welcome to ExcelCommander", "Would you like to create a new project?\n or open and existing one?",
                () -> {    //action1 New Project
                    String projectName = DialogHelper.showInputPrompt("Create A New Project",
                            "Please enter a name for the project you would like to create", "New Project");
                    if (projectName.length() > 1) {
                        project = new Project(projectName);
                        try {
                            projectService.save(project, event -> {

                                project = (Project) event.getSource().getValue();

                                try {
                                    WindowUtils.open(primaryStage, MenuController.ROOT_FXML, project.getProjectName(), null);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }, null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, //action2 Open Project

                () -> {
                    String nameOfProject = DialogHelper.showInputPrompt("Open Project",
                            "Please enter the #ID for the project you would like to open", "Welcome back to " + title);

                    projectService.findByProjectName(nameOfProject, e -> {

                        project = (Project) e.getSource().getValue();
                        HashMap<String, Object> parmas = new HashMap<>();
                        parmas.put(MenuController.NEW_PROJECT, project);
                        try {
                            WindowUtils.open(primaryStage, MenuController.ROOT_FXML, project.getProjectName(), parmas);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }, null);

                });


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
