package ac_layout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

class ah_anchor_pane {

    public static class AnchorPaneExample extends Application {

        @Override
        public void start(Stage primaryStage) {
            AnchorPane root = new AnchorPane();

            Button save = new Button("Save");
            Button cancel = new Button("Cancel");

            root.getChildren().addAll(save,cancel);
            AnchorPane.setBottomAnchor(save, 8.0);
            AnchorPane.setRightAnchor(save, 5.0);
            AnchorPane.setTopAnchor(cancel, 10.0);

            Scene mainScene = new Scene(root);
            primaryStage.setScene(mainScene);
            primaryStage.show();

        }


    }
}
