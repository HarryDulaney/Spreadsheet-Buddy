package InProcess;

import java.util.ArrayList;

import com.commander.app.model.Command;


public class ProjectHibObject {

	private int id;
	private String name;

	private ArrayList<Command> commandList;

	public ProjectHibObject() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Command> getSooperCommands() {
		return commandList;
	}

	public void setSooperCommands(ArrayList<Command> commandList) {
		this.commandList = commandList;
	}

}