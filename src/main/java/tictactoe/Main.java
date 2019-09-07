package tictactoe;

public class Main {

    public static void main(String[] args) {
        Field field = new Field();
        setTestPosition(field);
        testComputer(field);
    }

    private static void testComputer(Field field) {
        System.out.println(field);
        new TicTacToe().makeAMove(field);
        System.out.println(field);
    }

    private static void setTestPosition(Field field) {
        field.markBox(0, 0, BoxState.HUMAN);
        field.markBox(2, 2, BoxState.COMPUTER);
        field.markBox(2, 1, BoxState.HUMAN);
        field.markBox(0, 2, BoxState.COMPUTER);
        field.markBox(0, 1, BoxState.HUMAN);
    }

}
