package com.spreadsheetbuddy.model;

public class InternalSettings {

    static {
        INSTANCE = new InternalSettings();
    }

    private boolean turnOffDefaultSpreadsheet;

    public static InternalSettings INSTANCE;

    private InternalSettings() {}

    public boolean isTurnOffDefaultSpreadsheet() {
        return turnOffDefaultSpreadsheet;
    }

    public void setTurnOffDefaultSpreadsheet(boolean turnOffDefaultSpreadsheet) {
        this.turnOffDefaultSpreadsheet = turnOffDefaultSpreadsheet;
    }

}
