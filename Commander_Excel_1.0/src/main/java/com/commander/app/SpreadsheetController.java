package main.java.com.commander.app;

import org.controlsfx.control.spreadsheet.SpreadsheetView;

import javafx.fxml.FXML;

public class SpreadsheetController {

	@FXML
	public SpreadsheetView SSV;

	/*
	 * 
	 * @FXML public TableView table;
	 * 
	 * public void load(ActionEvent event) throws IOException { FileChooser
	 * fileChooser = new FileChooser(); fileChooser.setTitle("Open file"); File file
	 * = fileChooser.showOpenDialog(table.getScene().getWindow());
	 * 
	 * ExcelFile workbook = ExcelFile.load(file.getAbsolutePath()); ExcelWorksheet
	 * worksheet = workbook.getWorksheet(0); String[][] sourceData = new
	 * String[100][26]; for (int row = 0; row < sourceData.length; row++) { for (int
	 * column = 0; column < sourceData[row].length; column++) { ExcelCell cell =
	 * worksheet.getCell(row, column); if (cell.getValueType() !=
	 * CellValueType.NULL) sourceData[row][column] = cell.getValue().toString(); } }
	 * fillTable(sourceData); }
	 * 
	 * private void fillTable(String[][] dataSource) { table.getColumns().clear();
	 * 
	 * ObservableList<ObservableList<String>> data =
	 * FXCollections.observableArrayList(); for (String[] row : dataSource)
	 * data.add(FXCollections.observableArrayList(row)); table.setItems(data);
	 * 
	 * for (int i = 0; i < dataSource[0].length; i++) { final int currentColumn = i;
	 * TableColumn<ObservableList<String>, String> column = new TableColumn<>(
	 * ExcelColumnCollection.columnIndexToName(currentColumn));
	 * column.setCellValueFactory(param -> new
	 * ReadOnlyObjectWrapper<>(param.getValue().get(currentColumn)));
	 * column.setEditable(true);
	 * column.setCellFactory(TextFieldTableCell.forTableColumn());
	 * column.setOnEditCommit((TableColumn.CellEditEvent<ObservableList<String>,
	 * String> t) -> {
	 * t.getTableView().getItems().get(t.getTablePosition().getRow()).set(t.
	 * getTablePosition().getColumn(), t.getNewValue()); });
	 * table.getColumns().add(column); } }
	 * 
	 * public void save(ActionEvent event) throws IOException { ExcelFile file = new
	 * ExcelFile(); ExcelWorksheet worksheet = file.addWorksheet("sheet"); for (int
	 * row = 0; row < table.getItems().size(); row++) { ObservableList cells =
	 * (ObservableList) table.getItems().get(row); for (int column = 0; column <
	 * cells.size(); column++) { if (cells.get(column) != null)
	 * worksheet.getCell(row, column).setValue(cells.get(column).toString()); } }
	 * 
	 * FileChooser fileChooser = new FileChooser();
	 * fileChooser.getExtensionFilters().addAll(new
	 * FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx"), new
	 * FileChooser.ExtensionFilter("XLS files (*.xls)", "*.xls"), new
	 * FileChooser.ExtensionFilter("ODS files (*.ods)", "*.ods"), new
	 * FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"), new
	 * FileChooser.ExtensionFilter("HTML files (*.html)", "*.html")); File saveFile
	 * = fileChooser.showSaveDialog(table.getScene().getWindow());
	 * 
	 * file.save(saveFile.getAbsolutePath()); }
	 */
}