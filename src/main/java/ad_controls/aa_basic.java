package ad_controls;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

class aa_basic {

    public static class BasicControls extends Application {

        @Override
        public void start(Stage primaryStage) {
            VBox root = new VBox();
            root.setSpacing(10);

            root.getChildren().add(new Button("Button"));
            root.getChildren().add(new ToggleButton("Toggle Button"));
            root.getChildren().add(new CheckBox("Check Box"));
            root.getChildren().add(new ChoiceBox<>(FXCollections.observableArrayList("a", "b", "c")));
            root.getChildren().add(new ComboBox<>(FXCollections.observableArrayList("a", "b", "c")));
            root.getChildren().add(new TextField("text field"));
            root.getChildren().add(new TextArea("text area"));
            root.getChildren().add(new Label("label"));

            TextField numbersOnly = new TextField("123");
            numbersOnly.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    numbersOnly.setText(oldValue);
                }
            });
            root.getChildren().add(numbersOnly);

            root.getChildren().add(new ProgressIndicator());
            root.getChildren().add(new ProgressBar());

            ToggleGroup toggleGroup = new ToggleGroup();
            RadioButton radioButton1 = new RadioButton("a");
            RadioButton radioButton2 = new RadioButton("b");
            RadioButton radioButton3 = new RadioButton("c");
            radioButton1.setToggleGroup(toggleGroup);
            radioButton2.setToggleGroup(toggleGroup);
            radioButton3.setToggleGroup(toggleGroup);

            root.getChildren().add(new Slider());
            root.getChildren().add(new DatePicker());
            root.getChildren().add(new ColorPicker());

            root.getChildren().add(new HTMLEditor());

            WebView webView = new WebView();
            webView.getEngine().load("http://www.williammalone.com/articles/create-html5-canvas-javascript-drawing-app");
            root.getChildren().add(webView);

            Scene mainScene = new Scene(new ScrollPane(root));
            primaryStage.setScene(mainScene);
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);
            primaryStage.show();

        }
    }
}
