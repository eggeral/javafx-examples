package aa_basics;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import javax.swing.*;

class aq_javafx_in_swing {
    public static class JavaFxInSwing {

        public static void main(String[] args) {
            SwingUtilities.invokeLater(JavaFxInSwing::createUi);
        }

        private static void createUi() {
            JFrame frame = new JFrame("JavaFX in Swing");
            JFXPanel fxPanel = new JFXPanel();
            frame.add(fxPanel);
            frame.setSize(800, 600);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Platform.runLater(() -> fxPanel.setScene(createScene()));
        }

        private static Scene createScene() {
            BorderPane root = new BorderPane();

            Label text = new Label("Click the button!");
            root.setTop(text);

            Button button = new Button("Hello Swing");
            button.setOnAction(event -> text.setText("Button clicked!"));
            root.setCenter(button);

            return new Scene(root);
        }


    }
}
