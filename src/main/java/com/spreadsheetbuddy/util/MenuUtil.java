package com.spreadsheetbuddy.util;

import ch.qos.logback.core.util.FileUtil;
import com.spreadsheetbuddy.model.Project;
import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.nio.file.FileSystem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.prefs.Preferences;

public class MenuUtil {

    public static Menu initRecentFileMenu(Menu recentFilesMenu, List<String> recentFiles) {
        recentFilesMenu.getItems().clear();
        for (String filePath : recentFiles) {
            String fileName = FilenameUtils.getName(filePath);
            MenuItem menuItem = new MenuItem(fileName);
            //Set filePath as data object on item node for fast recall
            menuItem.setUserData(filePath);
            recentFilesMenu.getItems().add(menuItem);
        }

        return recentFilesMenu;
    }

}
