package com.web.app.worldgames.domain.guess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class AnswerLoader {
	private static final String ANSWERS_FILE = "answers";
	private static final Logger log = Logger.getLogger(AnswerLoader.class);
	
	public static List<String> loadAnswers() {
		String path = AnswerLoader.class.getClassLoader().getResource(ANSWERS_FILE).getFile();
		log.info("Loading answers by path: " + path);
		return readAnswers(new File(path));
	}

	private static List<String> readAnswers(File file) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			log.fatal("File with answers not found");
			e.printStackTrace();
		}
		List<String> words = new ArrayList<String>();
		
		while (scanner.hasNext()) {
			String word = scanner.nextLine();
			words.add(word);
		}
		return words;
	}
}
