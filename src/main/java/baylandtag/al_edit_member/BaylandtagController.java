package baylandtag.al_edit_member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;

public class BaylandtagController {

	private TableView<Member> memberTable;
	private ObservableList<Member> members;
	private Database database;

	public BaylandtagController(BaylandtagUi ui) {
		memberTable = ui.getMemberTable();
	}

	public void initialize() {
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

			memberTable.setItems(members);

		});

	}

	public void addMember() {

		MemberUi memberUi = new MemberUi();
		Parent content = memberUi.create();
		Dialog<Member> dialog = new Dialog<>();
		dialog.getDialogPane().setContent(content);
		dialog.setResultConverter(dialogButton -> {

			if (dialogButton == ButtonType.OK)
				return new Member(null, memberUi.getSurnameTextField().getText(),
						memberUi.getForenameTextField().getText(), memberUi.getTitleTextField().getText());

			return null;

		});

		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		dialog.showAndWait().ifPresent(member -> {
			try {
				database.addMember(member);
				members.add(member);
				memberTable.getSelectionModel().select(member);
				memberTable.scrollTo(member);
			} catch (DatabaseException ex) {
				Utils.showException(ex);
			}
		});

	}

	public void deleteSelectedMember() {
		Member member = memberTable.getSelectionModel().getSelectedItem();

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
		Member member = memberTable.getSelectionModel().getSelectedItem();

		if (member == null) {
			Alert alert = new Alert(Alert.AlertType.WARNING,
					"Bearbeiten kann nicht ausgeführt werden weil kein Abgeordneter ausgewählt wurde.");
			alert.setTitle("Achtung");
			alert.setHeaderText("Kein Abgeordneter ausgewählt");
			alert.show();
			return;
		}

		MemberUi memberUi = new MemberUi();
		memberUi.getTitleTextField().setText(member.getTitle());
		memberUi.getSurnameTextField().setText(member.getSurname());
		memberUi.getForenameTextField().setText(member.getForename());
		Parent content = memberUi.create();
		Dialog<Member> dialog = new Dialog<>();
		dialog.getDialogPane().setContent(content);
		dialog.setResultConverter(dialogButton -> {

			if (dialogButton == ButtonType.OK)
				return new Member(member.getId(), memberUi.getSurnameTextField().getText(),
						memberUi.getForenameTextField().getText(), memberUi.getTitleTextField().getText());

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
				
				memberTable.refresh();
			} catch (DatabaseException ex) {
				Utils.showException(ex);
			}
		});
	}

}
