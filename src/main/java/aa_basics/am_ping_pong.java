package aa_basics;

import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import static java.lang.StrictMath.random;

class am_ping_pong {
    public static class PingPong extends Application {

        @Override
        public void start(Stage primaryStage) {

            DoubleProperty directionX = new SimpleDoubleProperty(1);
            DoubleProperty directionY = new SimpleDoubleProperty(1);

            Circle redBall = new Circle(20, Color.RED);
            redBall.setLayoutX(100);
            redBall.setLayoutY(200);

            Rectangle topWall = new Rectangle(8, 8, 782, 2);
            topWall.setFill(Color.GREEN);

            Rectangle rightWall = new Rectangle(790, 8, 2, 582);
            rightWall.setFill(Color.GREEN);

            Rectangle leftWall = new Rectangle(8, 8, 2, 582);
            leftWall.setFill(Color.GREEN);

            Rectangle bottomWall = new Rectangle(8, 590, 784, 2);
            bottomWall.setFill(Color.GREEN);

            Group root = new Group();
            root.getChildren().addAll(topWall, rightWall, leftWall, bottomWall, redBall);

            // -- exercise start
            Rectangle paddle = new Rectangle(100, 20, Color.BLUE);
            // -- exercise end

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().addAll(
                    //60 frame per second -> every 16,7 milliseconds a new frame is rendered
                    new KeyFrame(new Duration(16), event -> {
                        if (redBall.getBoundsInParent().intersects(rightWall.getBoundsInParent()))
                            directionX.set(-1);

                        if (redBall.getBoundsInParent().intersects(bottomWall.getBoundsInParent()))
                            directionY.set(-1);

                        if (redBall.getBoundsInParent().intersects(topWall.getBoundsInParent()))
                            directionY.set(1);

                        if (redBall.getBoundsInParent().intersects(leftWall.getBoundsInParent()))
                            directionX.set(1);

                        // -- exercise start
                        if (redBall.getBoundsInParent().intersects(paddle.getBoundsInParent()))
                            directionY.set(-1);
                        // -- exercise end

                        redBall.setLayoutX(redBall.getLayoutX() + directionX.get());
                        redBall.setLayoutY(redBall.getLayoutY() + directionY.get());
                    }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();

            Scene mainScene = new Scene(root);
            primaryStage.setWidth(800);
            primaryStage.setHeight(650);
            primaryStage.setScene(mainScene);
            primaryStage.show();

            // -- Exercise add paddle
            root.getChildren().add(paddle);

            DoubleProperty paddlePosition = new SimpleDoubleProperty(400);
            paddle.relocate(paddlePosition.doubleValue(), 560);

            paddle.setOnMouseMoved(event -> paddle.setX(event.getX() - 50));


        }
    }
}
