package com.nm.crosswords.dictionary;

import java.util.BitSet;

public class DictionaryMap {
	BitSet[] wordMap = new BitSet[DictionaryLetter.AVAILABLE_LETTERS.length]; 

	public DictionaryMap(int wordCount) {
		for(int i=0; i<DictionaryLetter.AVAILABLE_LETTERS.length; i++){
			wordMap[i] = new BitSet(wordCount);
		}
	}
	
	public BitSet getWords(byte letter) {
		return wordMap[DictionaryLetter.getIndexForChar(letter)];
	}
	
	public void addWord(int position, byte letter) {
		wordMap[DictionaryLetter.getIndexForChar(letter)].set(position);
	}
}
