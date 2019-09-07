package tictactoe;

import java.security.InvalidParameterException;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        if (indexOutOfRange(x) ||indexOutOfRange(y)) {
            throw new InvalidParameterException("The only possible numbers for x and y are 0, 1 or 2");
        } else {
            this.x = x;
            this.y = y;
        }
    }

    private boolean indexOutOfRange(int i) {
        return (i < 0 || i > 2);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
