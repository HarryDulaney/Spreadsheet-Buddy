package control;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import control.PromptBox;
import javafx.fxml.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import model.Project;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class MainMenuController extends {
	
	@FXML
	protected void handleCreateProjectAction(ActionEvent event) {

		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("Select a location for your new project folder");

		File nooProjectFolderPath = chooser.showDialog(new Stage());

		PromptBox pBox = new PromptBox("Type a name for your project folder below and then press Finish", true);
		pBox.start(new Stage());
		

		String filePathString = nooProjectFolderPath.getAbsolutePath() + "\\" + pBox.getUserInput();
		File filePath = new File(filePathString);

		if (filePath.mkdir()) {
			
			PromptBox pBox2 = new PromptBox("You created a new project folder at: " + filePathString, "Press to Continue", true);
			pBox2.start(new Stage());
			
			

		} else {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Something Went Wrong");
			alert2.setContentText("We were unable to create your project folder at " + filePathString);
			alert2.show();
		}
		
	}

	@FXML
	protected void handleOpenProjectAction(ActionEvent event) {

		DirectoryChooser directoryChooser = new DirectoryChooser();
		File file = new File(System.getProperty("user.dir"));
		directoryChooser.setInitialDirectory(file);
		File toOpen = directoryChooser.showDialog(new Stage());
		
		
	}
	
	@FXML
	protected void handleAboutSuperCommander(ActionEvent event) {
		
		try {
			URI url = new URI("https://github.com/Nothingrhymeswithorange/SuperCommander_1.0/blob/master/README.md");
			
		        Desktop.getDesktop().browse(url);

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Something went wrong");
			alert.setContentText("We were unable to open the weblink to the about me document you requested");
			alert.show();
		}
	
	}
	
	@FXML
	protected void handleExitCommander(ActionEvent event) {
		
		System.exit(0);
	}
	@FXML
	protected void handleCreateWorkbookAction(ActionEvent event) {
		
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("Select a location to create and save your workbook");

		File nooProjectFolderPath = chooser.showDialog(new Stage());

		PromptBox pBox = new PromptBox("Type a name for your project folder below and then press Finish", true);
		pBox.start(new Stage());
		
		File fs = new File(nooProjectFolderPath.getAbsoluteFile() + "\\" + pBox.getUserInput() +".xlsx");
		Workbook wb = WorkbookFactory.create(fs);
		Sheet worksheet = wb.getSheetAt(0);
	}

}