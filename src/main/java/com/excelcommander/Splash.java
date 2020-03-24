package com.excelcommander;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Splash extends Preloader {
	
	private Stage splashStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.splashStage = primaryStage;
		
		splashStage.setScene(createScene());
		
	}

	private Scene createScene() {
		VBox vBox = new VBox();
		Scene scene = new Scene(vBox,600,400);
		
		return scene;
	}
	
	@Override
	public void handleApplicationNotification(PreloaderNotification notification) {
		if(notification instanceof StateChangeNotification) {
			splashStage.hide();
		}
		
	}

}
