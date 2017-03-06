package com.nm.crosswords.dictionary;

import org.apache.commons.lang3.ArrayUtils;

public class DictionaryLetter {
	static byte[] AVAILABLE_LETTERS = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
			'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	private static int[] letterIndex = new int[255];

	public static int getIndexForChar(byte c) {
		return letterIndex[c];
	}

	public static int getIndexForChar(char c) {
		return letterIndex[c];
	}

	public static boolean isValidWord(String line) {
		byte[] letters = line.getBytes();
		for (byte b : letters) {
			if (!ArrayUtils.contains(AVAILABLE_LETTERS, b)) {
				return false;
			}
		}
		return true;
	}

	static {
		for (int i = 0; i < AVAILABLE_LETTERS.length; i++) {
			letterIndex[AVAILABLE_LETTERS[i]] = i;
		}
	}

}
