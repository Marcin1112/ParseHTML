package com.html.parsing.parsing;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Marcin Zabadaj e-mail: zabadajmarcin@gmail.com
 *
 */
public class App {
	/**
	 * This function test the Parse class
	 * 
	 * @param args
	 * 	input parameters
	 */
	public static void main(String[] args) {
		Map<String, Map<String, String>> bigMap = new HashMap<String, Map<String, String>>();
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		System.out.println("Podaj ścieżkę do pliku: ");
		String pathToFile = reader.nextLine();
		try {
			bigMap = Parse.parseFile(pathToFile);
			for (String url : bigMap.keySet()) {
				System.out.println(url);
				Map<String, String> dataMap = bigMap.get(url);

				for (String key : dataMap.keySet()) {
					System.out.println(key + ": " + dataMap.get(key));
				}
				System.out.println();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
