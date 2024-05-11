package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class PreStory extends GridPane {
    private ArrayList<String> analogs;
    private int count;
    private Text analog;
    @FunctionalInterface
    public interface GameStarter {
        void startGame();
    }
    public PreStory(GameStarter gameStarter) {
        count = 0;
        analog = new Text();
        analogs = new ArrayList<String>();
        analogs.add("Once upon a time, in a forest far, far away, there lived a witch with extraordinary powers.");
        analogs.add("One morning, she woke up to a surprising discovery - her magic had disappeared!");
        analogs.add("Feeling sad, she thought");
        analogs.add("\"Hmm, maybe I can make three magic potions from special veggies to get my power back\"");
        analogs.add("Off she went to her garden. But unexpectedly , her garden was full of slimy slimes!");
        analogs.add("She didn't give up! With a determined smile, she started her journey!");

        //set default background image
        Image image = new Image(ClassLoader.getSystemResource("MenuPage/Sunny_MenuPage.gif").toString());
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,   // Repeat settings
                BackgroundRepeat.NO_REPEAT,
                javafx.scene.layout.BackgroundPosition.DEFAULT,
                new BackgroundSize(
                        BackgroundSize.AUTO,  // Width
                        BackgroundSize.AUTO,  // Height
                        false,               // Preserve ratio
                        false,               // Smooth
                        true,                // Resize to fit
                        false                // Repeat vertically
                )
        );
        Background background = new Background(backgroundImage);
        this.setBackground(background);

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
            if(count == analogs.size()){
                gameStarter.startGame();
            }else{
                showLog(count);
            }
        });

        analog = new Text();
        analog.setText("H");
        analog.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 16));
        analog.setFill(Color.BLACK);
        VBox textArea = new VBox();
        textArea.setAlignment(Pos.CENTER);
        textArea.setSpacing(5);
        textArea.setPrefWidth(850);
        textArea.setPrefHeight(150);
        textArea.getChildren().add(analog);
        textArea.setBackground(new Background(new BackgroundFill(Color.rgb(255, 153, 204, 0.9), new CornerRadii(8), null)));

        this.setAlignment(Pos.BOTTOM_LEFT);
        this.add(textArea, 0, 0);
        this.add(nextButton, 2, 0);
        this.setPadding(new Insets(20));
        this.setHgap(10);
        showLog(count);
    }

    public void showLog(int logCount){
        analog.setText(analogs.get(logCount));
        count = count + 1;
    }
}
