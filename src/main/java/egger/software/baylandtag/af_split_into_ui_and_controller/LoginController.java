package egger.software.baylandtag.af_split_into_ui_and_controller;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

	private TextField databaseUrlTextField;
	private Label connectedLabel;
	private TextField usernameTextField;
	private TextField passwordTextField;
	private Button connectButton;
	private Button disconnectButton;

	private Database database;

	public LoginController(LoginUi ui) {

		connectedLabel = ui.getConnectedLabel();
		databaseUrlTextField = ui.getDatabaseUrlTextField();
		usernameTextField = ui.getUsernameTextField();
		passwordTextField = ui.getPasswordTextField();

		connectButton = ui.getConnectButton();
		disconnectButton = ui.getDisconnectButton();

	}

	public void initialize() {
		
		// we could do this in initDatabase. Just to show the two approaches.
		connectButton.setOnMouseClicked((event) -> {
			connectToDatabase();
		});

		disconnectButton.setOnMouseClicked((event) -> {
			disconnectFromDatabase();
		});

	}

	public void connectToDatabase() {
		
		try {
			database.connect();
			if (database.isConnected())
				Utils.saveDatabaseProperties(database);
		} catch (Exception ex) {
			Utils.showException(ex);
		}

	}

	public void disconnectFromDatabase() {
		
		try {
			database.disconnect();
		} catch (Exception ex) {
			Utils.showException(ex);
		}
		
	}

	public void setDatabase(Database database) {
		
		if (this.database != null)
			throw new IllegalStateException("Database already set for LoginController.");
		
		this.database = database;

		connectedLabel.textProperty()
				.bind(Bindings.when(database.connectedProperty()).then("Connected").otherwise("Not Connected"));
		databaseUrlTextField.textProperty().bindBidirectional(database.urlProperty());
		usernameTextField.textProperty().bindBidirectional(database.userProperty());
		passwordTextField.textProperty().bindBidirectional(database.passwordProperty());

	}

}
