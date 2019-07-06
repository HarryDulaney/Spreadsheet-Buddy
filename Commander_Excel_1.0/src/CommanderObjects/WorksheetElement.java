package CommanderObjects;

import java.util.ArrayList;

/* Interface to define any category element in a worksheet. ie. (Column header)
*
*/

public interface WorksheetElement {

	/*
	 * For example, elementTypeName could be (Customer names), (Employees), etc...
	 */

	public static String elementName = "";
	
	/*
	 * An ArrayList to hold the elements for processing
	 */

	public static ArrayList<Object> stringElementStrings = new ArrayList<>();
	
	

}
