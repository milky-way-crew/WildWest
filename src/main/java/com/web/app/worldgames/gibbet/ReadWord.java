package com.web.app.worldgames.gibbet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.log4j.Logger;


public class ReadWord {
	private final static Logger log = Logger.getLogger(ReadWord.class);
	Random rand = new Random();
	
	public String readFromFile(String type) throws FileNotFoundException {
		log.info("This method is go");
		String word="";
		List<String> list = new ArrayList<String>();
		list.add("mathematic.txt");
		list.add("physic.txt");
		list.add("names.txt");
		list.add("developer.txt");
		
		File file;
		if(type.equals("random")){
			int i = rand.nextInt(list.size());
			file = new File("C:\\Users\\Modkram\\git\\WildWest\\src\\main\\webapp\\resources\\gibbetwords\\"+list.get(i));
			log.info("Random file is "+file);
		}else{
			file = new File("C:\\Users\\Modkram\\git\\WildWest\\src\\main\\webapp\\resources\\gibbetwords\\"+type+".txt");
			log.info("File is "+file);
		}
		
		Scanner scan = new Scanner(file);
		Scanner scanner = new Scanner(file);
		
		int wordsInFile=0;
		while(scan.hasNext()){
			String temp = scan.next();
			log.info("read words"+wordsInFile+"   "+temp);
			wordsInFile++;
		}
		log.info("words in file are "+wordsInFile);
		
		int numberWord = rand.nextInt(wordsInFile);
		log.info("Random word number:"+numberWord);
		int l=0;
		while(scanner.hasNext()){
			String temp = scanner.next();
			log.info("Temp word is  "+temp+"  l="+l);
			if(l==numberWord){
				word=temp;
				log.info("Word is:  "+word);
			}
			l++;
		}
		
		log.info("word is: "+word);
		
//		return "fuck";
		return word;
	}
}
