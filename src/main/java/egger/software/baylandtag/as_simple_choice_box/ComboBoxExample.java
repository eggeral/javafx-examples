package egger.software.baylandtag.as_simple_choice_box;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ComboBoxExample extends Application {

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
		HBox root = new HBox();

		List<Flight> flights = new ArrayList<>();
		flights.add(new Flight("LH1234"));
		flights.add(new Flight("OS234"));
		flights.add(new Flight("LH678"));

		// 1.

		ObservableList<Flight> observableFlights = FXCollections.observableArrayList(flights);
		ComboBox<Flight> comboBox = new ComboBox<Flight>(observableFlights);

		Label label = new Label("Label");
		root.getChildren().add(comboBox);
		root.getChildren().add(label);

		comboBox.valueProperty().addListener((observable, oldFlight, newFlight) -> {
			label.setText(newFlight.number);
		});

		// 2. use a converter
		comboBox.setConverter(new StringConverter<Flight>() {

			@Override
			public String toString(Flight flight) {
				return flight.getNumber();
			}

			@Override
			public Flight fromString(String string) {
				return new Flight(string);
			}

		});

		// 3. use a cell factory. We still need the StringConverter. See what happens if we do not set it :-)
		class FlightCell extends ListCell<Flight> {

			@Override
			public void updateItem(Flight flight, boolean empty) {
				super.updateItem(flight, empty);
				if (flight != null) {
					setText(flight.number);
					if (flight.number.startsWith("LH"))
						setTextFill(Color.YELLOW);
				} else {
					setText(null);
				}
			}

		}

		comboBox.setCellFactory((view) -> {
			return new FlightCell();
		});

		Scene mainScene = new Scene(root);
		primaryStage.setWidth(600);
		primaryStage.setScene(mainScene);
		primaryStage.show();

	}

}
