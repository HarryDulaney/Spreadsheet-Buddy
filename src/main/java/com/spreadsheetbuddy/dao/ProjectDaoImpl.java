package com.spreadsheetbuddy.dao;

import com.spreadsheetbuddy.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ProjectDaoImpl implements ProjectDao {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public void insertProject(Project p) {
        int rows = jdbcTemplate.update("INSERT INTO PROJECT (PROJECT_ID) VALUES (?)",
                p.getProjectId());
        logger.info(rows + " rows inserted into PROJECT");
    }

    @Override
    public void deleteProject(Project p) {
        int rows = jdbcTemplate.update("DELETE FROM PROJECT WHERE PROJECT_ID = ?", p.getProjectId());
        logger.info(rows + " rows deleted from PROJECT");
    }

    @Override
    public Project getProjectById(String id) {
        List<Map<String, Object>> rowsList = jdbcTemplate.queryForList("SELECT * FROM PROJECT WHERE PROJECT_ID = ?", id);

        if (rowsList.size() < 1) {
            Project project = new Project();
            insertProject(project);
            return project;
        }
        return new Project(String.valueOf(rowsList.get(0).get("PROJECT_ID")));
    }


    @Override
    public List<String> getRecentFiles(String id) {
        List<Map<String, Object>> selectedRows = jdbcTemplate.queryForList("SELECT * FROM PROJECT_FILES WHERE " +
                "PROJECT_ID = ?", id);
        List<String> recentFiles = new ArrayList<>();
        for (Map map : selectedRows) {
            recentFiles.add(String.valueOf(map.get("RECENT_FILE")));
        }

        return recentFiles;
    }

    @Override
    public void insertRecentFile(String file, String id) {
        jdbcTemplate.update("INSERT INTO PROJECT_FILES (PROJECT_ID, RECENT_FILE) VALUES (?,?)", id, file);

    }
}