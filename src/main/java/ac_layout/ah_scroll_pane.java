package ac_layout;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class ah_scroll_pane {

    public static class AnchorPaneExample extends Application {

        @Override
        public void start(Stage primaryStage) {
            VBox vbox = new VBox();
            for (int idx = 0; idx < 10; idx++) {
                vbox.getChildren().add(new Label("label " + idx));
            }
            ScrollPane root = new ScrollPane(vbox);

            Scene mainScene = new Scene(root);
            primaryStage.setScene(mainScene);
            primaryStage.show();

        }


    }
}
