package model;

public class Game {

    private Player player;
    private Field field;
    public static   Timer timer;


    public Game(Player player, Field field, Timer timer) {
        this.player = player;
        this.field = field;
        this.timer = timer;
    }

    public static void start() {
        new Thread(timer).start();
    }

    public Player getPlayer() {
        return player;
    }

    public Field getField() {
        return field;
    }

    public Timer getTimer() {
        return timer;
    }


    @Override
    public String toString() {
        String playerName = player.getName();
        int fieldW = field.getCells()[0].length;
        int fieldH = field.getCells().length;
        int timerT = timer.getTime();
        return playerName + "/n"
                + fieldW + "/n"
                + fieldH + "/n"
                + timerT;
    }
}
