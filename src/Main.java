import Games.Config;
import Games.GameController;
import Items.Character.Zombie;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // GUI
        int i = 0;
        while(! GameController.getInstance().getGameover() & i<5){
            GameController.play();
        }
        System.out.println("Game Over, You got money " + GameController.getInstance().getPlayer().getMoney());
    }
}

