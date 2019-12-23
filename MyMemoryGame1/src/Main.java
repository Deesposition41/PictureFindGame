import controller.MoveController;
import controller.NewGameCreaterController;
import model.Game;
import util.constants;
import view.GameWindow;

public class Main {

    public static void main(String[] args) {
        Game g = NewGameCreaterController.createGame(
                "Mechmat",
                1000,
                constants.COUNT_OF_COLS,
                constants.COUNT_OF_RAWS
        );  // создаём игру. Делаем это через контроллер. Можно напрямую, но этот вариант более гибкий и в дальнейшем проще менять саму игру.
        MoveController moveController = new MoveController(g); // создаём контроллер ходов.
        GameWindow window = new GameWindow(g, moveController); // создаём игровое окно, передавая в него информацию об игре и контроллере ходов.
    }

}
