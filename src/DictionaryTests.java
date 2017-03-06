import java.io.IOException;

import com.nm.crosswords.dictionary.Dictionary;
import com.nm.crosswords.dictionary.DictionaryLetter;
import com.nm.crosswords.tools.BitSetTools;

public class DictionaryTests {

	public static void main(String[] args) throws IOException {
		Dictionary dict= new Dictionary("./diccionario3.txt");
		System.out.print(BitSetTools.getIndices(dict.getWordsMatching(new byte[]{(byte) 'A', -1, (byte) 'C'} )));
	}
	
}
