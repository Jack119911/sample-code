package poem.generator;

public class PoemWords {

    private PoemWords() {}

    static final String[] NOUNS_PLURAL = {
            "Roses", "Friends", "Dreams", "The mountains",
    };

    static final String[] NOUNS_SINGULAR = {
            "The see", "The sun", "Coffee", "Chocolate", "Blue", "My Car", "Your hair", "Regensburg",
    };

    static final String[] VERBS_PLURAL = {
            "are", "smell", "look", "feel", "behave"
    };

    static final String[] VERBS_SINGULAR = {
            "is", "smells", "looks", "feels", "behaves"
    };

    static final RhymingWordPair[] RHYMING_ADJECTIVES = {
            new RhymingWordPair("fantastic", "like plastic"), new RhymingWordPair("green", "clean"),
            new RhymingWordPair("complicated", "hated"), new RhymingWordPair("cute", "like a suit"),
    };

}
