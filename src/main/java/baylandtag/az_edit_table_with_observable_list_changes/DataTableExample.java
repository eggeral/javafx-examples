package baylandtag.az_edit_table_with_observable_list_changes;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DataTableExample extends Application {
	public static class Flight {

		private StringProperty number = new SimpleStringProperty();

		public Flight(String number) {
			setNumber(number);
		}

		public String getNumber() {
			return number.get();
		}

		public void setNumber(String value) {
			number.set(value);
		}

		public StringProperty numberProperty() {
			return number;
		}

		@Override
		public String toString() {
			return "Flight [number=" + number + "]";
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

		List<Flight> flights = new ArrayList<>();
		flights.add(new Flight("OS201"));
		flights.add(new Flight("LH2123"));
		flights.add(new Flight("DF345"));
		flights.add(new Flight("LH2333"));
		flights.add(new Flight("LH1998"));

		// Make the list aware of flight properties which trigger update
		// changes.
		ObservableList<Flight> observableFlights = FXCollections.observableList(flights, flight -> {
			Observable[] observables = new Observable[1];
			observables[0] = flight.number;
			return observables;
		});

		flightNumberCol.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
		flightsTable.setItems(observableFlights);

		flightsTable.setEditable(true);
		flightNumberCol.setCellFactory(TextFieldTableCell.forTableColumn());

		observableFlights.addListener((Change<? extends Flight> change) -> {
			System.out.println("flights list changed");
			while (change.next()) {
				System.out.println("Was updated: " + change.wasUpdated());
				System.out.println("Was added: " + change.wasAdded());
				System.out.println("Was removed: " + change.wasRemoved());
				System.out.println("From: " + change.getFrom());
				System.out.println("To: " + change.getTo());

				for (int idx = change.getFrom(); idx < change.getTo(); idx++) {
					System.out.println("Changed item: " + change.getList().get(idx));
				}

			}
		});

	}

}
