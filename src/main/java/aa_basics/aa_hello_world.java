package aa_basics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

class aa_hello_world {
    public static class HelloWorld extends Application {

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            // root is the content we want to display.
            BorderPane root = new BorderPane();
            root.setCenter(new Label("Hello World"));

            // mainScene is the main window.
            Scene mainScene = new Scene(root);
            primaryStage.setScene(mainScene);
            primaryStage.show();
        }
    }
}
