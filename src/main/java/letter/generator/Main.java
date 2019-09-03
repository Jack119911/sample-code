package letter.generator;

public class Main {

    public static void main(String[] args) {
        LetterGenerator letterGenerator = new LetterGenerator();
        System.out.println(letterGenerator.generateLetter("Guten Tag Herr Mustermann", true, 6));
    }

}
