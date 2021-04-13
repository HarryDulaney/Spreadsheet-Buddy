package com.spreadsheetbuddy.model;

import java.util.prefs.Preferences;

public class PreferencesWrapper {
    static Preferences userPreferences;
    private boolean turnOffDefaultSpreadsheet;
    private static String workingDirectoryPreference;

    static {
        userPreferences = Preferences.systemNodeForPackage(PreferencesWrapper.class);
    }


    public boolean isTurnOffDefaultSpreadsheet() {
        return turnOffDefaultSpreadsheet;
    }

    public void setTurnOffDefaultSpreadsheet(boolean turnOffDefaultSpreadsheet) {
        this.turnOffDefaultSpreadsheet = turnOffDefaultSpreadsheet;
    }

    public static String getWorkingDirectoryPreference() {
        return workingDirectoryPreference;
    }

    public static void setWorkingDirectoryPreference(String workingDirectoryPreference) {
        PreferencesWrapper.workingDirectoryPreference = workingDirectoryPreference;
    }


}
