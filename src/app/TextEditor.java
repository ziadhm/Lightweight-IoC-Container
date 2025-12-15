package app;

public class TextEditor {
    private final SpellChecker spellChecker;

    public TextEditor(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

    public void checkText(String text) {
        String result = spellChecker.checkSpelling(text);
        System.out.println(result);
    }
}
