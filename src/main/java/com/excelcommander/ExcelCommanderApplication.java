package com.excelcommander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javafx.application.Application;
import javafx.stage.Stage;

@SpringBootApplication
public class ExcelCommanderApplication extends Application{
	
	private static ApplicationContext ctx;
	
	

	public static void main(String[] args) {
		ctx = SpringApplication.run(ExcelCommanderApplication.class, args);
	}



	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
