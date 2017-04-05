package egger.software.baylandtag.ai_style_and_refactor_the_app;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	// 1. set the size of the app
	// 2. remember the size of the app -> Refactor Utils.loadDatabaseProperties
	// -> Configuration
	// 3. use password text field, add prompt text
	// 4. login grid padding programmatically.
	// 4. login grid padding using css
	// 5. baylandtagPane -> borderLayout

	private Configuration configuration = new Configuration();
	private Database database = new Database();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
			Utils.showException(throwable);
			Platform.exit(); // will call stop()
		});

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

			Parent root = mainUi.create();
			Scene mainScene = new Scene(root);
			mainScene.getStylesheets()
					.add(Main.class.getPackage().getName().replaceAll("\\.", "/") + "/baylandtag.css");

			primaryStage.setScene(mainScene);
			// 1. set the size of the app
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

	// 2. Store stage properties
	@Override
	public void stop() throws Exception {
		try {
			configuration.save();
		} catch (IOException e) {
			Utils.showException(e);
		}
	}

}
