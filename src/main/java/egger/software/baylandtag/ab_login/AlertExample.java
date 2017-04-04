package egger.software.baylandtag.ab_login;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AlertExample extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	
    @Override
    public void start(Stage primaryStage) {
        HBox root = new HBox();
        root.setSpacing(10);

        Button alertButton = new Button("Alert");
        alertButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Alert!!");
            alert.setTitle("Title");
            alert.setHeaderText("Header");
            alert.show();
        });

        root.getChildren().add(alertButton);

        Scene mainScene = new Scene(root);
        primaryStage.setWidth(800);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


}
