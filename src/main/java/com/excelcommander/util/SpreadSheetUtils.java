package com.excelcommander.util;

import org.controlsfx.control.spreadsheet.GridBase;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;
import org.controlsfx.control.spreadsheet.SpreadsheetView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SpreadSheetUtils {

	public static SpreadsheetView createSSView() {

		int rowCount = 40; // Default row count

		int columnCount = 40; // Default column count

		GridBase grid = new GridBase(rowCount, columnCount);

		ObservableList<ObservableList<SpreadsheetCell>> rows = FXCollections.observableArrayList();
		{
			for (int row = 0; row < grid.getRowCount(); ++row) {
				final ObservableList<SpreadsheetCell> list = FXCollections.observableArrayList();
				for (int column = 0; column < grid.getColumnCount(); ++column) {
					list.add(SpreadsheetCellType.STRING.createCell(row, column, 1, 1, "value"));
				}
				rows.add(list);
			}
			grid.setRows(rows);

			return new SpreadsheetView(grid);
		}
	}
}
