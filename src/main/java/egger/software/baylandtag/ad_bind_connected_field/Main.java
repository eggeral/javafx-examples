package egger.software.baylandtag.ad_bind_connected_field;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class Main extends Application {

	private Database database = new Database();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		GridPane loginPane = new GridPane();
		Label connectedLabel = new Label();
		loginPane.add(connectedLabel, 0, 0);
		connectedLabel.textProperty()
				.bind(Bindings.when(database.connectedProperty()).then("Connected").otherwise("Not Connected"));

		TextField databaseUrlTextField = new TextField();
		loginPane.add(new Label("Database URL:"), 0, 1);
		loginPane.add(databaseUrlTextField, 1, 1);

		TextField usernameTextField = new TextField();
		loginPane.add(new Label("User name:"), 0, 2);
		loginPane.add(usernameTextField, 1, 2);

		TextField passwordTextField = new TextField();
		loginPane.add(new Label("Password:"), 0, 3);
		loginPane.add(passwordTextField, 1, 3);

		Button connectButton = new Button("Connect");
		loginPane.add(connectButton, 0, 4);

		Button disconnectButton = new Button("Disconnect");
		loginPane.add(disconnectButton, 1, 4);

		Scene mainScene = new Scene(loginPane);
		primaryStage.setScene(mainScene);

		connectButton.setOnMouseClicked((event) -> {
			try {
				database.disconnect();
				database.setUrl(databaseUrlTextField.getText());
				database.setUser(usernameTextField.getText());
				database.setPassword(passwordTextField.getText());
				database.connect();

				if (database.isConnected()) {
					saveDatabaseProperties(databaseUrlTextField.getText(), usernameTextField.getText(),
							passwordTextField.getText());
				}
			} catch (Exception ex) {
				showException(ex);
			}
		});

		disconnectButton.setOnMouseClicked((event) -> {
			try {
				database.disconnect();
			} catch (Exception ex) {
				showException(ex);
			}
		});

		Path databasePropertiesPath = databasePropertiesPath();
		if (Files.exists(databasePropertiesPath)) {

			Properties properties = new Properties();
			try (FileReader reader = new FileReader(databasePropertiesPath.toFile())) {

				properties.load(reader);
				databaseUrlTextField.setText(properties.getProperty("url"));
				usernameTextField.setText(properties.getProperty("username"));
				passwordTextField.setText(properties.getProperty("password"));

			} catch (IOException ex) {
				System.out.println("Error reading properties from: " + databasePropertiesPath());
				ex.printStackTrace();
			}

		}

		primaryStage.show();

	}

	private void showException(Exception ex) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(ex.getMessage());
		if (ex.getCause() != null)
			alert.setContentText(ex.getCause().getMessage());

		// Create expandable Exception.
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("The exception stacktrace was:");
		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		alert.getDialogPane().setExpandableContent(expContent);
		alert.showAndWait();
	}

	private void saveDatabaseProperties(String url, String username, String password) throws IOException {
		Properties properties = new Properties();
		properties.setProperty("url", url);
		properties.setProperty("username", username);
		properties.setProperty("password", password);

		try (FileWriter writer = new FileWriter(databasePropertiesPath().toFile())) {
			properties.store(writer, "Baylandtag DB properties");
		}
	}

	private Path databasePropertiesPath() {
		String userHome = System.getProperty("user.home");
		if (userHome == null)
			userHome = ".";
		return Paths.get(userHome, "baylandtag.properties");
	}

}
