package com.web.app.worldgames.gibbet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

public class Gibbet {
	private final static Logger log = Logger.getLogger(Gibbet.class);
	public String generateword(List<String> list){
		Random rand = new Random();
		int i = rand.nextInt(list.size());
		String word = list.get(i);
		log.info("Genereta random word   "+word);
		return word;
	}
	
	public String replacement(String word){
		String newword="";
		char[] mas = word.toCharArray();
		for(int i=0;i<mas.length;i++){
			mas[i]='*';
			 newword=newword+ mas[i];
		}
		log.info("replace"+newword);
		return newword;
	}
	
	public String checking(String trueword,String thisword, char a){
		char[] masthis = thisword.toCharArray();
		log.info("cheking words   "+ thisword+"     "+trueword);
		char[] mastrue = trueword.toCharArray();
		for(int i=0;i<masthis.length;i++){
			if(mastrue[i]==a && masthis[i]=='*'){
				masthis[i]=a;
				log.info("cheking and replace words"+ masthis[i]);
			}
		}
		String returnword="";
		for(int i=0;i<masthis.length;i++){
			returnword = returnword+masthis[i];
		}
		log.info("return word:"+returnword);
		return returnword;
	}
	
	public String checkingOpponent(String inword,String outword){
		String returnword="";
		char[] inmas = inword.toCharArray();
		char[] outmas = outword.toCharArray();
			for(int i=0;i<inmas.length;i++){
				if(inmas[i]!='*'){
					outmas[i]='#';
				}
			}
			for(int i=0;i<outmas.length;i++){
				returnword = returnword+outmas[i];
			}
		return returnword;
	}
}
