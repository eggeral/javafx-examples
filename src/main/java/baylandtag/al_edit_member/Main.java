package baylandtag.al_edit_member;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	// 1. Add edit menu item an link to BaylandtagController editSelectedMember
	// 2. Implement editSelectedMember on the list
	// 3. Implement updateMember in the database
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
			mainController.initialize(); 
			mainController.setDatabase(database);
			mainController.setLoginTabContent(loginUi.create());
			mainController.setBaylandtagTabContent(baylandtagUi.create());
			mainController.setAddMemberAction(() -> {baylandtagController.addMember();});
			mainController.setDeleteMemberAction(() -> {baylandtagController.deleteSelectedMember();});
			mainController.setEditMemberAction(() -> {baylandtagController.editSelectedMember();});

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
