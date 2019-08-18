package com.commander.app;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.controlsfx.control.spreadsheet.GridBase;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;
import org.controlsfx.control.spreadsheet.SpreadsheetView;

import com.sun.javafx.util.Utils;

import javafx.scene.control.SelectionMode;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * 
 * @author HG Dulaney IV
 *
 */

/**
 * This is the controller for the SpreadsheetView. Its purpose is to enable
 * users to interact with the program through a visual medium. I.E. the user can
 * highlight a cell in the SpreadsheetView and then assign it to a role in the
 * automated task or Super-Command.
 */
public class SpreadsheetController {

	@FXML
	private SpreadsheetView SSV;

	public SpreadsheetController() {

	}

	private void popView() {

		int rowCount = 15;
		int columnCount = 10;
		GridBase grid = new GridBase(rowCount, columnCount);

		ObservableList<ObservableList<SpreadsheetCell>> rows = FXCollections.observableArrayList();
		for (int row = 0; row < grid.getRowCount(); ++row) {
			final ObservableList<SpreadsheetCell> list = FXCollections.observableArrayList();
			for (int column = 0; column < grid.getColumnCount(); ++column) {
				list.add(SpreadsheetCellType.STRING.createCell(row, column, 1, 1, "value"));
			}
			rows.add(list);
		}
		grid.setRows(rows);

		SpreadsheetView spv = new SpreadsheetView(grid);

	}

}
