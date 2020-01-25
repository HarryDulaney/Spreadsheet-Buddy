package test;

import java.io.File;

import org.junit.Test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class TempFileDirectoryTest extends Application{

	@Test
	public void checkDefaultTempFileDirectory() throws InterruptedException {
		
		Pane pane = new Pane();
		Stage stage = new Stage();
		Scene scene = new Scene(pane,500,500);
		final String defaultSysDirectory = System.getProperty("java.io.tmpdir");
		
		stage.setScene(scene);

		DirectoryChooser dc = new DirectoryChooser();
		File tempFile = new File(defaultSysDirectory);
		dc.setInitialDirectory(tempFile);
		
		dc.showDialog(stage);
		
		/*assert(Observe the default temporary directory folder in the file system)
		 * 
		 */
		System.out.println(defaultSysDirectory);
		

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		TempFileDirectoryTest tFdT = new TempFileDirectoryTest();
		tFdT.checkDefaultTempFileDirectory();
		
	}
	public static void main(String[] args) {
		launch(TempFileDirectoryTest.class);

	}
}
