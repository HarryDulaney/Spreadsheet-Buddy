package com.commander.app.model;

import com.commander.app.model.Project;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import com.commander.app.model.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.commander.app.MainMenu;
import com.commander.app.MenuController;

public class JAXBtest {
	
	 
	
	public static void main(String[] args) throws FileNotFoundException {
		
		
		
		
		Project project = new Project("TestProject",new File("C:\\Users\\Jordan Lightgate\\Desktop\\TESTERone.xml"));
		
		Command commandOne = new Command();
		
		commandOne.setId(123);
		commandOne.setName("UPDATE");
		commandOne.setFileIn(new File("C:\\Users\\JordanLightgate"));
		commandOne.setFileOut(new File("C:\\Users"));
		project.addCommand(commandOne);
		
		Command commandTwo = new Command();
		
		commandTwo.setId(456);
		commandTwo.setName("SELECT");
		commandTwo.setFileIn(new File("C:\\Users\\JordanLightgate"));
		commandTwo.setFileOut(new File("C:\\Users"));
		project.addCommand(commandTwo);
		
		
		
		
		try {

			JAXBContext context = JAXBContext.newInstance(Project.class);

			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			OutputStream output = new FileOutputStream(project.getProjectFile());
			marshaller.marshal(project, output);

		} catch (JAXBException e) {
			System.out.print("Something went wrong with JAXB content and marshaller");
			e.printStackTrace();
		}
		
		
		
	}

}
