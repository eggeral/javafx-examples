package baylandtag.ay_pagination;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
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

		ObservableList<Flight> flights = FXCollections.observableArrayList();
		flights.add(new Flight("OS201", "GRZ", "DUS"));
		flights.add(new Flight("LH2123", "GRZ", "FRA"));
		flights.add(new Flight("A", "GRZ", "FRA"));
		flights.add(new Flight("B", "GRZ", "FRA"));
		flights.add(new Flight("C", "GRZ", "FRA"));
		flights.add(new Flight("D", "GRZ", "FRA"));
		flights.add(new Flight("E", "GRZ", "FRA"));
		flights.add(new Flight("F", "GRZ", "FRA"));
		flights.add(new Flight("G", "GRZ", "FRA"));
		flights.add(new Flight("H", "GRZ", "FRA"));
		flights.add(new Flight("I", "GRZ", "FRA"));

		flightNumberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
		flightFromCol.setCellValueFactory(new PropertyValueFactory<>("from"));
		flightToCol.setCellValueFactory(new PropertyValueFactory<>("to"));

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
		
		Pagination pagination = new Pagination((int) Math.ceil(flights.size() / 4.0));
		pagination.setPageFactory(page -> {
			flightsTable.setItems(FXCollections
					.observableArrayList(flights.subList(page * 4, Math.min((page + 1) * 4, flights.size()))));
			return flightsTable;
		});

		root.setCenter(pagination);
		pagination.setCurrentPageIndex(1);
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

		Scene mainScene = new Scene(root);
		primaryStage.setScene(mainScene);
		primaryStage.show();

	}

}
