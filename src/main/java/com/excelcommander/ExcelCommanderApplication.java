package com.excelcommander;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.excelcommander.controller.MenuController;
import com.excelcommander.util.WindowUtils;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Stage;

@SpringBootApplication
public class ExcelCommanderApplication extends Application {

	private static ConfigurableApplicationContext ctx;	
	
	
	@Value("${spring.application.name}")
	String title;


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() throws Exception {
		ctx = SpringApplication.run(ExcelCommanderApplication.class);


}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		WindowUtils.open(primaryStage, MenuController.ROOT_VIEW,title, null);
		

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
