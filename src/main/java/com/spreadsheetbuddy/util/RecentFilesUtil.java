package com.spreadsheetbuddy.util;

import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.util.Properties;
import java.util.prefs.Preferences;

public class RecentFilesUtil {

    public static Menu initRecentFileMenu(Menu recentFilesMenu) {
        recentFilesMenu.getItems().clear();
        for (File file : getRecentFilesReferences()) {
            MenuItem menuItem = new MenuItem(file.getName());
            menuItem.setUserData(file);
            recentFilesMenu.getItems().add(menuItem);
        }
        return recentFilesMenu;
    }

    private static File[] getRecentFilesReferences() {
        return new File[]{new File("Testfile.xlsx"), new File("TestFile2.csv")};
    }


}
