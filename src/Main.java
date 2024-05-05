import Games.Config;
import Games.GameController;
import Items.Character.Slime;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // GUI
        while(! GameController.getInstance().getGameover()){
            GameController.play();
        }
    }
}

