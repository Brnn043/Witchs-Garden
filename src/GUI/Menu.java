package GUI;

import Games.Config;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Menu extends GridPane {
    @FunctionalInterface
    public interface PreStory {
        void preStory();
    }
    public Menu(PreStory preStory, Stage stage) {
        Text titleText = new Text("Witch's Garden");
        titleText.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 40)); // Set font and size
        titleText.setFill(Color.WHITE);

        // howToPlayMenu
        VBox howToPlayMenu = new VBox();
        howToPlayMenu.setAlignment(Pos.CENTER);
        howToPlayMenu.setSpacing(5);
        ImageView imageView =  new ImageView(ClassLoader.getSystemResource("MenuPage/HowToPlay.png").toString());
        imageView.setFitHeight(270);
        howToPlayMenu.getChildren().add(imageView);
        howToPlayMenu.setVisible(false);
        howToPlayMenu.setBackground(new Background(new BackgroundFill(Color.rgb(255, 153, 204, 0.9), new CornerRadii(5), null)));

        // creditMenu
        VBox creditMenu = new VBox();
        creditMenu.setAlignment(Pos.CENTER);
        creditMenu.setSpacing(5);
        creditMenu.getChildren().addAll(
                new Text("This project created by"),
                new Text("Naphat Serirak 6632061321"),
                new Text("Raksakul Hiranas 6632190621"),
                new Text("                   "),
                new Text("2110215 (2023/2)"),
                new Text("Programming Methodology I")
        );
        creditMenu.setVisible(false);
        creditMenu.setBackground(new Background(new BackgroundFill(Color.rgb(255, 153, 204, 0.9), new CornerRadii(8), null)));

        // Add important Button
        Button startButton = new Button("Start Game");
        startButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 18));
        startButton.setTextFill(Color.WHITE);
        startButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#8F6F5C"), new CornerRadii(8), null)));
        startButton.setPrefWidth(180);
        startButton.setPrefHeight(50);
        startButton.setOnMouseEntered(e -> {
            startButton.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, new CornerRadii(8), null)));
        });
        startButton.setOnMouseExited(e -> {
            startButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#8F6F5C"), new CornerRadii(8), null)));
        });
        startButton.setOnAction(e -> preStory.preStory());

        Button howToPlayButton = new Button("How To Play");
        howToPlayButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 18));
        howToPlayButton.setTextFill(Color.WHITE);
        howToPlayButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#8F6F5C"), new CornerRadii(8), null)));
        howToPlayButton.setPrefWidth(180);
        howToPlayButton.setPrefHeight(50);
        howToPlayButton.setOnMouseEntered(e -> {
            howToPlayButton.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, new CornerRadii(5), null)));
        });
        howToPlayButton.setOnMouseExited(e -> {
            howToPlayButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#8F6F5C"), new CornerRadii(5), null)));
        });
        howToPlayButton.setOnAction(e -> {
            creditMenu.setVisible(false);
            howToPlayMenu.setVisible(true);
        });



        Button creditButton = new Button("Credit");
        creditButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 18));
        creditButton.setTextFill(Color.WHITE);
        creditButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#8F6F5C"), new CornerRadii(8), null)));
        creditButton.setPrefWidth(180);
        creditButton.setPrefHeight(50);
        creditButton.setOnMouseEntered(e -> {
            creditButton.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, new CornerRadii(8), null)));
        });
        creditButton.setOnMouseExited(e -> {
            creditButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#8F6F5C"), new CornerRadii(8), null)));
        });
        creditButton.setOnAction(e -> {
            creditMenu.setVisible(true);
            howToPlayMenu.setVisible(false);
        });

        Button exitButton = new Button("Exit");
        exitButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 18));
        exitButton.setTextFill(Color.WHITE);
        exitButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#8F6F5C"), new CornerRadii(8), null)));
        exitButton.setPrefWidth(180);
        exitButton.setPrefHeight(50);
        exitButton.setOnMouseEntered(e -> {
            exitButton.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, new CornerRadii(8), null)));
        });
        exitButton.setOnMouseExited(e -> {
            exitButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#8F6F5C"), new CornerRadii(8), null)));
        });
        exitButton.setOnAction(e -> stage.close());

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
        setBackground(background);

