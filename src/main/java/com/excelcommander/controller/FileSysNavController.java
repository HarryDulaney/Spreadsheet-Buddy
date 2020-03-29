package com.excelcommander.controller;

import com.excelcommander.ExcelCommanderApplication;
import com.excelcommander.model.Project;
import com.excelcommander.util.DialogHelper;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;

@Controller
public class FileSysNavController extends ParentController {


    private Project project; //Current Project
    private static ConfigurableApplicationContext ctx = ExcelCommanderApplication.getCtx();

    @FXML
    protected ScrollPane scrollPaneProjectNav;
    @FXML
    protected AnchorPane anchorPaneFileNav;

    protected Label chosenLbl;


    @SuppressWarnings("unchecked")
    @Override
    public <T> void init(Stage stage, HashMap<String, T> parameters) {
        super.init(stage, parameters);

        List<Project> projectList;
        if (parameters != null) {
            projectList = (List<Project>) parameters.get("project_list");
            initListView(projectList);
        }

    }

    /**
     * @param projectList List of local Projects
     *                    populates the ListView
     */
    private void initListView(List<Project> projectList) {
        JFXListView<Label> listView = new JFXListView<>();

        for (Project p : projectList) {
            listView.getItems().add(new Label(p.getProjectName()));

        }
        listView.autosize();
        listView.setExpanded(true);
        listView.setMinWidth(scrollPaneProjectNav.getPrefViewportWidth());
        listView.setMinHeight(scrollPaneProjectNav.getPrefViewportHeight());

        listView.setOnMouseClicked((MouseEvent e) -> {
            if (e.getClickCount() == 2) {
                chosenLbl = listView.getSelectionModel().getSelectedItem();

                if (!chosenLbl.getText().isEmpty()) {
                    for (Project p : projectList) {
                        if (p.getProjectName().equals(chosenLbl.getText())) {
                            this.project = p;
                            ctx.getBean(MenuController.class).setProject(p);

                        }

                    }
                    DialogHelper.showSimpleAlert("Success, your project is open", Alert.AlertType.CONFIRMATION);

                    stage.close();
                }
            }

        });

        anchorPaneFileNav.getChildren().add(listView);
    }

    @Autowired
    protected void setProject(Project project) {
        this.project = project;
    }


    @Override
    protected void onClose() {


    }

}
