package baylandtag.ag_dummy_person_table;

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

		ObservableList<Member> members = FXCollections.observableArrayList();
		members.add(new Member("Muster", "Max", "Dr."));
		members.add(new Member("Unbekannter", "Otto", null ));
		
		memberTable.setItems(members);
 
		
	}

}
