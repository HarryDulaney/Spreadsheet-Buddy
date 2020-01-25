package main.java.com.commander.app;

import java.io.IOException;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.PropertyUtils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.com.commander.app.model.ProjectBean;

/**
 * @author HGDIV
 */

public class MainMenu extends Application {

	private static Pane rootPane;

	private Stage primaryStage;

	private static MainMenu MAIN = null;
	
	private final static ProjectBean PB = ProjectBean.getInstance();
	
	private final static String ROOT_MENU = "/main/java/com/commander/app/view/RootRoot.fxml";
	private final static String PROJ_MENU = "/main/java/com/commander/app/view/UserView.fxml";

	public MainMenu() {

	}

	/**
	 * Application main entry point.
	 *
	 * @param primaryStage the primary stage
	 */
	@Override
	public void start(Stage primaryStage) {

		setMainMenu(this);
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Welcome to Super Commander for MS Excel");

		initRootLayerShow();

	}

	public void initRootLayerShow() {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainMenu.class.getResource(ROOT_MENU));
			rootPane = loader.load();

			MenuController controller = loader.getController();
			controller.setMainmenu(MainMenu.getMainMenu());

			Scene scene = new Scene(rootPane);
			// scene.getStylesheets().add("/com/commander/app/view/Style/CommanderStyle1.css");

			this.primaryStage.setScene(scene);
			getPrimaryStage().show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the root frame which contains the Drop Down MenuBar for the GUI.
	 */

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Initializes the UserView or 'open project'. Embeds the BorderPane inside the
	 * RootPane.
	 * 
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void showProject() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainMenu.class.getResource(PROJ_MENU));
		AnchorPane anchorpane = (AnchorPane) loader.load();

		ProjectController controller = loader.getController();
		controller.initialize(MainMenu.class.getResource(MAIN),);

		BorderPane borderpane = (BorderPane) getNestedPane(rootPane.getScene());
		borderpane.setCenter(anchorpane);

	}

	/**
	 * Gets the primary stage.
	 *
	 * @return the primary stage
	 */
	public Stage getPrimaryStage() {

		return primaryStage;
	}

	/**
	 * Gets the sub pane two.
	 *
	 * @param scene the scene
	 * @return the sub pane two
	 */
	public Node getSubPaneTwo(Scene scene) {

		Node bp = scene.lookup("#startBlank");
		return bp;

	}

	/**
	 * Gets the nested pane.
	 *
	 * @param scene the scene
	 * @return the nested pane
	 */
	public Node getNestedPane(Scene scene) {
		Node bp = scene.lookup("#borderpane");
		return bp;
	}

	/**
	 * Gets the main menu.
	 *
	 * @return the main menu
	 */
	public static MainMenu getMainMenu() {
		return MAIN;

	}

	/**
	 * Sets m as a static reference to this menu
	 * 
	 * @param m - static reference of this menu
	 */
	public static void setMainMenu(MainMenu m) {

		MAIN = m;

	}

	/**
	 * Gets the root pane.
	 *
	 * @return the root pane
	 */
	public static Pane getRootPane() {
		return rootPane;
	}

}