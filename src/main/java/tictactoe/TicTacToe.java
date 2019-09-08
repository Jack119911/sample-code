package tictactoe;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

class TicTacToe {

    void makeAMove(Field field) {
        if (hasNoEmptyCells(field)) {
            throw new IllegalArgumentException("There are no empty cells. The game is finished");
        }
        if (markLastEmptyBox(field)) {
            return;
        }
        if (markLastBoxOfCompletableRow(field, BoxState.COMPUTER)) {
            return;
        }
        if (markLastBoxOfCompletableRow(field, BoxState.HUMAN)) {
            return;
        }
        markRandomEmptyCell(field);
    }

    private boolean hasNoEmptyCells(Field field) {
        return getEmptyCellPositions(field).isEmpty();
    }

    private boolean markLastEmptyBox(Field field) {
        ArrayList<Position> positions = getEmptyCellPositions(field);
        if (positions.size() == 1) {
            markBox(field, positions.get(0));
            return true;
        } else {
            return false;
        }
    }

    private boolean markLastBoxOfCompletableRow(Field field, BoxState boxStateToCheck) {
        for (int i = 0; i <= 2; i++) {
            Position[] rowHorizontal = getRowHorizontal(i);
            Position[] rowVertical = getRowVertical(i);
            if (rowCanBeCompleted(field, rowHorizontal, boxStateToCheck)) {
                markEmptyBoxOfRow(field, rowHorizontal);
                return true;
            } else if (rowCanBeCompleted(field, rowVertical, boxStateToCheck)) {
                markEmptyBoxOfRow(field, rowVertical);
                return true;
            }
        }
        return false;
    }

    private Position[] getRowVertical(int i) {
        return new Position[]{new Position(i, 0), new Position(i, 1), new Position(i, 2)};
    }

    private Position[] getRowHorizontal(int i) {
        return new Position[]{new Position(0, i), new Position(1, i), new Position(2, i)};
    }

    private boolean rowCanBeCompleted(Field field, Position[] positions, BoxState toComplete) {
        int numOfEmptyBoxes = 0;
        int numOfCorrectlyMarkedBoxey = 0;
        for (Position position : positions) {
            BoxState boxState = field.getBoxState(position);
            if (boxState == toComplete) {
                numOfCorrectlyMarkedBoxey++;
            } else if (boxState == BoxState.EMPTY) {
                numOfEmptyBoxes++;
            }
        }
        return numOfEmptyBoxes == 1 && numOfCorrectlyMarkedBoxey == 2;
    }

    private void markRandomEmptyCell(Field field) {
        ArrayList<Position> emptyCells = getEmptyCellPositions(field);
        int randomInt = ThreadLocalRandom.current().nextInt(0, emptyCells.size());
        markBox(field, emptyCells.get(randomInt));
    }

    private ArrayList<Position> getEmptyCellPositions(Field field) {
        ArrayList<Position> positionsOfEmptyCells = new ArrayList<>();
        for (int x = 0; x <= 2; x++) {
            for (int y = 0; y <= 2; y++) {
                if (field.getBoxState(x, y) == BoxState.EMPTY) {
                    positionsOfEmptyCells.add(new Position(x, y));
                }
            }
        }
        return positionsOfEmptyCells;
    }

    private void markEmptyBoxOfRow(Field field, Position[] row) {
        for (Position position : row) {
            if (field.getBoxState(position) == BoxState.EMPTY) {
                markBox(field, position);
                return;
            }
        }
        throw new IllegalArgumentException("No empty Box to mark");
    }

    private void markBox(Field field, Position position) {
        field.markBox(position, BoxState.COMPUTER);
    }

}
