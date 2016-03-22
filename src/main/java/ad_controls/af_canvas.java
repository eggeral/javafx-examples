package ad_controls;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

class af_canvas {

    public static class CanvasExample extends Application {

        @Override
        public void start(Stage primaryStage) {
            Group root = new Group();

            final Canvas canvas = new Canvas(250,250);
            GraphicsContext gc = canvas.getGraphicsContext2D();

            gc.setFill(Color.BLUE);
            gc.fillRect(75,75,100,100);

            gc.setFill(Color.GREEN);
            gc.moveTo(10,10);
            gc.lineTo(10,50);
            gc.lineTo(50,50);
            gc.stroke();

            root.getChildren().add(canvas);

            Scene mainScene = new Scene(root);
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);
            primaryStage.setScene(mainScene);
            primaryStage.show();

        }


    }
}
