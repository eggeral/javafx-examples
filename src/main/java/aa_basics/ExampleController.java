package aa_basics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ExampleController {

    @FXML
    Label helloLabel;

    public void onButtonPressed(ActionEvent event) {
        helloLabel.setText("The button was pressed!");
    }
}
