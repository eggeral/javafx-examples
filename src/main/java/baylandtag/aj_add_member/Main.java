package baylandtag.aj_add_member;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	// 1. MenuExample + Add menu and add item
	// 2. CustomDialogExample
	// 3. Add Menu to Main UI
	// 4. MemberUi
	// 5. Add addMember() to BaylandtagController
	// 6. Implement addMember in Database
	// 7. Connect menu item action to BaylandtagController
	// 8. Add dialog to BaylandtagController
	// 9. Select added item
	// 10. Scroll to added item
	// 11. Set default sort to first column
	private Configuration configuration = new Configuration();
	private Database database = new Database();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		try {

			configuration.load();

			database.setUrl(configuration.getDatabaseUrl());
			database.setUser(configuration.getUsername());
			database.setPassword(configuration.getPassword());

			// we could also use properties in the configuration an connect them
			// but we do it this way in order not
			// to over do binding usage.
			database.connectedProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue == true) {
					configuration.setDatabaseUrl(database.getUrl());
					configuration.setUsername(database.getUser());
					configuration.setPassword(database.getPassword());
					try {
						configuration.save();
					} catch (IOException e) {
						Utils.showException(e);
					}
				}
			});

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
			mainController.initialize(); // does nothing but we stay with the
											// pattern for demo purpose.
			mainController.setDatabase(database);
			mainController.setLoginTabContent(loginUi.create());
			mainController.setBaylandtagTabContent(baylandtagUi.create());
			mainController.setAddMemberAction(() -> {baylandtagController.addMember();});

			Parent root = mainUi.create();
			Scene mainScene = new Scene(root);
			mainScene.getStylesheets()
					.add(Main.class.getPackage().getName().replaceAll("\\.", "/") + "/baylandtag.css");

			primaryStage.setScene(mainScene);
			primaryStage.setWidth(configuration.getStageWidth());
			primaryStage.setHeight(configuration.getStageHeight());

			primaryStage.widthProperty().addListener((observable, oldValue, newValue) -> {
				configuration.setStageWidth((Double) newValue);
			});

			primaryStage.heightProperty().addListener((observable, oldValue, newValue) -> {
				configuration.setStageHeight((Double) newValue);
			});

			primaryStage.show();

		} catch (IOException e) {
			Utils.showException(e);
		}

	}

	@Override
	public void stop() {
		try {
			configuration.save();
		} catch (IOException e) {
			Utils.showException(e);
		}
	}

}
