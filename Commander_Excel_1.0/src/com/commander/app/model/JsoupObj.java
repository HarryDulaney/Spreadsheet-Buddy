package com.commander.app.model;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.jsoup.Connection;
import org.jsoup.Connection.KeyVal;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

@XmlRootElement
public class JsoupObj {

	private String sourceUrl;
	private Document document;
	private String attOne;
	private String attTwo;
	private String attValueOne;
	private String attValueTWo;
	private String searchText;
	
	
	public JsoupObj() {

	}

	public JsoupObj(String sourceUrl) {
		this.sourceUrl = sourceUrl;

	}

	@XmlElement
	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	@XmlElement
	public String getAttOne() {
		return attOne;
	}

	public void setAttOne(String attOne) {
		this.attOne = attOne;
	}

	@XmlElement
	public String getAttTwo() {
		return attTwo;
	}

	public void setAttTwo(String attTwo) {
		this.attTwo = attTwo;
	}

	@XmlElement
	public String getAttValueOne() {
		return attValueOne;
	}

	public void setAttValueOne(String attValueOne) {
		this.attValueOne = attValueOne;
	}

	@XmlElement
	public String getAttValueTWo() {
		return attValueTWo;
	}

	public void setAttValueTWo(String attValueTWo) {
		this.attValueTWo = attValueTWo;
	}

	@XmlElement
	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	@XmlElement(name = "SourceURL")
	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String initData() throws Exception {

		document = Jsoup.connect(sourceUrl).userAgent(
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36")
				.timeout(3000).get();

		if (document != null) {
			return document.title();
		} else
			throw new Exception();

	}

}
