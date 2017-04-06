package baylandtag.aa_login_form;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		GridPane loginPane = new GridPane();
		Label connectedLabel = new Label("not connected");

		loginPane.add(connectedLabel, 0, 0);

		TextField databaseUrlTextField = new TextField();
		loginPane.add(new Label("Database URL:"), 0, 1);
		loginPane.add(databaseUrlTextField, 1, 1);

		TextField userNameTextField = new TextField();
		loginPane.add(new Label("User name:"), 0, 2);
		loginPane.add(userNameTextField, 1, 2);

		TextField passwordTextField = new TextField();
		loginPane.add(new Label("Password:"), 0, 3);
		loginPane.add(passwordTextField, 1, 3);

		Button connectButton = new Button("Connect");
		loginPane.add(connectButton, 0, 4);
		
		Button disconnectButton = new Button("Disconnect");
		loginPane.add(disconnectButton, 1, 4);

		Scene mainScene = new Scene(loginPane);
		primaryStage.setScene(mainScene);

		primaryStage.show();

	}
}
