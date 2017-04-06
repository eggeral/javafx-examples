package baylandtag.ag_dummy_person_table;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	// 1. Example of data table
	// 2. Example of tab pane
	// 3. MainUi and Controller MainUi has 2 tabs one for login one for baylandtag
	// 4. Bind the disabled property of baylandtag tab to database connected property
	// 5. Baylandtag UI
	// 6. Abgeordneter Table
	// 7. Baylandtag Controller
	private Database database = new Database();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		Utils.loadDatabaseProperties(database);
		
		LoginUi loginUi = new LoginUi();
		LoginController loginController = new LoginController(loginUi);
		loginController.initialize();
		loginController.setDatabase(database);

		BaylandtagUi baylandtagUi = new BaylandtagUi();
		BaylandtagController baylandtagController = new BaylandtagController(baylandtagUi);
		baylandtagController.initialize();
		baylandtagController.setDatabase(database);
		
		MainUi mainUi = new MainUi();
		MainController mainController = new MainController(mainUi);
		mainController.initialize(); // does nothing but we stay with the pattern for demo purpose.
		mainController.setDatabase(database);
		mainController.setLoginTabContent(loginUi.create());
		mainController.setBaylandtagTabContent(baylandtagUi.create());
		
		Parent root = mainUi.create();
		Scene mainScene = new Scene(root);
		primaryStage.setScene(mainScene);
		primaryStage.show();

	}


}
