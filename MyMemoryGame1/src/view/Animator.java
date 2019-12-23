package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import controller.TimerController;
import model.Cell;
import model.CellStatus;
import model.Game;
import model.Timer;
import util.constants;

import javax.imageio.ImageIO;

public class Animator implements Runnable {
    public static Graphics graphics;

    private static Graphics screenGraphics;
    private HashMap<Integer, BufferedImage> images;
    public static BufferedImage img;
    private Cell[][] cells;
    private int timerHeight;
    private static int fieldHeight;
    private int fieldWidth;
    Timer timer;
    int valuetime;
    int AllTime;

    public Animator(Graphics screenGraphics, Game game) {
        this.screenGraphics = screenGraphics;    // графикс, который будет рисвать на панельке.
        this.cells = game.getField().getCells();  // это массив ячеек, вытащили из игры, больше нам из неё тут ничего не нужно
        fieldHeight = constants.COUNT_OF_COLS * constants.SIZE_OF_CELL;  // чтобы уменьшить количество вычислений, сохраняю эти данные в переменную
        fieldWidth = constants.COUNT_OF_COLS * constants.SIZE_OF_CELL;
        img = new BufferedImage(fieldWidth, fieldHeight, BufferedImage.TYPE_3BYTE_BGR); // картинка, для двойной буфферизации
        graphics = img.getGraphics(); // графикс, который будет рисовать внутри картники (двойная буфферизация)
        timerHeight = fieldHeight;  // нужно для отрисовки таймера, пока не доделано.
        initImages();  // из файлов подтягиваем все картинки и сохраняем их в словарь images
        timer = new Timer(10);
        valuetime = 20;
        AllTime = 1000;
    }

    private void initImages() {
        images = new HashMap<>();  // инициализируем словарь.
        int imagesCount = constants.COUNT_OF_COLS * constants.COUNT_OF_RAWS / 2; // количество разных картинок.
        try {
            for (int i = 0; i < imagesCount; i++) {
                BufferedImage image = ImageIO.read(   // тут читаем картинки из файлов. ниже дан адрес каждого файла
                        new File(
                                constants.FOLDER_NAME + "\\" + i + ".jpg"
                        )
                );
                images.put(i, image);
            }
            BufferedImage image = ImageIO.read(
                    new File(constants.FOLDER_NAME + "\\-1.jpg")
            );
            images.put(-1, image);  // добавляю картинку с оборотной стороной карточек. Её value ставлю -1д
        } catch (IOException e) {
            System.out.println("ошибка при инициализации картинки");
            e.printStackTrace();
        }

    }

    private void drawAll() {
        resetImage();// обнуляем картинку.
        drawCells();  // рисуем все картинки внутри двойной буфферизации
        drawToScreen(); // рисуем всё что получилось на экран
    }


    public static void resetImage() {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, img.getWidth(), img.getHeight());
        graphics.setColor(Color.BLACK);
    }


    public static void LoseCounter() {
        String s = "";
        s = "You Lose!";
        graphics.drawString(s, 400, 400);
        graphics.fillRect(300, 500, constants.SIZE_OF_TIMER, fieldHeight);
        drawToScreen();
    }


    public static void WinCounter() {
        String s = "";
        s = "You Win!";
        graphics.drawString(s, 400, 400);
        graphics.fillRect(300, 500, constants.SIZE_OF_TIMER, fieldHeight);
        drawToScreen();
    }

    public static void Timeres(Timer timer) {
        String s = "TimeCash:" + timer.getTime();
        graphics.drawString(s, 400, 450);
        graphics.fillRect(300, 500, constants.SIZE_OF_TIMER, fieldHeight);
        drawToScreen();
    }

    public void drawCells() {
        for (int i = 0; i < constants.COUNT_OF_RAWS; i++) {
            for (int j = 0; j < constants.COUNT_OF_COLS; j++) {
                Cell c = cells[i][j];
                int value = c.getValue();
                CellStatus status = c.getStatus();
                int x = constants.SIZE_OF_CELL * i;
                int y = constants.SIZE_OF_CELL * j;
                switch (status) {     // делаем выбор исходя из статуса найшей ячейки
                    case CLOSSED:
                        graphics.drawImage(
                                images.get(-1),  // рисуем задник.
                                x, y, constants.SIZE_OF_CELL, constants.SIZE_OF_CELL,
                                null
                        )
                        ;
                        break;
                    case OPENED:
                        graphics.drawImage(
                                images.get(value), // рисуем картинку с соответсвующим value
                                x, y, constants.SIZE_OF_CELL, constants.SIZE_OF_CELL,
                                null
                        );
                        break;
                    case INACTIVE:
                        break;  // неактивные ячейки просто игнорируем
                }
            }
        }
    }

    private static void drawToScreen() {
        screenGraphics.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(33);   // 30 раз в секунда обновляем картинку на экране
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            drawAll();

        }

    }
}
