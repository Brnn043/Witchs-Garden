package GUI.Scene;

import GUISharedObject.RenderableHolder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class PreStory extends GridPane {
    private ArrayList<String> ANALOGS;
    private int count;
    private Text analog;
    private ImageView backgroundImage;
    @FunctionalInterface
    public interface GameStarter {
        void startGame();
    }
    public PreStory(GameStarter gameStarter) {
        count = 0;
        analog = new Text();
        ANALOGS = new ArrayList<>();
        ANALOGS.add("Once upon a time, in a forest far, far away, there lived a witch with extraordinary powers.");
        ANALOGS.add("One morning, she woke up to a surprising discovery - her magic had disappeared!");
        ANALOGS.add("Luckily, she can still manipulate the weather as desired.");
        ANALOGS.add("She thought \"Hmm, maybe I can make three magic potions from special veggies to get my power back\"");
        ANALOGS.add("Off she went to her garden. But unexpectedly , her garden was full of slimy slimes!");
        ANALOGS.add("She didn't give up! With a determined smile, she started her journey!");
        backgroundImage =  new ImageView();

        Button nextButton = new Button("Next...");
        nextButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 18));
        nextButton.setTextFill(Color.WHITE);
        nextButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#8F6F5C"), new CornerRadii(8), null)));
        nextButton.setPrefWidth(180);
        nextButton.setPrefHeight(50);
        nextButton.setOnMouseEntered(e -> {
            nextButton.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, new CornerRadii(8), null)));
        });
        nextButton.setOnMouseExited(e -> {
            nextButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#8F6F5C"), new CornerRadii(8), null)));
        });
        nextButton.setOnAction(e -> {
            if(count == ANALOGS.size()){
                gameStarter.startGame();
            }else{
                showLog(count);
            }
        });

        analog = new Text();
        analog.setText("H");
        analog.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 16));
        analog.setFill(Color.WHITE);
        VBox textArea = new VBox();
        textArea.setAlignment(Pos.CENTER);
        textArea.setSpacing(5);
        textArea.setPrefWidth(850);
        textArea.setPrefHeight(150);
        textArea.getChildren().add(analog);

        this.setAlignment(Pos.CENTER_LEFT);
        this.add(backgroundImage,0,0,2,1);
        this.add(textArea, 0, 1);
        this.add(nextButton, 1, 1);
        this.setPadding(new Insets(0,10,20,10));
        this.setHgap(10);
        this.setVgap(0);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        showLog(count);

        RenderableHolder.gameSong.stop();
        RenderableHolder.mainManuSong.stop();
        RenderableHolder.storySong.play();
    }

    public void showLog(int logCount){
        analog.setText(ANALOGS.get(logCount));

        //set default background image
        Image image = new Image(ClassLoader.getSystemResource("Story/BeginStory_" + Integer.toString(logCount+1) + ".png").toString());
        backgroundImage.setImage(image);
        backgroundImage.setFitHeight(450);
        backgroundImage.setFitWidth(1100);

        count++;
    }
}
