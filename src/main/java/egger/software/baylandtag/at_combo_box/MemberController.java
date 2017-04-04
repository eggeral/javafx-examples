package egger.software.baylandtag.at_combo_box;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
	private ComboBox<MaritalStatus> maritalStatusComboBox;

	private int currentMemberId;

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

		maritalStatusComboBox.setConverter(new StringConverter<MaritalStatus>() {

			@Override
			public String toString(MaritalStatus maritalStatus) {
				if (maritalStatus == null)
					return "";
				return maritalStatus.getName();
			}

			@Override
			public MaritalStatus fromString(String name) {
				return new MaritalStatus(-1, name);
			}

		});
		
		maritalStatusComboBox.setEditable(true);
				
	}

	public void setConfessions(ObservableList<Confession> confessions) {
		confessionChoiceBox.getItems().clear();
		confessionChoiceBox.getItems().addAll(confessions);
		confessionChoiceBox.setValue(confessions.get(0));
	}

	public void setMaritalStatus(ObservableList<MaritalStatus> maritalStatus) {
		maritalStatusComboBox.getItems().clear();
		maritalStatusComboBox.getItems().addAll(maritalStatus);
		maritalStatusComboBox.setValue(maritalStatus.get(0));
	}

	public void setMember(Member member) {
		if (member == null) {
			currentMemberId = -1;
			titleTextField.setText(null);
			surnameTextField.setText(null);
			forenameTextField.setText(null);
			confessionChoiceBox.setValue(confessionChoiceBox.getItems().get(0));
			maritalStatusComboBox.setValue(maritalStatusComboBox.getItems().get(0));
		} else {
			currentMemberId = member.getId();
			titleTextField.setText(member.getTitle());
			surnameTextField.setText(member.getSurname());
			forenameTextField.setText(member.getForename());
			confessionChoiceBox.setValue(member.getConfession());
			maritalStatusComboBox.setValue(member.getMaritalStatus());
		}
	}

	public Member getMember() {
		return new Member(currentMemberId, surnameTextField.getText(), forenameTextField.getText(),
				titleTextField.getText(), confessionChoiceBox.getValue(), maritalStatusComboBox.getValue());
	}

}
