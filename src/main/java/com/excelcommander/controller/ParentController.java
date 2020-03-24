package com.excelcommander.controller;

import javafx.stage.Stage;

import java.util.HashMap;
import java.util.prefs.Preferences;

public abstract class ParentController {

	protected Stage stage;
	protected static Preferences uiPreferences;

	public <T> void init(Stage stage, HashMap<String, T> parameters){
		this.stage = stage;

		this.stage.setOnHiding(e -> {
			onClose();
		});
		this.stage.setOnHidden(e -> {
			onClose();
		});
	}

	public Stage getStage() {
		return stage;
	}

	/**
	 * @return The Preferences obj for UI application elements
	 */
	public Preferences getUiPreferences(){
		uiPreferences = Preferences.userNodeForPackage(ParentController.class);
		return uiPreferences;
	}

	protected abstract void onClose();
}
