package app;

import java.util.*;

public class SimpleDictionary implements Dictionary {
    private final Set<String> words = new HashSet<>();

    public SimpleDictionary(FileLoader fileLoader) {
        String data = fileLoader.loadData();
        String[] wordArray = data.split("\\s+");
        words.addAll(Arrays.asList(wordArray));
    }

    @Override
    public boolean containsWord(String word) {
        return words.contains(word);
    }
}
