package com.commander.app;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.commander.app.model.Command;
import com.commander.app.model.ProjectBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * @author HG Dulaney IV
 */

public class ProjectController implements Initializable {

	private MainMenu mainMenu;

	@FXML
	private ImageView arrowImage;
	
	@FXML
	private Label label2;

	@FXML
	private Label label1;

	@FXML
	private TableView<Command> tableView;

	@FXML
	private TableColumn<Command, String> commandName;

	public ProjectController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if (ProjectBean.getInstance().getSooperCommands().isEmpty()) {
			label2.setVisible(true);
			arrowImage.setVisible(true);
		} else {
			label2.setVisible(false);
			arrowImage.setVisible(false);

		}
		ObservableList<Command> commandList = FXCollections
				.observableArrayList(ProjectBean.getInstance().getSooperCommands());

		commandName.setCellValueFactory(new PropertyValueFactory<Command, String>("commandName"));

		tableView.setItems(commandList);

		label1.setText(ProjectBean.getInstance().getName());

	}

	@FXML
	protected void handleNewSuperCommand(ActionEvent event) throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainMenu.class.getResource("/com/commander/app/view/SooperStartView.fxml"));
		AnchorPane pane = (AnchorPane) loader.load();
		
		BorderPane borderpane = (BorderPane) getNestedPane(MainMenu.getRootPane().getScene());
		borderpane.setLeft(pane);

	}
	

	public void setMainmenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;

	}
	public Node getNestedPane(Scene scene) {
		Node bp = scene.lookup("#bPane");
		return bp;
	}

}
