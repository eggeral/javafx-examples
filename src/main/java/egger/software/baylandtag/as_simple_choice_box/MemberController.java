package egger.software.baylandtag.as_simple_choice_box;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class MemberController {

	@FXML
	private TextField titleTextField;
	@FXML
	private TextField surnameTextField;
	@FXML
	private TextField forenameTextField;
	@FXML
	private ChoiceBox<Confession> confessionChoiceBox;

	@FXML
	public void initialize() {
		confessionChoiceBox.setConverter(new StringConverter<Confession>() {

			@Override
			public String toString(Confession confession) {
				if (confession == null)
					return "";
				return confession.getName();
			}

			@Override
			public Confession fromString(String name) {
				return new Confession(-1, name);
			}

		});
	}

	public void setConfessions(List<Confession> confessions) {
		confessionChoiceBox.getItems().clear();
		confessionChoiceBox.getItems().addAll(confessions);
		confessionChoiceBox.setValue(confessions.get(0));
	}

	public TextField getTitleTextField() {
		return titleTextField;
	}

	public TextField getSurnameTextField() {
		return surnameTextField;
	}

	public TextField getForenameTextField() {
		return forenameTextField;
	}

	public ChoiceBox<Confession> getConfessionChoiceBox() {
		return confessionChoiceBox;
	}

}
