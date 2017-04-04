package egger.software.baylandtag.am_tree_view_browser;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TreeViewExample extends Application {

	public static class Carrier {
		private String id;

		public Carrier(String id) {
			this.id = id;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

	}

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

		// 1. Simple tree only having String nodes.
		VBox root = new VBox();

		TreeItem<String> treeRoot = new TreeItem<String>("Root Node");
		treeRoot.setExpanded(true);
		treeRoot.getChildren().add(new TreeItem<String>("Item 1"));
		treeRoot.getChildren().add(new TreeItem<String>("Item 2"));

		TreeItem<String> level2 = new TreeItem<String>("Item 3");
		level2.getChildren().add(new TreeItem<String>("Sub Item 1"));
		level2.getChildren().add(new TreeItem<String>("Sub Item 2"));
		treeRoot.getChildren().add(level2);
		TreeView<String> treeView = new TreeView<String>(treeRoot);

		root.getChildren().add(treeView);

		// 2. complex tree having different items at each level
		TreeItem<Object> flightRoot = new TreeItem<Object>("Root Node");
		flightRoot.getChildren().add(new TreeItem<Object>(new Carrier("OS")));

		TreeItem<Object> lh = new TreeItem<Object>(new Carrier("LH"));
		lh.getChildren().add(new TreeItem<Object>(new Flight("LH5432")));
		lh.getChildren().add(new TreeItem<Object>(new Flight("LH1109")));

		flightRoot.getChildren().add(lh);
		TreeView<Object> flightTreeView = new TreeView<Object>(flightRoot);

		root.getChildren().add(flightTreeView);

		// 3. Render items depending on their type

		class FlightTreeCell extends TreeCell<Object> {
			@Override
			public void updateItem(Object item, boolean empty) {
				super.updateItem(item, empty);
				
				if (empty) { 
					setText(null);
					setGraphic(null);
					return;
				}

				if (item instanceof String) {
					setText(item.toString());
				} else if (item instanceof Carrier) {
					Carrier carrier = (Carrier) item;
					setText(carrier.id);
				} else if (item instanceof Flight) {
					Flight flight = (Flight) item;
					setText(flight.number);
					// 4. Click on a cell listener
					setOnMouseClicked(event -> {System.out.println(flight.number + " clicked");});
				}

			}
		}

		flightTreeView.setCellFactory((view) -> {
			return new FlightTreeCell();
		});

		
		Scene mainScene = new Scene(root);

		primaryStage.setScene(mainScene);

		primaryStage.show();
	}

}
