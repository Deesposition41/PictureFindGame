package controller;

import model.Timer;

public class TimerController {

    public static void addTime(Timer timer){
        timer.add();
    }
    public static void mineTime(Timer timer){
        timer.mine();
    }

}
