package ac_layout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

class ag_tile_pane {

    public static class TilePaneExample extends Application {

        @Override
        public void start(Stage primaryStage) {
            TilePane root = new TilePane();
            root.setHgap(10);
            root.setVgap(5);
            root.setPadding(new Insets(0, 10, 0, 10));
            root.setPrefColumns(2); // number of columns requested

            for (int idx = 0; idx < 10; idx++) {
                root.getChildren().add(new Button("button " + idx));
            }

            Scene mainScene = new Scene(root);
            primaryStage.setScene(mainScene);
            primaryStage.show();

        }


    }
}
