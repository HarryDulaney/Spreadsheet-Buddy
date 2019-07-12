package func;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Reader {

	public static void modifyExistingWorkbook() throws InvalidFormatException, IOException {
	    // Obtain a workbook from the excel file
	    Workbook workbook = WorkbookFactory.create(new File("existing-spreadsheet.xlsx"));

	    // Get Sheet at index 0
	    Sheet sheet = workbook.getSheetAt(0);

	    // Get Row at index 1
	    Row row = sheet.getRow(1);
	    
	    // Get the Cell at index 2 from the above row
	    Cell cell = row.getCell(2);

	    // Create the cell if it doesn't exist
	    if (cell == null)
	        cell = row.createCell(2);


	    // Write the output to the file
	    FileOutputStream fileOut = new FileOutputStream("existing-spreadsheet.xlsx");
	    workbook.write(fileOut);
	    fileOut.close();

	    // Closing the workbook
	    workbook.close();
	}
}
