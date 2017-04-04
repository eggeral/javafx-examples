package egger.software.baylandtag.ad_bind_connected_field;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BindingExample extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		// 1. simple binding

		GridPane testPane = new GridPane();
		
		Label boundLabel = new Label();
		TextField boundTextField = new TextField();
		testPane.add(boundLabel, 0, 0);
		testPane.add(boundTextField, 0, 1);

		boundLabel.textProperty().bind(boundTextField.textProperty());

		Scene mainScene = new Scene(testPane);
		primaryStage.setScene(mainScene);

		// 2. on off switch implementation
		Label boundLamp = new Label();
		ToggleButton boundSwitch = new ToggleButton("Switch");
		testPane.add(boundLamp, 0, 2);
		testPane.add(boundSwitch, 0, 3);

		// simple binding again
		// boundLamp.textProperty().bind(boundSwitch.selectedProperty().asString());
		boundLamp.textProperty().bind(Bindings.when(boundSwitch.selectedProperty()).then("ON").otherwise("OFF"));

		// 3. define and bind our own property
		
		Label connectedLabel = new Label();
		ToggleButton connectButton = new ToggleButton("Connect");
		testPane.add(connectedLabel, 0, 4);
		testPane.add(connectButton, 0, 5);

		BooleanProperty connected = new SimpleBooleanProperty(false);
		connectButton.setOnMouseClicked((event) -> {
			connected.set(connectButton.isSelected());
		});				
		
		// in a real class the following should be implemented
		// private BooleanProperty connected = new SimpleBooleanProperty(false);
		// public boolean isConnected() {return connected.get();}
		// public void setConnected(double value){connected.set(value);}
		// public BooleanProperty connectedProperty() {return connected;}
		
		connectedLabel.textProperty().bind(Bindings.when(connected).then("Connected").otherwise("Not Connected"));

		primaryStage.show();

	}

}
