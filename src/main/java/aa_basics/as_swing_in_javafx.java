package aa_basics;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;

class as_swing_in_javafx {
    public static class SwingInJavaFx extends Application {

        @Override
        public void start(Stage primaryStage) {
            Label javaFxLabel = new Label("Waiting for Swing button to be pressed!");
            SwingNode swingNode = new SwingNode();

            BorderPane root = new BorderPane();
            root.setCenter(swingNode);
            root.setTop(javaFxLabel);

            JButton swingButton = new JButton("Swing Button");
            swingButton.addActionListener(action -> {
                Platform.runLater(() -> javaFxLabel.setText("JavaFx label text set by JButton!"));
            });

            SwingUtilities.invokeLater(() -> swingNode.setContent(swingButton));

            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();


        }

    }
}
