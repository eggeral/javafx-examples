package ac_layout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class ac_vbox {

    public static class VBoxExample extends Application {

        @Override
        public void start(Stage primaryStage) {
            VBox root = new VBox();

            Label a = new Label("A");
            Label b = new Label("B");
            Label c = new Label("C");

            HBox.setMargin(a, new Insets(5,5,5,5));
            HBox.setMargin(b, new Insets(5,5,5,5));
            HBox.setMargin(c, new Insets(5,5,5,5));

            root.getChildren().addAll(a, b, c);
            Scene mainScene = new Scene(root);
            primaryStage.setScene(mainScene);
            primaryStage.show();
            
        }


    }
}
