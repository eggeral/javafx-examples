package baylandtag.au_display_image;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

public class LoginUi {
	
	private TextField databaseUrlTextField = new TextField();
	private TextField usernameTextField = new TextField();
	private PasswordField passwordTextField = new PasswordField();
	private ToggleButton connectDisconnectButton = new ToggleButton("Connect");
	
	public Parent create() {
		GridPane loginPane = new GridPane();
		loginPane.setId("loginPane");
//		loginPane.setPadding(new Insets(5.0));
//		loginPane.setVgap(3.0);
//		loginPane.setHgap(5.0);
		
		loginPane.add(new Label("Database URL:"), 0, 0);
		loginPane.add(databaseUrlTextField, 1, 0);
		databaseUrlTextField.setPromptText("baylandtag db url");

		loginPane.add(new Label("User name:"), 0, 1);
		loginPane.add(usernameTextField, 1, 1);
		usernameTextField.setPromptText("Your username");

		loginPane.add(new Label("Password:"), 0, 2);
		loginPane.add(passwordTextField, 1, 2);
		passwordTextField.setPromptText("Your password");
		
		loginPane.add(connectDisconnectButton, 0, 3);

		return loginPane;
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

	public ToggleButton getConnectDisconnectButton() {
		return connectDisconnectButton;
	}

}
