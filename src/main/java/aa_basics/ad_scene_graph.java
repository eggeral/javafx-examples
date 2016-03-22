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

class ad_scene_graph {

    // A scene graph represents everything that will appear on the screen.
    public static class SceneGraph extends Application {

        @Override
        public void start(Stage primaryStage) {
            BorderPane root = new BorderPane();
            root.setCenter(new Label("Hello World"));
            root.setBottom(new HBox(new Rectangle(100,100, Color.RED), new Button("Push!")));

            Scene mainScene = new Scene(root);
            primaryStage.setScene(mainScene);
            primaryStage.show();

            printNodes(root, "");

        }

        private void printNodes(Node node, String prefix) {
            System.out.println(prefix + node.getClass().getSimpleName());
            if (node instanceof Parent) {
                Parent parent = (Parent) node;
                for (Node child : parent.getChildrenUnmodifiable()) {
                    printNodes(child, prefix + "  ");
                }
            }
        }

    }
}
