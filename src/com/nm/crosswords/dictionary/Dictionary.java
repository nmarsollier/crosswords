package com.nm.crosswords.dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class Dictionary {
	List<String> words = new ArrayList<>();
	DictionaryMap[] maps;

	public Dictionary(String filePath) throws IOException {
		loadWords(filePath);
	}

	public BitSet getWordsBegginingWith(byte letter) {
		List<Integer> result = new ArrayList<>();
		return maps[0].getWords(letter);
	}

	public BitSet getWordsMatching(byte[] word) {
		BitSet result = new BitSet(words.size());
		result.set(0, words.size());
		for (int i = 0; i < word.length; i++) {
			if (word[i] >= 0) {
				result.and(maps[i].getWords(word[i]));
			}
		}
		return result;
	}

	private void loadWords(String filePath) throws IOException {
		words.clear();
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath), Charset.forName("UTF-8"))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.length() > 1) {
					words.add(line);
				}
			}
		}
		initializeDictionary();
	}

	private void initializeDictionary() {
		initializeDictionayMaps();
		for (int wIndex = 0; wIndex < words.size(); wIndex++) {
			byte[] word = words.get(wIndex).getBytes();
			for (int pos = 0; pos < word.length; pos++) {
				maps[pos].addWord(wIndex, word[pos]);
			}
		}
	}

	private void initializeDictionayMaps() {
		int wordSize = words.get(0).length();
		maps = new DictionaryMap[wordSize];
		for (int i = 0; i < maps.length; i++) {
			maps[i] = new DictionaryMap(wordSize);
		}
	}
}
