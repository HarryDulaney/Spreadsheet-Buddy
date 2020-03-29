package com.excelcommander;

import com.excelcommander.controller.MenuController;
import com.excelcommander.model.Project;
import com.excelcommander.service.ProjectService;
import com.excelcommander.util.DialogAction;
import com.excelcommander.util.DialogHelper;
import com.excelcommander.util.WindowUtils;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExcelCommanderApplication extends Application {

    private static ConfigurableApplicationContext ctx;
    ProjectService projectService;
    private static Project project;


    @Value("${application.title.display}")
    public String title;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        ctx = SpringApplication.run(ExcelCommanderApplication.class);
        projectService = ctx.getBean(ProjectService.class);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane sp = new StackPane();
        Scene scene = new Scene(sp);
        primaryStage.setScene(scene);
        primaryStage.show();

        DialogHelper.inputDialog(sp, "Welcome to " + title, "Would you like to open an existing project?", () -> {
            try {
                openProject(primaryStage);

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

  /*      String projectName = DialogHelper.showInputPrompt("Create A New Project",
                "Please enter a name for the project you would like to create", "New Project");
        if (projectName.length() < 1) {
            project = new Project(projectName);
            projectService.save(project, event -> {

                project = (Project) event.getSource().getValue();

                try {
                    WindowUtils.open(primaryStage, MenuController.ROOT_FXML, project.getProjectName(), null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }, null);
*/
    }

    private void openProject(Stage primaryStage) throws Exception {
        String nameOfProject = DialogHelper.showInputPrompt("Open Project",
                "Please enter the #ID for the project you would like to open", "Welcome back to " + title);

        projectService.findById(Long.parseLong(nameOfProject), e -> {

            project = (Project) e.getSource().getValue();
            try {
                WindowUtils.open(primaryStage, MenuController.ROOT_FXML, project.getProjectName(), null);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }, null);
    }
    private void createNewProject(){

    }



    @Bean
    public static ConfigurableApplicationContext getCtx() {
        return ctx;
    }

    @Bean
    HostServices initHostServices() {
        return this.getHostServices();
    }

    @Bean
    Project initProject() {
        return project;

    }


}
