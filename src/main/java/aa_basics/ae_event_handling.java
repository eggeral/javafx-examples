package aa_basics;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

class ae_event_handling {

    public static class EventHandling extends Application {

        @Override
        public void start(Stage primaryStage) {
            BorderPane root = new BorderPane();
            Button button = new Button("Hello world");
            root.setCenter(button);

            // Fires on key (space), touch, mouse
            button.setOnAction((event) -> System.out.println("Button action"));

            button.setOnMouseClicked((event) -> System.out.println("Mouse clicked"));
            button.setOnKeyTyped((event) -> System.out.println("Key typed"));

            button.setOnZoom((event) -> {
                        System.out.println("Zoom");
                        button.setScaleX(button.getScaleX() * event.getZoomFactor());
                        button.setScaleY(button.getScaleY() * event.getZoomFactor());
                    }
            );

            Scene mainScene = new Scene(root);
            primaryStage.setScene(mainScene);
            primaryStage.show();

        }

    }
}
