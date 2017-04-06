package baylandtag.ah_fill_member_table_from_db;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PropertyListenerExample extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	int counter = 0;

	@Override
	public void start(Stage primaryStage) throws Exception {

		GridPane root = new GridPane();

		Label l1 = new Label("l1");
		Label l2 = new Label("l2");
		Button button = new Button("click");
		root.add(l1, 0, 0);
		root.add(l2, 1, 0);
		root.add(button, 0, 1);
		button.setOnAction((event) -> {
			l1.setText("l1-" + counter);
			l2.setText("l2-" + counter);
			counter++;
		});

		// use change listener if you have to work with the old/new value. The
		// value of the property is
		// calculated eager in this case which is slower compared to just get
		// notified of the change.
		l1.textProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("ChangeListener: oldValue=" + oldValue + ", newValue=" + newValue);
		});

		// use if you just need to get notified of a change. The value might not
		// have changed in this moment.It is just invalid.
		// For example if the property depends on other properties it is just
		// invalidated if one of the other properties changes.
		l2.textProperty().addListener((observable) -> {
			System.out.println("InvalidationListener: value invalidate");
			// if we need the value of the property we have to trigger the
			// calculation
			System.out.println("InvalidationListener: the new value is: " + l2.getText());
		});

		Scene mainScene = new Scene(root);
		primaryStage.setScene(mainScene);
		primaryStage.show();

	}

}
