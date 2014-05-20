package com.greg;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WCMultiThread  {
	private ConcurrentHashMap<String, Long> wordcount = new ConcurrentHashMap<String,Long>();
	private int threadPoolSize = 1;
	private ExecutorService tpool = null;
	private List<String> wordList;
	
	WCMultiThread(List<String> wordList) {
		this.wordList = wordList;
		tpool =  Executors.newFixedThreadPool(threadPoolSize);
    }
	
	public ConcurrentHashMap<String,Long> countAllDocs() {
		unleashThreads();
		return wordcount;
	}
	
	public void unleashThreads() {
		for(final String words : wordList) {
			tpool.execute(new Runnable() {
			    public void run() {
			    	//System.out.println(words);
			        countSingleThread(words);
			    }
			});
		}
		tpool.shutdown();
		try {
			tpool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void countSingleThread(String wordList) {
		String wordBuilder = "";
		List<String> toAddToHash = new ArrayList<String>();
		for(int i = 0; i != wordList.length(); i++) {
			if(wordList.charAt(i) == ' ') {
				toAddToHash.add(wordBuilder);
				wordBuilder = "";
			} else {
				wordBuilder += wordList.charAt(i);
			}
		}
		toAddToHash.add(wordBuilder); //leftovers, if any
		//add at once to the resulting hash map
		addWordsToHash(toAddToHash);
	}
	
	private synchronized void addWordsToHash(List<String> wordsToAdd) {
		if(wordsToAdd.isEmpty()) {
			return;
		}
		for(String word : wordsToAdd) {
			if(wordcount.containsKey(word)) {
				wordcount.put(word, wordcount.get(word)+1L);
			} else {
				wordcount.put(word, 1L);
			}
		}
	}
}
