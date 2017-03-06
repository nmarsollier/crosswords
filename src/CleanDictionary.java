import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.nm.crosswords.dictionary.DictionaryLetter;

public class CleanDictionary {
	static Path source = Paths.get("./en-US.dic");

	public static void main(String[] args) throws IOException {
		HashMap<Integer, List<String>> words = new HashMap<>();
		Charset charset = Charset.forName("UTF-8");

		// Leo los valores y los pongo en un mapa idicando el tamaño de la palabra
		try (BufferedReader reader = Files.newBufferedReader(source, charset)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				line = line.trim().toUpperCase();
				if (!line.contains("'") && line.length() > 2 && DictionaryLetter.isValidWord(line)) {
					Integer size = line.length();

					List<String> tmp = words.get(size);
					if (tmp == null) {
						tmp = new ArrayList<String>();
						words.put(size, tmp);
					}
					
					tmp.add(line);
				}
			}
		} catch (IOException x) {
			x.printStackTrace();
		}

		// ordeno las palabras en el mapa
		for (Map.Entry<Integer, List<String>> entry : words.entrySet()) {
			List<String> value = entry.getValue();
			value.sort((o1, o2) -> o1.compareTo(o2));
		}

		// escribo los mapas en diferentes archivos
		for (Map.Entry<Integer, List<String>> entry : words.entrySet()) {
			Integer size = entry.getKey();
			List<String> list = entry.getValue();
			if (list.size() > 1000) {
				Path dest = Paths.get("./diccionario" + size + ".txt");

				try (Writer output = Files.newBufferedWriter(dest, StandardOpenOption.CREATE,
						StandardOpenOption.TRUNCATE_EXISTING)) {
					for (String s : list) {
						output.write(s);
						output.write("\n");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
