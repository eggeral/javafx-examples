
package baylandtag.aj_add_member;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuExample extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		VBox root = new VBox(); // use a VBox so the menu bar is on the top.

		Label text = new Label();
		
		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("File");
		Menu editMenu = new Menu("Edit");
		Menu helpMenu = new Menu("Help");
		
		MenuItem addMenuItem = new MenuItem("Add Text");
		
		addMenuItem.setOnAction(event -> {
			String currentText = text.getText();
			text.setText(currentText + " - 1234\n");
		});
		
		editMenu.getItems().add(addMenuItem);

		menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
		//menuBar.setUseSystemMenuBar(true); // Use macOS system menu bar
		root.getChildren().addAll(menuBar, text);
		Scene mainScene = new Scene(root);

		primaryStage.setScene(mainScene);

		primaryStage.show();

	}
}
