package baylandtag.ah_fill_member_table_from_db;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	// 1. Listener Example
	// 2. Implement get List<Member> from database in Database()
	// 3. Add listener to connected property and load member values as soon as the database is connected
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
