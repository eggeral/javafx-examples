package egger.software.baylandtag.ag_dummy_person_table;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginUi {
	
	private Label connectedLabel = new Label();
	private TextField databaseUrlTextField = new TextField();
	private TextField usernameTextField = new TextField();
	private TextField passwordTextField = new TextField();
	private Button connectButton = new Button("Connect");
	private Button disconnectButton = new Button("Disconnect");
	
	public Parent create() {
		GridPane loginPane = new GridPane();
		loginPane.add(connectedLabel, 0, 0);

		loginPane.add(new Label("Database URL:"), 0, 1);
		loginPane.add(databaseUrlTextField, 1, 1);

		loginPane.add(new Label("User name:"), 0, 2);
		loginPane.add(usernameTextField, 1, 2);

		loginPane.add(new Label("Password:"), 0, 3);
		loginPane.add(passwordTextField, 1, 3);

		loginPane.add(connectButton, 0, 4);
		loginPane.add(disconnectButton, 1, 4);

		return loginPane;
	}

	public Label getConnectedLabel() {
		return connectedLabel;
	}

	public TextField getDatabaseUrlTextField() {
		return databaseUrlTextField;
	}

	public TextField getUsernameTextField() {
		return usernameTextField;
	}

	public TextField getPasswordTextField() {
		return passwordTextField;
	}

	public Button getConnectButton() {
		return connectButton;
	}

	public Button getDisconnectButton() {
		return disconnectButton;
	}

}
