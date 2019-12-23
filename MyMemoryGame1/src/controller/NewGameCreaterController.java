package controller;

import model.Field;
import model.Game;
import model.Player;
import model.Timer;

public class NewGameCreaterController {

    public static Game createGame(String playerName,
                                  int time,
                                  int fieldW,
                                  int fieldH){
        Player p = new Player(playerName);
        Timer timer = new Timer(time);
        Field field = new Field(fieldW, fieldH);
        Game game = new Game(p, field, timer);
        return game;

    }

}
