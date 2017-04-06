package baylandtag.ar_context_menu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ContextMenuExample extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox root = new HBox();

		Label label = new Label("Context Menu Test");
		root.getChildren().add(label);
		
		// 1.
		ContextMenu contextMenu = new ContextMenu();
		contextMenu.setOnShowing(event -> {System.out.println("showing");});
		contextMenu.setOnShown(event -> {System.out.println("shown");});
		
		MenuItem item = new MenuItem("Change");
		item.setOnAction(event -> {label.setText("Changed");});
		contextMenu.getItems().add(item);
		label.setContextMenu(contextMenu);

		// 2. 
		contextMenu.setOnShowing(event -> {System.out.println("showing"); item.setText("Change the label");}); // before the menu is shown
		contextMenu.setOnShown(event -> {System.out.println("shown");}); // right after it appears on the screen

		Scene mainScene = new Scene(root);
		primaryStage.setWidth(600);
		primaryStage.setScene(mainScene);
		primaryStage.show();

	}

}
