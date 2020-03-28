package com.excelcommander;

import com.excelcommander.controller.MenuController;
import com.excelcommander.model.Project;
import com.excelcommander.service.ProjectService;
import com.excelcommander.util.WindowUtils;
import javafx.application.Application;
import javafx.application.HostServices;
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


    @Value("${spring.application.name}")
    String title;

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
        Project ptest = new Project("Test");

        projectService.save(ptest,e->{


        },null);


        projectService.findByProjectName("Test", e -> {

            project = (Project) e.getSource().getValue();
            try {
                WindowUtils.open(primaryStage, MenuController.ROOT_FXML, project.getProjectName(), null);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }, null);


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
    Project initProject(){
        return project;

    }


}
