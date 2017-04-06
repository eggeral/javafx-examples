package baylandtag.aj_add_member;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomDialogExample extends Application {

	public static class Flight {
		private String number;

		public Flight(String number) {
			this.number = number;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		VBox root = new VBox(); // use a VBox so the menu bar is on the top.

		Label text = new Label();

		MenuBar menuBar = new MenuBar();
		Menu editMenu = new Menu("Edit");

		MenuItem addFlightMenuItem = new MenuItem("Add Flight");

		editMenu.getItems().add(addFlightMenuItem);

		menuBar.getMenus().addAll(editMenu);
		root.getChildren().addAll(menuBar, text);

		addFlightMenuItem.setOnAction(event -> {
			Dialog<Flight> dialog = new Dialog<>();
			TextField number = new TextField();
			dialog.getDialogPane().setContent(new HBox(new Label("Flight number"), number));
			dialog.setResultConverter(dialogButton -> {
				if (dialogButton == ButtonType.OK) {
					return new Flight(number.getText());
				}
				return null;
			});
			dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

			dialog.showAndWait().ifPresent(flight -> {
				text.setText(flight.number);
			});
		});
		
		addFlightMenuItem.setOnAction(event -> {System.out.println("TEST");});
		Scene mainScene = new Scene(root);

		primaryStage.setScene(mainScene);

		primaryStage.show();
	}

}
