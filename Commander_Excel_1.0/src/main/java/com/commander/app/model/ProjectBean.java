package main.java.com.commander.app.model;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ProjectBean is a Singleton JavaBean. Instantiating a new ProjectBean is done
 * using the static method: {@code ProjectBean.getInstance() }.
 * <p>
 * The ProjectBean represents the application users project properties, i.e.
 * references to Workbooks, Sheets, user-defined directory folder, and
 * SuperCommands{@link SuperCommand}.
 * 
 *
 * @author HGDIV
 */
public class ProjectBean {

	/**
	 * Represents the attributes and state of the current project.
	 */
	private static ProjectBean instance;
	private LinkedList<SuperCommand> sooperCommands;
	private String projectName;
	private List<SuperWorkbook> workbooks;
	private File directoryLoc = null;
	private String TEMP_DIR;

	private ProjectBean() {
		setTEMP_DIR(System.getProperty("java.io.tmpdir"));

	}

	public static ProjectBean getInstance() {
		if (instance == null) {

			instance = new ProjectBean();
		}

		return instance;

	}

	public LinkedList<SuperCommand> getSooperCommands() {
		return this.sooperCommands;
	}

	public void setSooperCommands(LinkedList<SuperCommand> sooperCommands) {
		this.sooperCommands = sooperCommands;
	}

	public String getName() {
		return this.projectName;
	}

	public void setName(String projectName) {
		this.projectName = projectName;
	}

	public void addCommand(SuperCommand supercommand) {

		this.sooperCommands.add(supercommand);

	}

	public void closeProject() {

		this.projectName = null;
		this.sooperCommands.clear();
		instance = null;

	}

	public File getDirectoryLoc() {
		return directoryLoc;
	}

	public void setDirectoryPath(File file) {
		this.directoryLoc = file;

	}

	public List<SuperWorkbook> getWorkbooks() {
		return workbooks;
	}

	public void setWorkbooks(List<SuperWorkbook> workbooks) {
		this.workbooks = workbooks;
	}
	
	public String getTEMP_DIR() {
		return TEMP_DIR;
	}

	public void setTEMP_DIR(String tEMP_DIR) {
		this.TEMP_DIR = tEMP_DIR;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "Project Name: " + projectName;

	}
	
	public static class DataAccessObject {

		public static void writeProjectJson(final File resultFile, final Object value) {

			ObjectMapper mapper = new ObjectMapper();

			try {
				mapper.writeValue(resultFile, value);
			} catch (IOException e) {
				// TODO Throw alert dialog
				e.printStackTrace();
			}

		}

		public static boolean readProjectJson(final File file) {
			boolean success = false;

			ObjectMapper mapper = new ObjectMapper();

			try {

				  ProjectBean.instance = mapper.readValue(file,ProjectBean.class);
				  
				  success = true;
			} catch (IOException e) {
				success = false;
				e.printStackTrace();

			}
			return success;

		}

	}
}
