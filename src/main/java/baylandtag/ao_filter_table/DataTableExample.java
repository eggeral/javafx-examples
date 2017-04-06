package baylandtag.ao_filter_table;

import java.text.Collator;
import java.util.Locale;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DataTableExample extends Application {
	public static class Flight {
		private String number;
		private String from;
		private String to;

		public Flight(String number, String from, String to) {
			this.number = number;
			this.from = from;
			this.to = to;
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
		TableColumn<Flight, String> flightFromCol = new TableColumn<>("From");
		TableColumn<Flight, String> flightToCol = new TableColumn<>("To");
		flightsTable.getColumns().add(flightNumberCol);
		flightsTable.getColumns().add(flightFromCol);
		flightsTable.getColumns().add(flightToCol);

		root.setCenter(flightsTable);

		Scene mainScene = new Scene(root);
		primaryStage.setWidth(600);
		primaryStage.setScene(mainScene);
		primaryStage.show();

		ObservableList<Flight> flights = FXCollections.observableArrayList();
		flights.add(new Flight("OS201", "GRZ", "DUS"));
		flights.add(new Flight("LH2123", "GRZ", "FRA"));
		flights.add(new Flight("DF345", "VIE", "MUN"));
		flights.add(new Flight("LH2333", "FRA", "HUM"));
		flights.add(new Flight("LH1998", "HUM", "FRA"));

		flightNumberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
		flightFromCol.setCellValueFactory(new PropertyValueFactory<>("from"));
		flightToCol.setCellValueFactory(new PropertyValueFactory<>("to"));

		// ------------ Filtering ----

		// 1.
		TextField filterNumber = new TextField();
		filterNumber.setPromptText("Filter Number");
		TextField filterFrom = new TextField();
		filterFrom.setPromptText("Filter From");
		TextField filterTo = new TextField();
		filterTo.setPromptText("Filter To");

		filterNumber.setPromptText("Type here to filter");

		// 2.
		FilteredList<Flight> filteredFlights = new FilteredList<>(flights);
		flightsTable.setItems(filteredFlights);

		// 3.
		ChangeListener<String> filterChangeListener = (observable, oldValue, newValue) -> {
			filteredFlights.setPredicate((flight) -> {
				if (flight.getNumber().startsWith(filterNumber.getText())
						&& flight.getFrom().startsWith(filterFrom.getText())
						&& flight.getTo().startsWith(filterTo.getText()))
					return true;
				return false;
			});
		};

		filterNumber.textProperty().addListener(filterChangeListener);
		filterFrom.textProperty().addListener(filterChangeListener);
		filterTo.textProperty().addListener(filterChangeListener);

		// 4. add sorting again
		SortedList<Flight> sortedFlights = new SortedList<>(filteredFlights);
		flightsTable.setItems(sortedFlights);
		sortedFlights.comparatorProperty().bind(flightsTable.comparatorProperty());

		flightNumberCol.setComparator((lhs, rhs) -> {
			Collator collator = Collator.getInstance(Locale.GERMAN);
			collator.setStrength(Collator.SECONDARY);// a == A, a < Ä
			return collator.compare(lhs, rhs);
		});

		Button removeButton = new Button("Remove Flight");
		TextField number = new TextField();
		number.setPromptText("Flight Number");
		TextField from = new TextField();
		from.setPromptText("From");
		TextField to = new TextField();
		to.setPromptText("To");
		Button addButton = new Button("Add Flight");
		ToolBar addToolBar = new ToolBar(removeButton, number, from, to, addButton);

		ToolBar filterToolBar = new ToolBar(filterNumber, filterFrom, filterTo);

		VBox top = new VBox(addToolBar, filterToolBar);
		root.setTop(top);

		removeButton.setOnMouseClicked(event -> {
			Flight selectedFlight = flightsTable.getSelectionModel().getSelectedItem();
			if (selectedFlight != null) {
				flights.remove(selectedFlight);
			}
		});

		addButton.setOnMouseClicked(event -> {
			Flight newFlight = new Flight(number.getText(), from.getText(), to.getText());
			flights.add(newFlight);
		});

		flightsTable.setTableMenuButtonVisible(true);
	}

}
