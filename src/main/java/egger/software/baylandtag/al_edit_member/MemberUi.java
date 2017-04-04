package egger.software.baylandtag.al_edit_member;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MemberUi {

	private TextField titleTextField = new TextField();
	private TextField surnameTextField = new TextField();
	private TextField forenameTextField = new TextField();
	
	public Parent create() {

		GridPane memberPane = new GridPane();

		Label titleLabel = new Label("Titel");
		Label surnameLabel = new Label("Name");
		Label forenameLabel = new Label("Vorname");

		titleTextField.setPromptText("Titel");
		surnameTextField.setPromptText("Name");
		forenameTextField.setPromptText("Vorname");

		memberPane.add(titleLabel, 0, 0);
		memberPane.add(surnameLabel, 0, 1);
		memberPane.add(forenameLabel, 0, 2);

		memberPane.add(titleTextField, 1, 0);
		memberPane.add(surnameTextField, 1, 1);
		memberPane.add(forenameTextField, 1, 2);

		return memberPane;

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
	
	

}
