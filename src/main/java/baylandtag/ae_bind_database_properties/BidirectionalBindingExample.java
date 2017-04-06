package baylandtag.ae_bind_database_properties;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BidirectionalBindingExample extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		// 1. bidirectional binding

		GridPane testPane = new GridPane();

		TextField boundTextField1 = new TextField();
		TextField boundTextField2 = new TextField();
		testPane.add(boundTextField1, 0, 0);
		testPane.add(boundTextField2, 0, 1);

		boundTextField1.textProperty().bindBidirectional(boundTextField2.textProperty());

		Scene mainScene = new Scene(testPane);
		primaryStage.setScene(mainScene);

		primaryStage.show();

	}

}
