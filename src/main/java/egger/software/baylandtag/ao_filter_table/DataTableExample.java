package egger.software.baylandtag.ao_filter_table;

import java.text.Collator;
import java.util.Locale;

import javafx.application.Application;
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
import javafx.stage.Stage;

public class DataTableExample extends Application {
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
		BorderPane root = new BorderPane();

		TableView<Flight> flightsTable = new TableView<>();
		TableColumn<Flight, String> flightNumberCol = new TableColumn<>("Flight Number");
		flightsTable.getColumns().add(flightNumberCol);

		root.setCenter(flightsTable);

		Scene mainScene = new Scene(root);
		primaryStage.setWidth(600);
		primaryStage.setScene(mainScene);
		primaryStage.show();

		ObservableList<Flight> flights = FXCollections.observableArrayList();
		flights.add(new Flight("OS201"));
		flights.add(new Flight("LH2123"));
		flights.add(new Flight("DF345"));
		flights.add(new Flight("LH2333"));
		flights.add(new Flight("LH1998"));

		flightNumberCol.setCellValueFactory(new PropertyValueFactory<>("number"));

		// ------------ Filtering ----

		// 1.
		TextField filter = new TextField();
		filter.setPromptText("Type here to filter");

		// 2.
		FilteredList<Flight> filteredFlights = new FilteredList<>(flights);
		flightsTable.setItems(filteredFlights);

		// 3.
		filter.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredFlights.setPredicate(flight -> {
				if (newValue == null || newValue.isEmpty())
					return true;
				if (flight.number.startsWith(newValue))
					return true;
				return false;
			});
		});

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
		Button addButton = new Button("Add Flight");
		ToolBar toolbar = new ToolBar(filter, removeButton, number, addButton);
		root.setTop(toolbar);
		

		removeButton.setOnMouseClicked(event -> {
			Flight selectedFlight = flightsTable.getSelectionModel().getSelectedItem();
			if (selectedFlight != null) {
				flights.remove(selectedFlight);
			}
		});

		addButton.setOnMouseClicked(event -> {
			Flight newFlight = new Flight(number.getText());
			flights.add(newFlight);
		});

		flightsTable.setTableMenuButtonVisible(true);
	}

}
