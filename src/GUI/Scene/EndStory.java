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

// this the story after ending the game
public class EndStory extends GridPane {
    private final ArrayList<String> ANALOGS;
    private int count;
    private Text analog;
    private ImageView storyImage;
    private Button nextButton;
    private VBox textArea;

    @FunctionalInterface
    public interface GameEnding {
        void CompleteGame();
    }

    public EndStory(GameEnding gameEnding) {
        count = 0;

        ANALOGS = new ArrayList<>();
        storyImage = new ImageView();

        initializeAnalogs();
        initializeNextButton(gameEnding);
        initializeTextArea();

        this.add(storyImage, 0, 0, 2, 1);
        this.add(textArea, 0, 1);
        this.add(nextButton, 1, 1);

        setStyle();
        showLog(count);

        RenderableHolder.gameSong.stop();
        RenderableHolder.mainMenuSong.stop();
        RenderableHolder.storySong.play();
    }

    private void setStyle() {
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPadding(new Insets(0,10,20,10));
        this.setHgap(10);
        this.setVgap(0);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
    }

    private void initializeAnalogs() {
        ANALOGS.add("The witch ate up all her potions.");
        ANALOGS.add("Afterward, her power returned in a rush.");
    }

    private void initializeNextButton(GameEnding gameEnding) {
        nextButton = new Button("Next...");
        nextButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 18));
        nextButton.setTextFill(Color.WHITE);
        nextButton.setBackground(new Background(
                new BackgroundFill(Color.valueOf("#8F6F5C"), new CornerRadii(8), null)));
        nextButton.setPrefWidth(180);
        nextButton.setPrefHeight(50);
        nextButton.setOnMouseEntered(e -> {
            nextButton.setBackground(new Background(
                    new BackgroundFill(Color.LIGHTPINK, new CornerRadii(8), null)));
        });
        nextButton.setOnMouseExited(e -> {
            nextButton.setBackground(new Background(
                    new BackgroundFill(Color.valueOf("#8F6F5C"), new CornerRadii(8), null)));
        });
        nextButton.setOnAction(e -> {
            if (count == ANALOGS.size()) {
                gameEnding.CompleteGame();
            }
            else {
                showLog(count);
            }
        });
    }

    private void initializeTextArea() {
        analog = new Text();
        analog.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 16));
        analog.setFill(Color.WHITE);

        textArea = new VBox();
        textArea.setAlignment(Pos.CENTER);
        textArea.setSpacing(5);
        textArea.setPrefWidth(850);
        textArea.setPrefHeight(150);
        textArea.getChildren().add(analog);
    }

    private void showLog(int logCount){
        analog.setText(ANALOGS.get(logCount));

        //set default background image
        Image image = new Image(ClassLoader.getSystemResource("Story/EndStory_"
                + (logCount + 1) + ".png").toString());
        storyImage.setImage(image);
        storyImage.setFitHeight(450);
        storyImage.setFitWidth(1100);

        count++;
    }
}
