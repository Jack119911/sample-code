import java.util.Random;

public class LetterGenerator {

    private StringBuilder currentLetter;

    public String generateLetter(String greeting, boolean isReplyToLetter, int lengthInLines) {
        currentLetter = new StringBuilder();
        generateGreeting(greeting);
        generateBody(isReplyToLetter, lengthInLines);
        generateRegards();
        return currentLetter.toString();
    }

    private void generateGreeting(String greeting) {
        addLineToLetter(greeting);
        addNewLine();
    }

    private void generateBody(boolean isReplyToLetter, int lengthInLines) {
        if (isReplyToLetter) {
            generateThankYou();
        }
        for(int i = 0; i < lengthInLines; i++) {
            generateSentence();
        }
    }

    private void generateThankYou() {
        addLineToLetter(LetterBlocks.THANK_YOU);
    }

    private void generateSentence() {
        if (shouldGenerateStatement()) {
            generateStatement();
        } else {
            generateQuestion();
        }
    }

    private boolean shouldGenerateStatement() {
        return Math.random() < 0.6;
    }

    private void generateQuestion() {
        String randomQuestion = getRandomEntry(LetterBlocks.QUESTIONS);
        addLineToLetter(randomQuestion);
    }

    private void generateStatement() {
        String randomStatement = getRandomEntry(LetterBlocks.STATEMENTS);
        addLineToLetter(randomStatement);
    }

    private String getRandomEntry(String[] possibilities) {
        int randomIndex = new Random().nextInt(possibilities.length);
        return possibilities[randomIndex];
    }

    private void generateRegards() {
        addNewLine();
        addLineToLetter(LetterBlocks.REGARDS);
    }

    private void addLineToLetter(String line) {
        addToLetter(line);
        addNewLine();
    }

    private void addNewLine() {
        addToLetter(LetterBlocks.LINE_BREAK);
    }

    private void addToLetter(String toAdd) {
        currentLetter.append(toAdd);
    }

}
