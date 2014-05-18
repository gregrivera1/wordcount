package com.greg;

import java.util.HashMap;


public class WordCountSingleThread {
	private HashMap<String, Long> wordcount = new HashMap<String,Long>();
	
	public HashMap<String, Long> countSingleThread(String wordList) {
		String wordBuilder = "";
		
		for(int i = 0; i != wordList.length(); i++) {
			if(wordList.charAt(i) == ' ') {
				addWordToHash(wordBuilder);
				wordBuilder = "";
			} else {
				wordBuilder += wordList.charAt(i);
			}
		}
		addWordToHash(wordBuilder); //leftovers, if any
		return wordcount;
	}
	
	private void addWordToHash(String word) {
		if(word.isEmpty()) {
			return;
		}
		if(wordcount.containsKey(word)) {
			wordcount.put(word, wordcount.get(word)+1L);
		} else {
			wordcount.put(word, 1L);
		}
	}
	
	public HashMap<String,Long> getWordCountMap() {
		return wordcount;
	}
}
