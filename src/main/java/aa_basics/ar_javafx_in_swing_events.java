package aa_basics;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import javax.swing.*;
import java.awt.*;

class ar_javafx_in_swing_events {
    public static class JavaFxInSwing {

        public static void main(String[] args) {
            SwingUtilities.invokeLater(JavaFxInSwing::createUi);
        }

        private static void createUi() {
            JFrame frame = new JFrame("JavaFX in Swing plus events");
            JFXPanel fxPanel = new JFXPanel();

            JPanel borderPanel = new JPanel(new BorderLayout(3, 3));
            borderPanel.add(fxPanel, BorderLayout.CENTER);

            JLabel swingLabel = new JLabel("Swing Label");
            borderPanel.add(swingLabel, BorderLayout.NORTH);

            JButton swingButton = new JButton("Swing Button");
            borderPanel.add(swingButton, BorderLayout.SOUTH);

            frame.add(borderPanel);
            frame.setSize(800, 600);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Platform.runLater(() -> fxPanel.setScene(createScene(swingButton, swingLabel)));
        }

        private static Scene createScene(JButton swingButton, JLabel swingLabel) {
            BorderPane root = new BorderPane();

            Label javaFxText = new Label("JavaFx Label");
            root.setTop(javaFxText);

            // -- step 1 connect Swing button to JavaFx label
            // Throws exception because not an JavaFx application thread!
            // swingButton.addActionListener(action -> javaFxText.setText("JavaFx Label text set by JButton!"));
            swingButton.addActionListener(
                    action -> {
                        Platform.runLater(() -> javaFxText.setText("JavaFx label text set by JButton!"));
                    });

            // -- step 2 connect JavaFx button Swing label
            Button javaFxButton = new Button("JavaFx Button");
            root.setCenter(javaFxButton);

            // works but not recommended
            // javaFxButton.setOnAction(event -> swingLabel.setText("Button clicked!"));
            javaFxButton.setOnAction(event -> {
                SwingUtilities.invokeLater(() -> swingLabel.setText("Swing label text set by JavaFx button!"));
            });

            return new Scene(root);
        }


    }
}
