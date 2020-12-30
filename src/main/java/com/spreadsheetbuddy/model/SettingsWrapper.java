package com.spreadsheetbuddy.model;

public class SettingsWrapper {

    static {
        INSTANCE = new SettingsWrapper();
    }

    private boolean turnOffDefaultSpreadsheet;

    public static SettingsWrapper INSTANCE;

    private SettingsWrapper() {}

    public boolean isTurnOffDefaultSpreadsheet() {
        return turnOffDefaultSpreadsheet;
    }

    public void setTurnOffDefaultSpreadsheet(boolean turnOffDefaultSpreadsheet) {
        this.turnOffDefaultSpreadsheet = turnOffDefaultSpreadsheet;
    }

}
