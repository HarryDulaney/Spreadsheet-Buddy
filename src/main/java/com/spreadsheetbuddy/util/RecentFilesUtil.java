package com.spreadsheetbuddy.util;

import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.prefs.Preferences;

public class RecentFilesUtil {
    private static List<File> recentFiles;


    public static Menu initRecentFileMenu(Menu recentFilesMenu) {
        recentFilesMenu.getItems().clear();

        MenuItem menuItem1 = new MenuItem("ExampleFile1.xlsx");
        //TODO: Implement Recent Files persistence and add File Object to MenuItem as setUserData(file);
        MenuItem menuItem2 = new MenuItem("file2.xlsx");
        MenuItem menuItem3 = new MenuItem("file2.xlsx");

//            menuItem.setUserData(file);
        recentFilesMenu.getItems().add(menuItem1);
        recentFilesMenu.getItems().add(menuItem2);
        recentFilesMenu.getItems().add(menuItem3);

        return recentFilesMenu;
    }
//
//    private static List<File> getRecentFilesReferences() {
//        List<File> workingList = new ArrayList<>();
//
//        for (String key : FILE_KEY_LIST) {
//
//                workingList.add(new File(filePath));
//            }
//        }
//        return workingList;
//    }


}
