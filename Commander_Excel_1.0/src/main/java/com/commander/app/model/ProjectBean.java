package main.java.com.commander.app.model;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.prefs.Preferences;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ProjectBean is JavaBean and factory class. Instantiating a new ProjectBean
 * is done using the static method: {@code ProjectBean.getInstance() }.
 * <p>
 * The ProjectBean represents the application users project properties, i.e.
 * references to Workbooks, Sheets, user-defined directory folder, and
 * SuperCommands{@link SuperCommand}.
 * 
 *
 * @author HGDIV
 */
public class ProjectBean {

	private static ProjectBean instance;
	private Integer projectID;
	private LinkedList<SuperCommand> sooperCommands;
	private String projectName;
	private List<SuperWorkbook> workbooks;
	private File directoryLoc = null;

	private ProjectBean() {
		setProjectID(-1); // Mark new instances until persisted into DB memory

	}

	private ProjectBean(ProjectBean pb) {
		instance = pb;
	}

	public static ProjectBean getInstance() {
		if (instance == null) {

			instance = new ProjectBean();
		}

		return instance;

	}

	public static void getInstance(ProjectBean pb) {

		instance = new ProjectBean(pb);

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
		if (instance != null) {

			instance = null;
		}
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

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "Project Name: " + projectName;

	}

	public Integer getProjectID() {
		return projectID;
	}

	public void setProjectID(Integer projectID) {
		this.projectID = projectID;
	}

	public static class JsonAccessObject {

		public static void writeProjectJson(final File resultFile, final ProjectBean value) {

			ObjectMapper mapper = new ObjectMapper();

			try {
				String packagedBean = mapper.writeValueAsString(value);

				Properties p = new Properties();
				p.setProperty(instance.getName(), packagedBean);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		public static boolean readProjectJson(final File file) {
			boolean success = false;

			ObjectMapper mapper = new ObjectMapper();

			try {

				ProjectBean.instance = mapper.readValue(file, ProjectBean.class);

				success = true;
			} catch (IOException e) {
				success = false;
				e.printStackTrace();

			}
			return success;

		}

	}
}
