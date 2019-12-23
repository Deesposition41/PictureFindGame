package view;

import javax.swing.*;

import Exceptions.UsageOfInactiveCellException;
import controller.MoveController;
import controller.TimerController;
import controller.WinnerController;
import model.*;
import model.Timer;
import model.GameStatus;
import util.constants;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GameWindow extends JFrame {
    int AllTime;

    public GameWindow(Game game, MoveController moveController) {
        super(); // конструктор перегружен, поэтому вызываем вначале пустой конструктор класса родителя
        this.setBounds(
                100, 100,
                constants.COUNT_OF_RAWS * constants.SIZE_OF_CELL + constants.SIZE_OF_TIMER,
                constants.COUNT_OF_COLS * constants.SIZE_OF_CELL + 50); // +50 - это для того, чтобы не съедался нижний край снежинок. Это приходится делать при использовании панели, если рисовать без неё то это добавлять не нужно. (попробуйте)
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, this.getWidth(), this.getHeight());
        panel.setBackground(Color.WHITE);
        this.add(panel);
        this.setVisible(true);
        Animator animator = new Animator(panel.getGraphics(), game);  // создаём аниматор, в него переходит графикс, который будет рисовать на панели, а также сама игра. Из неё мы сможем получиться всю необходимую информацию для отрисовки.
        AllTime = 100;
        game.timer = new Timer(AllTime);
        GameStatus losestatus = GameStatus.LOSE;
        GameStatus winstatus = GameStatus.WIN;
        GameStatus playstatus = GameStatus.PLAY;

        panel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / constants.SIZE_OF_CELL;  // получили номер ячейки, которую кликнул игрок. (по X)
                int y = e.getY() / constants.SIZE_OF_CELL; // получили номер ячейки, которую кликнул игрок. (по Y)
                Cell[][] cells = game.getField().getCells();
                if (x < cells.length && y < cells[0].length) {  // убедились что кликнули на ячейку, а не куда-то мимл.
                    Cell c = game.getField().getCells()[x][y];  // зафиксировали эту ячейку
                    try {
                        if (moveController.getCountOfOpenCells() < 2) {   // если открыто 2 ячейки, то ничего не делаем.
                            moveController.makeMove(c);  // пытаемся сходить.
                        }
                    } catch (UsageOfInactiveCellException us) {
                        // если ход идёт на инактивную ячейку, то у нас будет ошибка. тут ловим её и игнорируем это действие.
                    }

                    if (moveController.getCountOfOpenCells() == 2) {
                        if (c.getStatus() == CellStatus.INACTIVE) {
                            TimerController.addTime(game.timer);
                        }
                    }
                    if (moveController.getCountOfOpenCells() == 2) {
                        if (c.getStatus() != CellStatus.INACTIVE){
                            TimerController.mineTime(game.timer);
                        }
                    }

                    WinnerController.checkGameStatus(game);
                    if (WinnerController.checkGameStatus(game) == winstatus) {
                        animator.WinCounter();
                    }
                    if (WinnerController.checkGameStatus(game) == losestatus) {
                        animator.LoseCounter();
                    }

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        new Thread(animator).start();
        Game.start();

    }

}
