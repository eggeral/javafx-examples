package egger.software.baylandtag.an_fxml;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FxmlExampleController {
	
	@FXML private Label myLabel;
	@FXML private Button myButton;
	
	// @FXML is optional here. I like to mark the method!
	@FXML public void initialize() {
		myButton.setOnAction(event -> { myLabel.setText("My Button 2 clicked");});
	}

	@FXML 
	public void onButtonClick() {
		myLabel.setText("Button clicked");
	}


}
