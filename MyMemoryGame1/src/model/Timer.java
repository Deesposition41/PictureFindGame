package model;

import view.Animator;

public class Timer<s> implements Runnable {
    private int time;
    private int addTime = 100;
    private int frequency = 100;
    private int sleepTime = 1000 / frequency;
    String s = "";
    public Timer(int time) {
        this.time = time * frequency;
    }

    public void add() {
        time += addTime;
    }

    public void mine() {
        time -= addTime;
    }




    @Override
    public void run() {
        while (time > 0) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {

            }
            time--;
            Animator.Timeres(Game.timer);
        }

    }

    public int getTime() {
        return time;
    }
}
