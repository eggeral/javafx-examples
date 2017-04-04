package egger.software.baylandtag.aj_add_member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
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
				return new Member(
						null, 
						memberUi.getSurnameTextField().getText(),
						memberUi.getForenameTextField().getText(), 
						memberUi.getTitleTextField().getText()
						);

			return null;

		});

		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		dialog.showAndWait().ifPresent(member -> {
			try {
				database.addMember(member);
				members.add(member);
				System.out.println(member);
				memberTable.getSelectionModel().select(member);
				memberTable.scrollTo(member);
			} catch (DatabaseException ex) {
				Utils.showException(ex);
			}
		});
		
	}
}
