package tictactoe;

class Field {

    private BoxState[][] boxes = {
        {BoxState.EMPTY, BoxState.EMPTY, BoxState.EMPTY},
        {BoxState.EMPTY, BoxState.EMPTY, BoxState.EMPTY},
        {BoxState.EMPTY, BoxState.EMPTY, BoxState.EMPTY},
    };

    BoxState getBoxState(int x, int y) {
        return boxes[x][y];
    }

    BoxState getBoxState(Position position) {
        return getBoxState(position.getX(), position.getY());
    }

    void markBox(int x, int y, BoxState newBoxState) {
        if (boxes[x][y] != BoxState.EMPTY) {
            throw new IllegalArgumentException("You can not mark a box, that is already marked by yourself or your enemy");
        } else if (newBoxState == BoxState.EMPTY) {
            throw new IllegalArgumentException("You can not mark a box as empty");
        } else {
            boxes[x][y] = newBoxState;
        }
    }

    void markBox(Position position, BoxState newBoxState) {
        markBox(position.getX(), position.getY(), newBoxState);
    }

    @Override
    public String toString() {
        return  boxes[0][0] + " " + boxes[1][0] + " " + boxes[2][0] + "\n" +
                boxes[0][1] + " " + boxes[1][1] + " " + boxes[2][1] + "\n" +
                boxes[0][2] + " " + boxes[1][2] + " " + boxes[2][2] + "\n";
    }
}
