package com.greg;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class WordCounter {
	
	public static void main(String args[]) {
		WCSingleThread single = new WCSingleThread();

		single.countSingleThread("hi hi hi hello hello hi");
		List<String> wordDocsList = new ArrayList<String>();
		
		wordDocsList.add("hi hi hi hi h a b cfs f");
		wordDocsList.add("he he he he");
		wordDocsList.add("ha ha ha ha");
		wordDocsList.add("ho ho ho ho");
		wordDocsList.add("hi hi hi hi");
		
		WCMultiThread  multiWC  = new WCMultiThread(wordDocsList);
		ConcurrentHashMap<String,Long> wordDocCounts = multiWC.countAllDocs();
		
		for (Map.Entry<String, Long> entry : wordDocCounts.entrySet()) {
			System.out.println(entry.getKey() + ", " + entry.getValue());
		}
	}
}
