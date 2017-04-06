package baylandtag.al_edit_member;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class LoginController {

	private TextField databaseUrlTextField;
	private TextField usernameTextField;
	private TextField passwordTextField;
	private ToggleButton connectDisconnectButton;

	private Database database;

	public LoginController(LoginUi ui) {

		databaseUrlTextField = ui.getDatabaseUrlTextField();
		usernameTextField = ui.getUsernameTextField();
		passwordTextField = ui.getPasswordTextField();

		connectDisconnectButton = ui.getConnectDisconnectButton();

	}

	public void initialize() {
		
		connectDisconnectButton.selectedProperty().addListener((obeservable, oldValue, newValue) ->  {
			if (newValue == true) { 
				connectToDatabase();
				connectDisconnectButton.setText("Disonnect");
			} else {
				disconnectFromDatabase();				
				connectDisconnectButton.setText("Connect");
			}
		});

	}

	public void connectToDatabase() {
		
		try {
			database.connect();
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

		databaseUrlTextField.textProperty().bindBidirectional(database.urlProperty());
		usernameTextField.textProperty().bindBidirectional(database.userProperty());
		passwordTextField.textProperty().bindBidirectional(database.passwordProperty());

	}

}
