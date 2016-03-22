package ad_controls;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

class az_java_packager {

    public static class BorderPaneExample extends Application {

        @Override
        public void start(Stage primaryStage) {
            BorderPane root = new BorderPane();
            root.setTop(new Label("TOP"));
            root.setLeft(new Label("LEFT"));
            root.setRight(new Label("RIGHT"));
            root.setCenter(new Label("CENTER"));

            Label bottom = new Label("BOTTOM");
            root.setBottom(bottom);

            Scene mainScene = new Scene(root);
            primaryStage.setScene(mainScene);
            primaryStage.show();

            // -- step 1 set alignment
            BorderPane.setAlignment(bottom, Pos.CENTER);
            BorderPane.setMargin(bottom, new Insets(12,12,12,12));


        }


    }
}
