package baylandtag.an_fxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// 1. use e(fx)clipse plugin from Eclipse market place for fxml editing in Eclipse
// 2. use Scenebuilder for creating a FlowLayout with a label and a button.
// 3. load fxml in  start
// 4. set onAction property in Scenebuilder
// 5. Create FxmlController and connect to Example.fxml using fx:controller="..."
// 6. Add label id 
// 7. Injection of label into controller 
// 8. Change label on button click
// 9. New Button. Inject and add handler in init()
// 10. How to get the Controller
// 11. Set your own Controller
public class FxmlExample extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// 3.
		Parent root = FXMLLoader.load(Utils.getResourceInCurrentPackage("Example.fxml"));

		// 10.
		// FXMLLoader fxmlLoader = new
		// FXMLLoader(Utils.getResourceInCurrentPackage("Example.fxml"));
		// Parent root = fxmlLoader.load();
		// FxmlExampleController controller = (FxmlExampleController)
		// fxmlLoader.getController();

		// 11. // remove Controller statement in fxml first!
		// FXMLLoader fxmlLoader = new
		// FXMLLoader(Utils.getResourceInCurrentPackage("Example.fxml"));
		// FxmlExampleController controller = new FxmlExampleController();
		// fxmlLoader.setController(controller);
		// Parent root = fxmlLoader.load();

		Scene mainScene = new Scene(root);
		primaryStage.setWidth(800);
		primaryStage.setHeight(600);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}
}
