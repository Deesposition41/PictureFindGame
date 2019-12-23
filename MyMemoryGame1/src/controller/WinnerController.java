package controller;

import model.Cell;
import model.CellStatus;
import model.Game;
import model.GameStatus;

public class WinnerController {

    public static GameStatus checkGameStatus(Game g){
        if(g.getTimer().getTime() <= 0){
            return GameStatus.LOSE;
        }
        Cell[][] cells = g.getField().getCells();
        boolean win = true;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if(cells[i][j].getStatus() != CellStatus.INACTIVE){
                    win = false;
                }
            }
        }
        if(win == true){
            return GameStatus.WIN;
        } else {
            return GameStatus.PLAY;
        }

    }
}
