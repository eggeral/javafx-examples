package ac_layout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

class ad_stack_pane {

    public static class StackPaneExample extends Application {

        @Override
        public void start(Stage primaryStage) {
            StackPane root = new StackPane();

            Rectangle rectangle = new Rectangle(200,100, Color.GREEN);
            Button button = new Button("click");

            root.getChildren().addAll(rectangle, button);
            Scene mainScene = new Scene(root);
            primaryStage.setScene(mainScene);
            primaryStage.show();
            
        }


    }
}
