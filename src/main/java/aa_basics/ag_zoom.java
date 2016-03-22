package aa_basics;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

class ag_zoom {

    public static class Zoom extends Application {

        @Override
        public void start(Stage primaryStage) {

            BorderPane root = new BorderPane();

            TextField zoomFactor = new TextField("1");
            Slider zoomSlider = new Slider(0.5, 2, 1);
            zoomSlider.setOrientation(Orientation.VERTICAL);

            Rectangle rectangle = new Rectangle(200, 100, Color.RED);
            root.setCenter(rectangle);
            root.setTop(zoomFactor);
            root.setLeft(zoomSlider);

            Scene mainScene = new Scene(root);
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);
            primaryStage.setScene(mainScene);
            primaryStage.show();

            // step 2
            rectangle.scaleXProperty().bind(zoomSlider.valueProperty());

            // step 3
            // show zoom factor in text field
            //zoomFactor.textProperty().bind(rectangle.scaleXProperty().asString());

            // step 4
            // text field updates rectangle scale
            StringConverter<Number> converter = new NumberStringConverter();
            Bindings.bindBidirectional(zoomFactor.textProperty(), zoomSlider.valueProperty(), converter);

            // step 5
            // calculate the area of the rectangle
            SimpleDoubleProperty area = new SimpleDoubleProperty();
            area.bind(rectangle.widthProperty());

            Label areaLabel = new Label();

            areaLabel.textProperty().bind(Bindings.multiply(
                    Bindings.multiply(rectangle.widthProperty(), rectangle.scaleXProperty()),
                    Bindings.multiply(rectangle.heightProperty(), rectangle.scaleYProperty())
            ).asString());

            root.setBottom(areaLabel);
        }

    }
}
