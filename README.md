# SuperCommander_1.0

-> is a project designed and coded by Harry Dulaney. 

The purpose of the program is to implement Apache POI and other open source Excel interactor API's;
in a way that will award the user with new possibilities for task automation between their computer and MS_Excel. 

The user will create a new project, and name it and save it in the file directory.

Then they will be able to browse a list of commands which they can pick from to interact with existing or new .xlsx
workbooks. (Still debating if we should include .xls compatability).

Essentially, the user will create a project which when complete will be an amalgamation of commands choosen from the UI Menu.
The user will choose add new task, choose which task they want the SuperCommander to perform, ie. *combine two excel workbooks togheter* 
and then they will be prompted to enter whatever user specific data, ie. *The names of the workbooks* , is needed to complete the task.
This task will then be saved as part of the project and displayed, with other tasks are already created on the project, 
to the user. At any time the option exists to *Execute SuperCommander* and run the scripts.  

The main elements of a Commander project will be :

1) Source (ie) choose from new workbook created, retrieve existing Excel workbook to operate on, and designate a web URL to scrape for 

user designated information.

2) Do something with the source material, (ie) organize it, sort it, or format its style or combine with other information. Or perform some 
function on the worksheet data.

3) Select form of output to what workbook or sheet. 
