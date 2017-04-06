package baylandtag.as_simple_choice_box;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	// 1. ChoiceBoxExample
	// 2. Show Confession in  MemberTable. 
	// 2.a Get Confession of Member from the database
	// 2.b Set Confession when loading a Member from the database
	// 2.c Column data type is now Confession not String 
	// 3. CellTableValueFactory (custom) for extracting Confession from Member
	// 4. setCellFactory to ChoiceBoxTableCell, set as StringConverter
	// 5. read all confessions from the db and set items into cellfactory
	// 6. setOnEditCommit
	// 7. Change update of member
	// 8. select default confession when clicking the add button
	// 9. add confession to MemberUi
	// 10. set confessions into MemberController
	// 11. addMember extend with confession
	// 12. editMember extend with confession
	// 13. browse with confession
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
