package aa_basics;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.Instant;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import static java.lang.StrictMath.random;

class al_animations {
    public static class Animations extends Application {

        @Override
        public void start(Stage primaryStage) {

            Circle redBall = new Circle(20, Color.RED);

            Group root = new Group();
            root.getChildren().add(redBall);

            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setFromX(0);
            translateTransition.setFromY(0);
            translateTransition.setToX(200);
            translateTransition.setToY(200);
            translateTransition.setDuration(Duration.seconds(5));
            translateTransition.setNode(redBall);

            translateTransition.play(); // note that the animation starts and ends slower than in the middle

            Scene mainScene = new Scene(root);
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);
            primaryStage.setScene(mainScene);
            primaryStage.show();

            // -- step 1 key frames
            Circle greenBall = new Circle(20, Color.GREEN);
            root.getChildren().add(greenBall);

            Timeline timeline = new Timeline();

            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, // set start position at 0
                            new KeyValue(greenBall.translateXProperty(), random() * 800),
                            new KeyValue(greenBall.translateYProperty(), random() * 600)),
                    new KeyFrame(new Duration(2000),
                            new KeyValue(greenBall.translateXProperty(), random() * 800),
                            new KeyValue(greenBall.translateYProperty(), random() * 600)),
                    new KeyFrame(new Duration(4000),
                            new KeyValue(greenBall.translateXProperty(), random() * 800),
                            new KeyValue(greenBall.translateYProperty(), random() * 600)));
            timeline.play();

        }


    }
}