//        setGridLinesVisible(true);

        // Set fixed sizes for each column and row
        for (int i = 0; i < Config.GAMEFRAMEWIDTH / Config.WIDTHPERROW; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints(Config.WIDTHPERROW);
            getColumnConstraints().add(columnConstraints);
        }

        for (int i = 0; i < Config.GAMEFRAMEHEIGHT / Config.HEIGHTPERROW; i++) {
            RowConstraints rowConstraints = new RowConstraints(Config.HEIGHTPERROW);
            getRowConstraints().add(rowConstraints);
        }

        // Create layout and add the start button
        VBox menuButton = new VBox(startButton,howToPlayButton,creditButton,exitButton);
        menuButton.setSpacing(15);
        menuButton.setAlignment(Pos.CENTER);

        VBox titleAndMenu = new VBox(titleText,menuButton);
        titleAndMenu.setSpacing(40);
        titleAndMenu.setAlignment(Pos.CENTER);

        StackPane creditHowtoPlay = new StackPane(creditMenu,howToPlayMenu);

        // Creating an ImageView for the sunny weather icon
        ImageView sunnyImage = new ImageView(ClassLoader.getSystemResource("MenuPage/Sunny_Icon.png").toString());
        Button sunnyButton = new Button();
        sunnyImage.setFitWidth(55);
        sunnyImage.setFitHeight(55);

        // Creating a Button to display the sunny weather icon
        sunnyButton.setGraphic(sunnyImage);
        sunnyButton.setShape(new Circle(30));
        sunnyButton.setMinSize(60,60);
        sunnyButton.setMaxSize(60,60);
        sunnyButton.setStyle("-fx-background-color: transparent;");

        // Creating an ImageView for the snowy weather icon
        ImageView snowyImage = new ImageView(ClassLoader.getSystemResource("MenuPage/Snowy_Icon.png").toString());
        snowyImage.setFitWidth(60);
        snowyImage.setFitHeight(60);

        // Creating a Button to display the snowy weather icon
        Button snowyButton = new Button();
        snowyButton.setGraphic(snowyImage);
        snowyButton.setShape(new Circle(30));
        snowyButton.setMinSize(60, 60);
        snowyButton.setMaxSize(60, 60);
        snowyButton.setStyle("-fx-background-color: transparent;");

        // Creating an ImageView for the rainy weather icon
        ImageView rainyImage = new ImageView(ClassLoader.getSystemResource("MenuPage/Rainy_Icon.png").toString());
        rainyImage.setFitWidth(60);
        rainyImage.setFitHeight(60);

        // Creating a Button to display the rainy weather icon
        Button rainyButton = new Button();
        rainyButton.setGraphic(rainyImage);
        rainyButton.setShape(new Circle(30));
        rainyButton.setMinSize(60, 60);
        rainyButton.setMaxSize(60, 60);
        rainyButton.setStyle("-fx-background-color: transparent;");

        sunnyButton.setOnAction(event -> handleSunnyButton());
        snowyButton.setOnAction(event -> handleSnowyButton());
        rainyButton.setOnAction(event -> handleRainyButton());

        HBox weatherButton = new HBox(sunnyButton,snowyButton,rainyButton);
        weatherButton.setSpacing(5);

        this.add(titleAndMenu,35,8);
        this.add(creditHowtoPlay,1,20,53,10);
        this.add(weatherButton,44,25);
    }

    private void handleSunnyButton() {
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
        setBackground(background);
    }

    private void handleSnowyButton() {
        Image image = new Image(ClassLoader.getSystemResource("MenuPage/Snowy_MenuPage.gif").toString());
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
        setBackground(background);
    }

    private void handleRainyButton() {
        Image image = new Image(ClassLoader.getSystemResource("MenuPage/Rainy_MenuPage.gif").toString());
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
        setBackground(background);
    }
}
