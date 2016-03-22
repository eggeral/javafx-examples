package aa_basics;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

class ab_life_cycle {
    public static class LifeCycle extends Application {
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void init() {
            // Called before the Application is constructed. Does not use JavaFx application thread.
            // Do not create Scenes etc here.
            System.out.println("init: " + Thread.currentThread().getName());
        }


        @Override
        public void start(Stage primaryStage) {
            System.out.println("start: " + Thread.currentThread().getName());
            Platform.exit();
            //System.exit(1);
        }

        @Override
        public void stop() {
            // Any clean up can be done here.
            // Do not use System.exit(...) because stop() is not called then.
            // Use Platform.exit() in order to exit cleanly.
            System.out.println("stop: " + Thread.currentThread().getName());
        }

    }
}
