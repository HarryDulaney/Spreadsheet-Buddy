package com.commander.app.utils.Scrapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.commander.app.model.ProjectElement;

@XmlRootElement
public class JsoupObj extends ProjectElement {

	private String sourceUrl;
	private static final String uAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36"
			+ " (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36";

	public JsoupObj() {

	}

	public JsoupObj(String sourceUrl) {
		this.sourceUrl = sourceUrl;

	}

	@XmlElement
	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String initData() throws Exception {

		Document document = Jsoup.connect(sourceUrl).userAgent(uAgent).timeout(3000).get();

		if (document != null) {
			return document.title();
		} else
			throw new Exception();

	}

}
