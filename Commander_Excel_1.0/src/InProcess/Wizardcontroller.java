package InProcess;

import java.io.IOException;

import com.commander.app.MainMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

/**
 * @author Harry Gingles Dulaney IV
 */
public class Wizardcontroller {

	@FXML
	private TextField workbookNameInput;

	@FXML
	private TextField spreadsheetNameInput;

	private MainMenu mm;

	@FXML
	protected void completeActionButton(ActionEvent event) throws IOException {

		if (!workbookNameInput.getText().isEmpty() || !spreadsheetNameInput.getText().isEmpty()) {

			WorkBookmaker wbook = new WorkBookmaker(workbookNameInput.getText().toString(),
					spreadsheetNameInput.getText().toString());
			setWorkBooktoPject(wbook);

			try {
				wbook.executeTask();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				this.mm = getMainMenu();

				mm.showProject();

			}

		} else {
			Alert a = new Alert(AlertType.WARNING);
			a.setHeaderText("Something went wrong");
			a.setContentText("Please fill in all required text fields");
			a.showAndWait();
		}

	}

	public void setWorkBooktoPject(WorkBookmaker wbmaker) {

		MainMenu.getCurrentProject().addProjectTask(wbmaker);

	}

	public MainMenu getMainMenu() {
		return MainMenu.getMainMenu();
	}

	public void setMainmenu(MainMenu mm) {
		this.mm = MainMenu.getMainMenu();

	}

}
