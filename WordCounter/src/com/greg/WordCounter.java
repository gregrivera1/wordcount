package com.greg;

import java.util.*;

public class WordCounter {
	
	public static void main(String args[]) {
		WordCountSingleThread single = new WordCountSingleThread();
		single.countSingleThread("hi hi hi hello hello hi");
		
		HashMap<String,Long> singleWC = single.getWordCountMap();
		for (Map.Entry<String, Long> entry : singleWC.entrySet()) {
			System.out.println(entry.getKey() + ", " + entry.getValue());
		}
	}
}
