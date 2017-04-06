package baylandtag.ao_filter_table;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MembersController {

	private TableView<Member> membersTable;
	private TextField filterTextField;
	
	private ObservableList<Member> members;
	private FilteredList<Member> filteredMembers; // we can not add values to a FilteredList

	private Database database;

	private Parent memberUi;
	private MemberController memberController = new MemberController();
	
	public MembersController(MembersUi ui) {
		membersTable = ui.getMemberTable();
		filterTextField = ui.getFilterTextField();
	}

	public void initialize() throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(Utils.getResourceInCurrentPackage("MemberUi.fxml"));
		fxmlLoader.setController(memberController);
		memberUi = fxmlLoader.load();
		
		filterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredMembers.setPredicate(member -> {
				if (newValue == null || newValue.isEmpty())
					return true;
				if (member.getSurname().toLowerCase().startsWith(newValue.toLowerCase()))
					return true;
				return false;
			});
		});
	}

	public void setDatabase(Database database) {

		if (this.database != null)
			throw new IllegalStateException("Database already set for LoginController.");

		this.database = database;

		database.connectedProperty().addListener((observable, oldValue, newValue) -> {

			if (newValue == true)
				members = FXCollections.observableList(database.getAllMembers());
			else
				members = FXCollections.observableArrayList();

			filteredMembers = new FilteredList<>(members);
			membersTable.setItems(filteredMembers);

		});

	}

	public void addMember() {

		Dialog<Member> dialog = new Dialog<>();
		dialog.getDialogPane().setContent(memberUi);
		dialog.setResultConverter(dialogButton -> {

			if (dialogButton == ButtonType.OK)
				return new Member(null, memberController.getSurnameTextField().getText(),
						memberController.getForenameTextField().getText(), memberController.getTitleTextField().getText());

			return null;

		});

		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		dialog.showAndWait().ifPresent(member -> {
			try {
				database.addMember(member);
				members.add(member);
				membersTable.getSelectionModel().select(member);
				membersTable.scrollTo(member);
			} catch (DatabaseException ex) {
				Utils.showException(ex);
			}
		});

	}

	public void deleteSelectedMember() {
		Member member = membersTable.getSelectionModel().getSelectedItem();

		if (member == null) {
			Alert alert = new Alert(Alert.AlertType.WARNING,
					"Löschen kann nicht ausgeführt werden weil kein Abgeordneter ausgewählt wurde.");
			alert.setTitle("Achtung");
			alert.setHeaderText("Kein Abgeordneter ausgewählt");
			alert.show();
			return;
		}

		try {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Wollen sie den Abgeordneten " + member.getSurname()
					+ " " + member.getForename() + " wirklich löschen?");
			alert.setTitle("Bitte bestätigen");
			alert.setHeaderText("Abgeordneten löschen");
			alert.showAndWait().ifPresent(buttonType -> {
				if (buttonType == ButtonType.OK) {
					database.deleteMember(member);
					members.remove(member);
				}
			});

		} catch (DatabaseException ex) {
			Utils.showException(ex);
		}
	}

	public void editSelectedMember() {
		Member member = membersTable.getSelectionModel().getSelectedItem();

		if (member == null) {
			Alert alert = new Alert(Alert.AlertType.WARNING,
					"Bearbeiten kann nicht ausgeführt werden weil kein Abgeordneter ausgewählt wurde.");
			alert.setTitle("Achtung");
			alert.setHeaderText("Kein Abgeordneter ausgewählt");
			alert.show();
			return;
		}

		memberController.getTitleTextField().setText(member.getTitle());
		memberController.getSurnameTextField().setText(member.getSurname());
		memberController.getForenameTextField().setText(member.getForename());
		Dialog<Member> dialog = new Dialog<>();
		dialog.getDialogPane().setContent(memberUi);
		dialog.setResultConverter(dialogButton -> {

			if (dialogButton == ButtonType.OK)
				return new Member(member.getId(), memberController.getSurnameTextField().getText(),
						memberController.getForenameTextField().getText(), memberController.getTitleTextField().getText());

			return null;

		});

		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		dialog.showAndWait().ifPresent(editedMember -> {
			try {
				database.updateMember(editedMember);
				
				member.setId(editedMember.getId());
				member.setSurname(editedMember.getSurname());
				member.setForename(editedMember.getForename());
				member.setTitle(editedMember.getTitle());
				
				membersTable.refresh();
			} catch (DatabaseException ex) {
				Utils.showException(ex);
			}
		});
	}

}
