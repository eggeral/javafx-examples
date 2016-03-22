package aa_basics;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

class ao_css {
    public static class Css extends Application {

        @Override
        public void start(Stage primaryStage) {

            Group root = new Group();

            Rectangle rectangle = new Rectangle(150,100, Color.GREEN);
            Button button1 = new Button("I am a button");
            Button button2 = new Button("I am a button too");
            root.getChildren().addAll(rectangle, button1, button2);

            button1.setTranslateX(50);
            button1.setTranslateY(50);

            button2.setTranslateX(250);
            button2.setTranslateY(50);

            Scene mainScene = new Scene(root);
            mainScene.getStylesheets().add("ao_css.css");

            primaryStage.setWidth(800);
            primaryStage.setHeight(600);
            primaryStage.setScene(mainScene);
            primaryStage.show();

            // -- step 1 style rectangle
            rectangle.setId("rectangle");
            rectangle.getStyleClass().add("rect");

            // -- step 2 style button
            button1.getStyleClass().add("button-win7");
            button2.getStyleClass().add("record-sales");

            // -- step3 use style property
            rectangle.setStyle("-fx-fill: red");
        }


    }
}
