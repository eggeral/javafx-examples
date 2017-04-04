package egger.software.baylandtag.as_simple_choice_box;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ChoiceBoxExample extends Application {

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
		ChoiceBox<Flight> choiceBox = new ChoiceBox<Flight>();
		choiceBox.getItems().addAll(flights);

		Label label = new Label("Label");
		root.getChildren().add(choiceBox);
		root.getChildren().add(label);

		choiceBox.valueProperty().addListener((observable, oldFlight, newFlight) -> {
			label.setText(newFlight.number);
		});

		// 2. use a converter
		choiceBox.setConverter(new StringConverter<Flight>() {

			@Override
			public String toString(Flight flight) {
				return flight.getNumber();
			}

			@Override
			public Flight fromString(String string) {
				return new Flight(string);
			}

		});
		
		// 3. set the selected value
		choiceBox.setValue(flights.get(0));

		Scene mainScene = new Scene(root);
		primaryStage.setWidth(600);
		primaryStage.setScene(mainScene);
		primaryStage.show();

	}

}
