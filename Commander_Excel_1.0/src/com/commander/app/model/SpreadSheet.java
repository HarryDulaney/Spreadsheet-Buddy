package com.commander.app.model;

import java.io.File;

public class SpreadSheet {

	private static final String ELEMENT_TYPE = "SpreadSheet";
	private File location;
	private boolean hasHeaders;
	private int id;
	private int numRows;
	private int numColums;
	private enum Role {
		SOURCE,
		DESTINATION,
		SOLO
	}

	public SpreadSheet() {
		id = makeId();
		System.out.println(id);

	}

	public boolean isHasHeaders() {
		return hasHeaders;
	}

	public void setHasHeaders(boolean hasHeaders) {
		this.hasHeaders = hasHeaders;
	}

	public File getLocation() {
		return location;
	}

	public void setLocation(File location) {
		this.location = location;
	}

	private int makeId() {

		int res = (int) (Math.random() * 999999 * Math.random());

		return res;

	}

	@Override
	public String toString() {
		return "Element Type: " + ELEMENT_TYPE;

	}

}
