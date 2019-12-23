package controller;

import model.Cell;
import model.CellStatus;
import model.Game;

public class MoveController implements Runnable {

    private int countOfOpenCells;

    private Game game;

    public MoveController(Game game) {
        this.game = game;
    }

    public int getCountOfOpenCells() {
        return countOfOpenCells;   // Добавлен геттер, чтобы мы снаружи этого класса могли знать сколько ячеек сейчас открыто. (нужно на обработке клика)
    }

    public void makeMove(Cell cell) {
        switch (countOfOpenCells) {
            case 0:
                CellStatusController.swap(cell);
                countOfOpenCells = 1; // одна ячейка открыта
                break;
            case 1:
                CellStatusController.swap(cell);
                countOfOpenCells = 2; // сейчас 2 ячейки открыто.
                new Thread(this).start(); // там внутри, после того как перевернём или уберём ячейеи, снова сделаем 0 открытых
                break;
            default:
                break;
        }

    }

    private void checkCells() {
        Cell[][] cells = game.getField().getCells();
        Cell c1 = null;
        Cell c2 = null;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j].getStatus() == CellStatus.OPENED) {
                    if (c1 == null) {
                        c1 = cells[i][j];
                    } else {
                        c2 = cells[i][j];
                    }
                }
            }
        }
        if (c1 == null || c2 == null) {

        } else {
            int v1 = c1.getValue();
            int v2 = c2.getValue();
            if (v1 == v2) {
                CellStatusController.makeInaktive(c1);
                CellStatusController.makeInaktive(c2);
            } else {
                CellStatusController.swap(c1);
                CellStatusController.swap(c2);
            }
        }
        countOfOpenCells = 0;   // вот тут мы сделали 0 открытых
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);    // ждём полсекунды, а потом скрываем ячейки.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        checkCells();    // метод, который проверяет ячейки на одинаковость, а потом убирает если открыты одинаковые и скрывает в обратном случае.
    }
}
