package app;

public class EnglishSpellChecker implements SpellChecker {
    private final Dictionary dictionary;

    public EnglishSpellChecker(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public String checkSpelling(String text) {
        String[] words = text.split("\\s+");
        for (String word : words) {
            if (!dictionary.containsWord(word)) {
                return "Misspelled word found: " + word;
            }
        }
        return "All words are spelled correctly!";
    }
}
