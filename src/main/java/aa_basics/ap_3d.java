package aa_basics;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.Light;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;


class ap_3d {
    public static class ThreeD extends Application {

        @Override
        public void start(Stage primaryStage) {

            Group group3d = new Group();

            Box box = new Box(50, 50, 50);
            box.relocate(150,150);
            group3d.getChildren().add(box);

            // SubScene is not necessary but I want to place some controls in the scene
            SubScene subScene = new SubScene(group3d, 500,500);
            subScene.setFill(Color.CADETBLUE);  // because by default the box is light gray
            BorderPane root = new BorderPane();
            root.setCenter(subScene);

            Scene mainScene = new Scene(root);
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);
            primaryStage.setScene(mainScene);
            primaryStage.show();

            // -- step 1 set material
            box.setMaterial(new PhongMaterial(Color.RED));

            // -- step 2 set camera
            PerspectiveCamera camera = new PerspectiveCamera(true);
            camera.setFarClip(300); // 100 is default
            camera.getTransforms().addAll (
                    new Rotate(-20, Rotate.Y_AXIS),
                    new Rotate(-20, Rotate.X_AXIS),
                    new Translate(150, 190, -200));
            subScene.setCamera(camera);

            // -- step 3 rotate the box
            Rotate xRotate = new Rotate(0,Rotate.X_AXIS);
            box.getTransforms().add(xRotate);
            Slider xRotationSlider = new Slider(0, 360, 0);
            xRotationSlider.setPadding(new Insets(20));
            xRotate.angleProperty().bind(xRotationSlider.valueProperty());
            root.setBottom(xRotationSlider);

            Rotate yRotate = new Rotate(0,Rotate.Y_AXIS);
            box.getTransforms().add(yRotate);
            Slider yRotationSlider = new Slider(0, 360, 0);
            yRotationSlider.setOrientation(Orientation.VERTICAL);
            yRotationSlider.setPadding(new Insets(20));
            yRotate.angleProperty().bind(yRotationSlider.valueProperty());
            root.setLeft(yRotationSlider);

            // -- step 4 a button in 3d
            Button button = new Button("A 3D Button");
            button.relocate(120,150);
            button.setTranslateZ(10);
            button.setScaleX(0.5);
            button.setScaleY(0.5);
            group3d.getChildren().add(button);

            Label label = new Label("Click the button");
            root.setTop(label);
            button.setOnAction(e -> label.setText("Button pressed!"));

            // -- step 5 rotate the button
            button.getTransforms().addAll(xRotate, yRotate);
        }

    }
}
