package main.java.com.commander.app.utils.Scrapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ScrapeController {

	private String urlString;

	@FXML
	private Label bodyToFill;

	@FXML
	private TextField urlToScrape;

	public ScrapeController() {

	}

	@FXML
	void initialize() {

	}

	@FXML
	protected void handleScrapeTable(ActionEvent event) {

		ArrayList<String> resultList = new ArrayList<>();

		urlString = urlToScrape.getText();

		URL url = null;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e1) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Something went wrong connecting to the URL... ensure it is a full URL "
					+ "including the http and https");

			e1.printStackTrace();
		}

		Document doc = null;
		try {
			doc = Jsoup.parse(url, 3000);
		} catch (IOException e) {

			e.printStackTrace();
		}
		if (doc != null) {
			Element table = doc.select("table").get(0);

			Elements rows = table.select("tr");
			Element colHeader = rows.get(0);

			for (int i = 1; i < rows.size(); i++) {

				Element row = rows.get(i);
				Elements col = row.select("td");

			}

		}

	}

	public Node getNestedPane(Scene scene) {
		Node bp = scene.lookup("#bPane");
		return bp;
	}

}
