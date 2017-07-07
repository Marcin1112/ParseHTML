package com.html.parsing.parsing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Marcin Zabadaj email: zabadajmarcin@gmail.com
 */
public class Parse {
	/**
	 * This method read data from HTML page
	 * @param url
	 * 	String that represent the url
	 * @return
	 * 	{@code Map<String, String>}
	 * @throws IOException
	 * 	IOException
	 */
	public static Map<String, String> parseHtml(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		Elements content = doc.getElementsByClass("detail");
		Map<String, String> map = new HashMap<String, String>();
		for (Element element : content) {
			Elements header = element.getElementsByClass("label");
			Elements data = element.getElementsByClass("value");
			String key = header.text().toLowerCase().replace(' ', '_').replace(":", "");
			String value = data.text().toLowerCase();
			map.put(key, value);
		}
		return map;
	}

	/**
	 * This method read urls from file and call parseHtml() function
	 * @param fileName
	 * 	String that represent the path to file
	 * @return
	 * 	{@code Map<String, Map<String, String>>}
	 * @throws IOException
	 * 	IOException
	 */
	public static Map<String, Map<String, String>> parseFile(String fileName) throws IOException {
		Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String str;
		while ((str = in.readLine()) != null)
			map.put(str, parseHtml(str));
		in.close();
		return map;
	}
}
