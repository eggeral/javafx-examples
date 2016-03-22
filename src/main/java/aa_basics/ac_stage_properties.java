package aa_basics;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

class ac_stage_properties {
    public static class StageProperties extends Application {

        @Override
        public void start(Stage primaryStage) {
            BorderPane root = new BorderPane();
            root.setCenter(new Label("Hello World"));

            Scene mainScene = new Scene(root);
            primaryStage.setScene(mainScene);
            primaryStage.setFullScreen(false);
            //primaryStage.setFullScreen(true);
            primaryStage.setTitle("Hello World");
            primaryStage.setIconified(false);
            //primaryStage.setIconified(true);
            primaryStage.setHeight(600);
            primaryStage.setWidth(800);
            primaryStage.setMinWidth(400);
            primaryStage.setMinHeight(300);
            primaryStage.setX(200);
            primaryStage.setY(300);
            primaryStage.setResizable(true);
            //primaryStage.setResizable(false);
            primaryStage.show();
        }

    }
}
