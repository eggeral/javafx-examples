package baylandtag.ag_dummy_person_table;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class DataTableExample extends Application {
	public static class Flight {
		private String number;
		private String from;
		private String to;

		public Flight(String number, String from, String to) {
			this.number = number;
			this.setFrom(from);
			this.setTo(to);
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		public String getTo() {
			return to;
		}

		public void setTo(String to) {
			this.to = to;
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();

		TableView<Flight> flightsTable = new TableView<>();
		TableColumn<Flight, String> flightNumberCol = new TableColumn<>("Flight Number");
		flightsTable.getColumns().add(flightNumberCol);

		TableColumn<Flight, String> flightFromCol = new TableColumn<>("Von");
		flightsTable.getColumns().add(flightFromCol);

		TableColumn<Flight, String> flightToCol = new TableColumn<>("Nach");
		flightsTable.getColumns().add(flightToCol);

		root.setCenter(flightsTable);

		Scene mainScene = new Scene(root);
		primaryStage.setScene(mainScene);
		primaryStage.show();

		// -- step 1 add flights to the table
		ObservableList<Flight> flights = FXCollections.observableArrayList();
		flights.add(new Flight("OS201", "GRZ", "DUS"));
		flights.add(new Flight("LH2123", "GRZ", "FRA"));
		flightsTable.setItems(flights);

		// -- step 2 show flight number
		flightNumberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
		flightFromCol.setCellValueFactory(new PropertyValueFactory<>("from"));
		flightToCol.setCellValueFactory(new PropertyValueFactory<>("to"));

		// -- step 3 add / remove entry
		Button removeButton = new Button("Remove Flight");
		TextField number = new TextField();
		number.setPromptText("Number");
		TextField from = new TextField();
		from.setPromptText("From");
		TextField to = new TextField();
		to.setPromptText("To");

		Button addButton = new Button("Add Flight");
		ToolBar toolbar = new ToolBar(removeButton, number, from, to, addButton);
		root.setTop(toolbar);

		removeButton.setOnAction(event -> {
			Flight selectedFlight = flightsTable.getSelectionModel().getSelectedItem();
			if (selectedFlight != null) {
				flights.remove(selectedFlight);
			}
		});

		addButton.setOnAction(event -> {
			Flight newFlight = new Flight(number.getText(), from.getText(), to.getText());
			flights.add(newFlight);
		});

		// -- step 4 observe selection changes
		flightsTable.getSelectionModel().selectedItemProperty().addListener((src, oldFlight, newFlight) -> {
			System.out.println("change");
			if (oldFlight != null)
				System.out.println("old: " + oldFlight.getNumber());
			if (newFlight != null)
				System.out.println("new: " + newFlight.getNumber());
		});

		// -- step 6 show / hide columns
		flightsTable.setTableMenuButtonVisible(true);

		// -- step 7 set the column width to fit text plus sort arrow
		// we can not get the font of the header! The header style is css only
		// :-(
		// we can only guess the font!
		// we can only guess the size of the arrow :-(
		// double columnWidth = calculateColumnWidth(flightNumberCol.getText());
		// flightNumberCol.setPrefWidth(columnWidth);

		// -- step 8 set the column with in percentage of the table width
		flightNumberCol.prefWidthProperty().bind(flightsTable.widthProperty().multiply(0.5));
		flightFromCol.prefWidthProperty().bind(flightsTable.widthProperty().multiply(0.25));
		flightToCol.prefWidthProperty().bind(flightsTable.widthProperty().multiply(0.25));

		// -- step 9 custom CellValueFactory
		flightNumberCol.setCellValueFactory(cellData -> {
			return new SimpleStringProperty(cellData.getValue().getNumber());
		});

		// -- step 10 custom CellFactory
		flightNumberCol.setCellFactory(column -> {
			return new TableCell<Flight, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty)
						return;

					setText(item);
					if (item.startsWith("LH"))
						setTextFill(Color.YELLOW);
					else
						setTextFill(Color.BLACK);
					setGraphic(new Circle(5.0, Color.GREEN));
				}
			};
		});

	}

	// private double calculateColumnWidth(String header) {
	// FontMetrics fontMetrics =
	// Toolkit.getToolkit().getFontLoader().getFontMetrics(new Font("Arial",
	// 14));
	// return fontMetrics.computeStringWidth(header + "mmm");
	// }

}
