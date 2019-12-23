package model;

import Exceptions.CountOfCellsIsOddException;
import java.util.Random;

public class Field {

    private Cell[][] cells;

    public Field(int width, int height) {
        if (width * height % 2 == 1) {
            throw new CountOfCellsIsOddException();
        }
        cells = new Cell[height][width];
        init();
    }

    public void init() {
        int countOfCells = cells.length * cells[0].length;
        int countOfValues = countOfCells / 2;
        int[] values = new int[countOfCells];
        for (int i = 0; i < countOfCells; i++) {
            values[i] = i % countOfValues;
        }
        shuffle(values);
        int index = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = new Cell(values[index]);
                index++;
            }
        }
    }

    private void shuffle(int[] array) {
        Random r = new Random();
        for (int j = 0; j < r.nextInt(10) + 2; j++) {
            for (int i = 0; i < array.length; i++) {
                int index = r.nextInt(array.length);
                int temp = array[i];
                array[i] = array[index];
                array[index] = temp;
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }
}
