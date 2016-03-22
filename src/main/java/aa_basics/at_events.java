package aa_basics;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

class at_events {
    public static class Events extends Application {

        @Override
        public void start(Stage primaryStage) {

            Group root = new Group();

            Pane pane = new Pane();
            root.getChildren().add(pane);

            Circle circle = new Circle(50, Color.GREEN);
            circle.relocate(80, 80);
            pane.getChildren().add(circle);

            Scene mainScene = new Scene(root);
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);
            primaryStage.setScene(mainScene);
            primaryStage.show();

            // -- step 1 filters are executed during event capturing phase scenegraph from root to base
            root.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> System.out.println("root -> click filter"));

            pane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> System.out.println("pane -> click filter"));

            circle.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> System.out.println("circle -> click filter"));

            // -- step 2 handlers are executed during event bubbling phase scenegraph base to root
            root.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> System.out.println("root -> click handler"));

            pane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> System.out.println("pane -> click handler"));

            circle.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> System.out.println("circle -> click handler"));

            // -- step 3 convenience methods
            pane.setOnMouseClicked(event -> System.out.println("pane -> on mouse clicked."));

            // -- step 4 consume events
//            pane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
//                        event.consume();
//                        System.out.println("pane -> event consumed in filter. stops here!");
//                    }
//            );

            pane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                        event.consume();
                        System.out.println("pane -> event consumed in handler. stops here!");
                    }
            );

        }
    }
}
