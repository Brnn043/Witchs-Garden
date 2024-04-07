import Games.GameController;

public class Main {
    public static void main(String[] args) {
        // GUI
        while(! GameController.getInstance().getGameover()){
            GameController.play();
        }
    }
}

