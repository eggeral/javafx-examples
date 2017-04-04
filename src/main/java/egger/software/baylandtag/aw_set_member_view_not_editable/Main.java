package egger.software.baylandtag.aw_set_member_view_not_editable;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	// 1. Cell factory for marital status column
	
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

			MembersUi membersUi = new MembersUi();
			MembersController membersController = new MembersController(membersUi);
			membersController.initialize();
			membersController.setDatabase(database);

			BrowseUi browseUi = new BrowseUi();
			BrowseController browseController = new BrowseController(browseUi);
			browseController.initialize();
			browseController.setDatabase(database);

			MainUi mainUi = new MainUi();
			MainController mainController = new MainController(mainUi);
			mainController.initialize(); 
			mainController.setDatabase(database);
			
			mainController.setLoginTabContent(loginUi.create());
			mainController.setMembersTabContent(membersUi.create());
			mainController.setBrowseTabContent(browseUi.create());

			mainController.setAddMemberAction(() -> {membersController.addMember();});
			mainController.setDeleteMemberAction(() -> {membersController.deleteSelectedMember();});
			mainController.setEditMemberAction(() -> {membersController.editSelectedMember();});

			Parent root = mainUi.create();
			Scene mainScene = new Scene(root);
			mainScene.getStylesheets()
					.add(getClass().getPackage().getName().replaceAll("\\.", "/") + "/baylandtag.css");

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
