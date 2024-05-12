package GUI;

import Games.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class PreStory extends GridPane {
    private ArrayList<String> analogs;
    private int count;
    private Text analog;
    private StackPane backgroundImage;
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
        analogs.add("Feeling sad, she thought \"Hmm, maybe I can make three magic potions from special veggies to get my power back\"");
        analogs.add("Off she went to her garden. But unexpectedly , her garden was full of slimy slimes!");
        analogs.add("She didn't give up! With a determined smile, she started her journey!");
        backgroundImage = new StackPane();

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
        this.add(textArea, 0, 2);
        this.add(nextButton, 2, 2);
        this.add(backgroundImage,0,1,2,1);
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));



        showLog(count);
    }

    public void showLog(int logCount){
        analog.setText(analogs.get(logCount));

        //set default background image
        Image image = new Image(ClassLoader.getSystemResource("Story/BeginStory_" + Integer.toString(logCount+1) + ".png").toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(700);
        imageView.setFitWidth(1100);
        Rectangle overlay = new Rectangle(1100, 500, Color.BLACK);
        backgroundImage.getChildren().addAll(overlay, imageView);

        count = count + 1;
    }
}
