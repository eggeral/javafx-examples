package egger.software.baylandtag.af_split_into_ui_and_controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private Database database = new Database();

	public static void main(String[] args) {
		launch(args);
	}

	// Refactor: Move all UI stuff to LoginUi.java and everything else to LoginController.java
	// LoginUi returns a Node representing the UI when create() is called
	// LoginUi has public getters for UI Elements needed by LoginController.
	// LoginController gets LoginUi in it's constructor 
	// LoginController sets up binding and event handlers in initialize
	// LoginController gets Database via setDatabase(...)
	// We try to keep close to the FXML - Controller pattern here!
	@Override
	public void start(Stage primaryStage) {

		Utils.loadDatabaseProperties(database);
		
		LoginUi loginUi = new LoginUi();
		LoginController loginController = new LoginController(loginUi);
		loginController.initialize();
		loginController.setDatabase(database);

		Scene mainScene = new Scene(loginUi.create());
		primaryStage.setScene(mainScene);
		primaryStage.show();

	}


}
