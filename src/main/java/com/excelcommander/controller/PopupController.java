package com.excelcommander.controller;

import com.excelcommander.model.Project;
import com.excelcommander.service.ProjectService;
import com.excelcommander.util.WindowUtils;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;

import static com.excelcommander.controller.MenuController.ROOT_FXML;


@Controller
public class PopupController extends ParentController {

    private ProjectService projectService;
    List<Project> projectList;
    protected Label chosenLbl;
    private MenuController menuController;
    private Project tempProject = new Project();

    @FXML
    protected ScrollPane scrollPaneProjectNav;
    @FXML
    protected AnchorPane anchorPaneFileNav;


    @Autowired
    public PopupController(MenuController menuController) {
        this.menuController = menuController;

    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> void init(Stage stage, HashMap<String, T> parameters) {
        super.init(stage, parameters);


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

        listView.setOnMouseClicked(e -> {
            if (e.getClickCount() > 1) {
                chosenLbl = listView.getSelectionModel().getSelectedItem();

                if (!chosenLbl.getText().isEmpty()) {
                    for (Project project : projectList) {
                        if (project.getProjectName().equals(chosenLbl.getText())) {
                            tempProject = project;
                            break;
                        }
                    }
                    projectService.activeProject().setOpen(false);
                    projectService.setActiveProject(tempProject);
                    menuController.getStage().close();
                    try {
                        WindowUtils.open(stage,ROOT_FXML,tempProject.getProjectName(),null);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
                }
        });
        anchorPaneFileNav.getChildren().add(listView);
    }


    @Override
    protected void onClose() {


    }

    @Autowired
    private void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

}
