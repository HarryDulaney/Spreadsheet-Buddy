#SuperCommander_1.0

Commander_Excel_1.0 is a project by Harry G. Dulaney.(So far just me, but contributions are very welcome!)

The goal of this project is to use Excel communicator API's, specifically FILLO (because of ease of use and unlimited potential),
in a way that will award the user with new possibilities for automating tedious or repetitive functions in MS Excel.

The user will create a new project, and name it and save it; data persistence is being set up using JAXB for marshaling and unmarshalling XML
documents which contain the user's instance of their project. 

The user will be able to browse through a menu of tasks or "commands" which they can customize by inputting their spreadsheets/ workbooks
information.

Essentially, the user will create a script themselves using the UI menu to choose the options they want their 
Commander project to run. 

The main elements of a Commander project will be :

1) Source (i.e.) choose from new workbook created, retrieve existing Excel workbook to operate on and designate a 
web URL which they can search through (Web scrapping tool for this, Apache Storm anyone? ).


2) User can define multiple sources and outputs to create repeatable functions called SuperCommmands. 
3) Select the form of output to what workbook or sheet. 

4) Save the task and all its dependency information to recall the project at the owners discretion.

08/14/2019
Recent Developments:
1. Fillo - for advanced manipulation of spreadsheets. Fillo allows developers to interact with Excel spreadsheets using SQL statements.
The FILLO API also includes packages from Apache POI, Apache Commons, Apache Collections, and other open-source APIs making it a
useful solution for us to use to interface with Excel documents. 
Other than:

2. Nashorn -Java scripting engine for processing JavaScript and other scripting languages.

->  Nashorn can be used to allow us access to Microsoft’s extensive body of 
scripting tools for interacting with MS Excel because Microsoft publishes the MS Office APIs in JavaScript.  (This is an area I could use the support of some other programmers as my knowledge
of JavaScript is still intermediate, but I have already set up a Nashorn object in “com.commander.app.model” named "JSObj.java".

