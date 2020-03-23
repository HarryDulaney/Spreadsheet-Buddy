package com.excelcommander.model;

/**
 * 
 * @author H.G. Dulaney IV
 *
 */

public class SpecialObj {

	private String str1;
	private String str2;
	private String str3;
	private String str4;
	private String str5;
	private int keepCount;

	public SpecialObj() {

	}

	public String[] getStrings() {

		String[] stringList = new String[keepCount];

		switch (keepCount) {

		case 1:
			stringList[0] = this.getStr1();
			break;

		case 2:
			stringList[0] = this.getStr1();
			stringList[1] = this.getStr2();
			break;

		case 3:
			stringList[0] = this.getStr1();
			stringList[1] = this.getStr2();
			stringList[2] = this.getStr3();
			break;

		case 4:
			stringList[0] = this.getStr1();
			stringList[1] = this.getStr2();
			stringList[2] = this.getStr3();
			stringList[3] = this.getStr4();
			break;
		case 5:
			stringList[0] = this.getStr1();
			stringList[1] = this.getStr2();
			stringList[2] = this.getStr3();
			stringList[3] = this.getStr4();
			stringList[4] = this.getStr5();
			break;

		}

		return stringList;
	}

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		keepCount++;
		this.str1 = str1;

	}

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		keepCount++;
		this.str2 = str2;

	}

	public String getStr3() {
		return str3;
	}

	public void setStr3(String str3) {
		keepCount++;
		this.str3 = str3;

	}

	public String getStr4() {
		return str4;
	}

	public void setStr4(String str4) {
		keepCount++;
		this.str4 = str4;

	}

	public String getStr5() {
		return str5;
	}

	public void setStr5(String str5) {
		keepCount++;
		this.str5 = str5;

	}

}