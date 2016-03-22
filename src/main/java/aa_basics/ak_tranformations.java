package aa_basics;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

class ak_tranformations {
    public static class Threads extends Application {

        @Override
        public void start(Stage primaryStage) {

            Group root = new Group();

            Button button = new Button("I am a button!");
            root.getChildren().add(button);

            Scene mainScene = new Scene(root);
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);
            primaryStage.setScene(mainScene);
            primaryStage.show();

            // -- step 1 move the button around
            button.setTranslateX(250);
            button.setTranslateY(300);

            // -- step 2 zoom the button
            button.setScaleX(5.0);
            button.setScaleY(3.0);

            // -- step 3 rotate the button
            button.setRotate(45.0);

        }
    }
}
