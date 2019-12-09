import model.*;
import controller.*;
import view.*;

public class Main {


    public static void main(String[] args) {
        
        GameModel model = new GameModel();
        GameController controller = new GameController(model);
        GameView view = new GameView(controller);
        
        model.addObserver(view);
    }

}
