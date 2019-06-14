import java.util.Random;

public class LetterGenerator {

    private String currentLetter;
    private String[] questions = {
            "Wie steht es um Ihre Gesundheit?",
            "Haben Sie das Fu\u00dfballspiel gestern Abend gesehen?",
            "Wie geht es der Familie?",
            "Kennen Sie schon den neuen Film mit Leonardo DiCaprio",
            "Wie ist das Wetter bei Ihnen?"
    };
    private String[] statements = {
            "Meine Frau hat gestern einen fantastischen Apfelkuchen gebacken.",
            "Beim Wandern gestern konnte ich mich sehr entspannen.",
            "Der neue Schirach ist ein wahres Meisterwerk.",
            "Die letzten Jahre habe ich den Jazz f\u00fcr mich entdeckt.",
            "Im n\u00e4chsten Sommer machen wir Urlaub in Island"
    };


    public LetterGenerator() {

    }

    public String generateLetter(String sirName, boolean isMale, boolean isResponse) {
        currentLetter = "";
        generateGreeting(sirName, isMale);
        generateBody(isResponse);
        generateRegards();
        return currentLetter;
    }

    private void generateGreeting(String sirName, boolean isMale) {
        if (isMale) {
            addLineToLetter("Sehr geehrter Herr " + sirName);
        } else {
            addLineToLetter("Sehr geehrte Frau " + sirName);
        }
        addNewLine();
    }

    private void generateBody(boolean isResponse) {
        if (isResponse) {
            generateThankYou();
        }
        for(int i = 0; i < 5; i++) {
            generateSentence();
        }
    }

    private void generateThankYou() {
        addLineToLetter("Vielen Dank für Ihren Brief, er war mir eine große Freude.");
    }

    private void generateSentence() {
        if (shouldGenerateSentence()) {
            generateStatement();
        } else {
            generateQuestion();
        }
    }

    private boolean shouldGenerateSentence() {
        return Math.random() < 0.6;
    }

    private void generateQuestion() {
        String randomQuestion = getRandomEntry(questions);
        addLineToLetter(randomQuestion);
    }

    private void generateStatement() {
        String randomStatement = getRandomEntry(statements);
        addLineToLetter(randomStatement);
    }

    private String getRandomEntry(String[] possibilities) {
        int randomIndex = new Random().nextInt(possibilities.length);
        return possibilities[randomIndex];
    }

    private void generateRegards() {
        addNewLine();
        addLineToLetter("Viele Gr\u00fc\u00dfe,");
    }

    private void addLineToLetter(String line) {
        addToLetter(line);
        addNewLine();
    }

    private void addNewLine() {
        addToLetter("\n");
    }

    private void addToLetter(String toAdd) {
        currentLetter += toAdd;
    }

}
