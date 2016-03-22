package aa_basics;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.time.Instant;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

class aj_threads {
    public static class Threads extends Application {

        @Override
        public void start(Stage primaryStage) {

            BorderPane root = new BorderPane();
            Label result = new Label();
            HBox centerBar = new HBox();
            ProgressIndicator someThingThatMoves = new ProgressIndicator();
            someThingThatMoves.setPrefWidth(400);

            Button thisBlocksTheUi = new Button("Block");
            thisBlocksTheUi.setOnAction(e -> result.setText("blocking: " + longLastingTask(5_000)));

            centerBar.getChildren().addAll(someThingThatMoves, thisBlocksTheUi);
            root.setCenter(centerBar);
            root.setBottom(result);

            Scene mainScene = new Scene(root);
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);
            primaryStage.setScene(mainScene);
            primaryStage.show();

            // -- step 1 do long lasting in a thread in order not to block the ui
            Executor executor = new ScheduledThreadPoolExecutor(10);

            Button doItInATask = new Button("No Block");

            doItInATask.setOnAction(e -> {
                Task<String> task = new Task<String>() {

                    @Override
                    protected String call() throws Exception {
                        doItInATask.setDisable(true);
                        String result = longLastingTask(10_000);
                        doItInATask.setDisable(false);
                        return result;
                    }

                    @Override
                    protected void succeeded() {
                        result.setText("non blocking: " + getValue());
                    }
                };
                executor.execute(task);
            });
            centerBar.getChildren().add(doItInATask);

            // -- step 2 update the ui during the task
            Button updateTheUi = new Button("Animate");

            updateTheUi.setOnAction(e -> {
                Task<Void> task = new Task<Void>() {

                    @Override
                    protected Void call() throws Exception {
                        updateTheUi.setDisable(true); // this can be done outside of the UI thread
                        Circle circle = new Circle(10, Color.CADETBLUE);
                        // centerBar.getChildren().add(circle) -> java.lang.IllegalStateException: Not on FX application thread;
                        Platform.runLater(() -> centerBar.getChildren().add(circle));
                        for (int i = 0; i < 100; i++) {
                            circle.setTranslateX(circle.getTranslateX() + 1);
                            Thread.sleep(100);
                        }
                        // centerBar.getChildren().remove(circle); -> java.lang.IllegalStateException: Not on FX application thread;
                        Platform.runLater(() -> centerBar.getChildren().remove(circle));
                        updateTheUi.setDisable(false);
                        return null;
                    }

                    @Override
                    protected void failed() {
                        System.err.println(getException());
                    }
                };
                executor.execute(task);
            });
            centerBar.getChildren().addAll(updateTheUi);


        }

        private String longLastingTask(int millis) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "done at: " + Instant.now();
        }

    }
}
