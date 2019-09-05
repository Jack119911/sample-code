package poem.generator;

public class Main {

    public static void main(String[] args) {
        String poem = new PoemGenerator().generatePoem(PoemGenerator.PoemLength.MEDIUM);
        System.out.println(poem);
    }

}
