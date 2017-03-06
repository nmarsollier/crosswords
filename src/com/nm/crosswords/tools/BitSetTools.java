package com.nm.crosswords.tools;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class BitSetTools {

	public static List<Integer> getIndices(BitSet bs) {
		List<Integer> result = new ArrayList<>();
		for (int i = bs.nextSetBit(0); i >= 0; i = bs.nextSetBit(i + 1)) {
			result.add(i);
		}
		return result;
	}
}
