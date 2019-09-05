package poem.generator;

import java.util.ArrayList;
import java.util.Random;

class PoemGenerator {

    enum PoemLength {
        SHORT, MEDIUM, LONG
    }

    private StringBuilder poem;
    private static final int STANZAS_FOR_SHORT_POEM = 1;
    private static final int STANZAS_FOR_MEDIUM_POEM = 2;
    private static final int STANZAS_FOR_LONG_POEM = 4;
    private Random random = new Random();

    String generatePoem(PoemLength poemLength) {
        poem = new StringBuilder();
        generateTitle();
        generateBody(poemLength);
        return poem.toString();
    }

    private void generateTitle() {
        addLineToPoem(composeTitle());
        addBlankLineToPoem();
    }

    private ArrayList<String> composeTitle() {
        ArrayList<String> title = new ArrayList<>();

        title.add("About");
        title.add(getRandomWord(PoemWords.NOUNS_PLURAL));
        title.add("and");
        title.add(getRandomWord(PoemWords.NOUNS_SINGULAR));

        return  title;
    }

    private void generateBody(PoemLength poemLength) {
        int numOfStanzas = determineStanzaCount(poemLength);
        for (int i = 1; i <= numOfStanzas; i++) {
            generateStanza(poemLength);
        }
    }

    private void generateStanza(PoemLength poemLength) {
        generateTwoRhymingLines();
        if (poemLength != PoemLength.SHORT) {
            generateTwoRhymingLines();
        }
        addBlankLineToPoem();
    }

    private void generateTwoRhymingLines() {
        RhymingWordPair rhymingWordPair = getRhymingWordPair(PoemWords.RHYMING_ADJECTIVES);
        generateVerseWithEnding(rhymingWordPair.getFirst());
        generateVerseWithEnding(rhymingWordPair.getSecond());
    }

    private void generateVerseWithEnding(String endingAdjective) {
        boolean usePlural = random.nextBoolean();
        if (usePlural) {
            generatePluralVerseWithEnding(endingAdjective);
        } else {
            generateSingularVerseWithEnding(endingAdjective);
        }
    }

    private void generateSingularVerseWithEnding(String endingAdjective) {
        ArrayList<String> verse = new ArrayList<>();
        verse.add(getRandomWord(PoemWords.NOUNS_SINGULAR));
        verse.add(getRandomWord(PoemWords.VERBS_SINGULAR));
        verse.add(endingAdjective);
        addLineToPoem(verse);
    }

    private void generatePluralVerseWithEnding(String endingAdjective) {
        ArrayList<String> verse = new ArrayList<>();
        verse.add(getRandomWord(PoemWords.NOUNS_PLURAL));
        verse.add(getRandomWord(PoemWords.VERBS_PLURAL));
        verse.add(endingAdjective);
        addLineToPoem(verse);
    }

    private int determineStanzaCount(PoemLength poemLength) {
        switch (poemLength) {
            case SHORT: return STANZAS_FOR_SHORT_POEM;
            case MEDIUM: return STANZAS_FOR_MEDIUM_POEM;
            case LONG: return STANZAS_FOR_LONG_POEM;
        }
        return -1;
    }

    private String getRandomWord(String[] words) {
        int length = words.length;
        int randomInt = getRandomInt(length);
        return words[randomInt];
    }

    private RhymingWordPair getRhymingWordPair(RhymingWordPair[] wordPairs) {
        int length = wordPairs.length;
        int randomInt = getRandomInt(length);
        return wordPairs[randomInt];
    }

    private void addBlankLineToPoem() {
        addLineToPoem(new ArrayList<>());
    }

    private void addLineToPoem(ArrayList<String> line) {
        for (String word : line) {
            addWordToPoem(word);
        }
        addWordToPoem("\n");
    }

    private void addWordToPoem(String toAdd) {
        poem.append(toAdd);
        poem.append(" ");
    }

    private int getRandomInt(int range) {
        return random.nextInt(range);
    }

}
