package com.commander.app;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 
 * @author HG Dulaney IV
 */


public class JSObj {
	/**
	 * JSObj is for creating instances of the Nashorn ScriptEngine
	 */

	private File scriptFile;

	public JSObj() {

	}

	public JSObj(File scriptFile) {
		this.scriptFile = scriptFile;

	}

	public void runJscript() {

		ScriptEngineManager manage = new ScriptEngineManager();
		ScriptEngine engine = manage.getEngineByName("nashorn");
		try {
			engine.eval(Files.newBufferedReader(Paths.get(this.scriptFile.getAbsolutePath()), StandardCharsets.UTF_8));
		} catch (ScriptException | IOException e) {

			e.printStackTrace();
		}

		Invocable inv = (Invocable) engine;
		try {

			inv.invokeFunction("**********HERE GOES FUNCTION", "HERE GOES PARAM");

		} catch (NoSuchMethodException | ScriptException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the scriptFile
	 */
	public File getScriptFile() {
		return scriptFile;
	}

	/**
	 * @param scriptFile the scriptFile to set
	 */
	public void setScriptFile(File scriptFile) {
		this.scriptFile = scriptFile;
	}

}
