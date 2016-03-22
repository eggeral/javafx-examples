package ac_layout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

class af_flow_pane {

    public static class FlowPaneExample extends Application {

        @Override
        public void start(Stage primaryStage) {
            FlowPane root = new FlowPane();
            root.setHgap(10);
            root.setVgap(5);
            root.setPadding(new Insets(0, 10, 0, 10));

            for (int idx = 0; idx < 10; idx++) {
                root.getChildren().add(new Button("button " + idx));
            }

            Scene mainScene = new Scene(root);
            primaryStage.setScene(mainScene);
            primaryStage.show();

        }


    }
}
