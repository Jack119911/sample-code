package poem.generator;

import java.util.Random;

class PoemGenerator {

    Random rand = new Random();

    String generatePoem() {
        for (int i = 0; i < 3; i++) {
            RhymingWordPair pair = PoemWords.RHYMING_ADJECTIVES[randomInt(PoemWords.RHYMING_ADJECTIVES.length)];
            System.out.print(PoemWords.NOUNS_PLURAL[randomInt(PoemWords.NOUNS_PLURAL.length)] + " ");
            System.out.print(PoemWords.VERBS_PLURAL[randomInt(PoemWords.VERBS_PLURAL.length)] + " ");
            System.out.println(pair.getFirst());
            System.out.print(PoemWords.NOUNS_SINGULAR[randomInt(PoemWords.NOUNS_SINGULAR.length)] + " ");
            System.out.print(PoemWords.VERBS_SINGULAR[randomInt(PoemWords.VERBS_SINGULAR.length)] + " ");
            System.out.println(pair.getSecond());
        }
        return "";
    }

    private int randomInt(int range) {
        return rand.nextInt(range);
    }

}
