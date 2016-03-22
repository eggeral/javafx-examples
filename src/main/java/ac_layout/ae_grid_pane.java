package ac_layout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

class ae_grid_pane {

    public static class GridPaneExample extends Application {

        @Override
        public void start(Stage primaryStage) {
            GridPane root = new GridPane();
            root.setHgap(10);
            root.setVgap(5);
            root.setPadding(new Insets(0, 10, 0, 10));

            TextField flightName = new TextField();
            Label flightNameLabel = new Label("Flight Name:");
            root.add(flightNameLabel, 0, 0);
            root.add(flightName, 1, 0);

            TextField from = new TextField();
            Label fromLabel = new Label("From:");
            root.add(fromLabel, 0, 1);
            root.add(from, 1, 1);

            Scene mainScene = new Scene(root);
            primaryStage.setScene(mainScene);
            primaryStage.show();

        }


    }
}
