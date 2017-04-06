package baylandtag.ah_fill_member_table_from_db;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class BaylandtagController {

	private TableView<Member> memberTable;
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
			ObservableList<Member> members;

			if (newValue == true)
				members = FXCollections.observableList(database.getAllMembers());
			else
				members = FXCollections.observableArrayList();

			memberTable.setItems(members);

		});
		// Does not work because the database is set if if no connection is
		// available
		// ObservableList<Member> members =
		// FXCollections.observableList(database.getAllMembers());
		// memberTable.setItems(members);

	}

}
