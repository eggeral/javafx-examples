package baylandtag.ap_edit_table_row;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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

		ObservableList<Flight> flights = FXCollections.observableArrayList();
		flights.add(new Flight("OS201"));
		flights.add(new Flight("LH2123"));
		flights.add(new Flight("DF345"));
		flights.add(new Flight("LH2333"));
		flights.add(new Flight("LH1998"));

		flightNumberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
		flightsTable.setItems(flights);

		// 1.
		flightsTable.setEditable(true);
		flightNumberCol.setCellFactory(TextFieldTableCell.forTableColumn());

		// 2.
//		flightNumberCol.setOnEditCommit(event -> {
//			System.out.println(
//					"OnEditCommit: Table item " + event.getTableView().getItems().get(event.getTablePosition().getRow())
//							+ " -> " + event.getNewValue());
//			
//			// note that the flight does not change.
//		});
		
		// 3.
		flightNumberCol.setOnEditCancel(event -> {
			System.out.println("OnEditCancel");
		});

		// 4.
		flightNumberCol.setOnEditCommit(event -> {
			System.out.println(
					"OnEditCommit: Table item " + event.getTableView().getItems().get(event.getTablePosition().getRow())
							+ " -> " + event.getNewValue());

			Flight flight =  event.getTableView().getItems().get(event.getTablePosition().getRow());
			flight.setNumber(event.getNewValue());
			
			// note that the flight does not change.
		});

		
	}

}
