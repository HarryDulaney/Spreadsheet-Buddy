DROP TABLE IF EXISTS project;
CREATE TABLE project (
  id INT NOT NULL AUTO_INCREMENT,
  project_name VARCHAR(100) NOT NULL,
  last_known_workbook VARCHAR(100),
  PRIMARY KEY (id));