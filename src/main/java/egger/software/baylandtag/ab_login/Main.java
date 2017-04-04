package egger.software.baylandtag.ab_login;

import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.application.Application;
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

//1. create Database class
//2. create connect method - load driver if not already loaded - wrap exceptions in DatabaseException
//3. connect to the database //"jdbc:mysql://localhost:3306/baylandtag" user1:user1
//4. disconnect form the database
//5. add handler to connect button
//6. add handler to disconnect button
//7. Alert example
//8. Show exception in using Alert

public class Main extends Application {

	private Database database = new Database();

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

		connectButton.setOnMouseClicked((event) -> {
			try {
				database.disconnect();

				database.setUrl(databaseUrlTextField.getText());
				database.setUser(userNameTextField.getText());
				database.setPassword(passwordTextField.getText());
				database.connect();

				if (database.isConnected()) {
					connectedLabel.setText("connected");
				} else {
					connectedLabel.setText("not connected");
				}
			} catch (Exception ex) {
				showException(ex);
			}
		});

		disconnectButton.setOnMouseClicked((event) -> {
			try {
				database.disconnect();
				connectedLabel.setText("not connected");
			} catch (Exception ex) {
				showException(ex);
			}
		});

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

}
