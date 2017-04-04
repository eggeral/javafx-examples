package egger.software.baylandtag.ag_dummy_person_table;

import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class BaylandtagUi {

	private TableView<Member> memberTable = new TableView<>(); 
	
	public Parent create() {

		GridPane baylandtagPane = new GridPane();

		
		TableColumn<Member, String> surnameCol = new TableColumn<>("Name");
		surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));

		TableColumn<Member, String> forenameCol = new TableColumn<>("Vorname");
		forenameCol.setCellValueFactory(new PropertyValueFactory<>("forename"));
		
		TableColumn<Member, String> titleCol = new TableColumn<>("Titel");
		titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		
		memberTable.getColumns().add(surnameCol);
		memberTable.getColumns().add(forenameCol);
		memberTable.getColumns().add(titleCol);

		baylandtagPane.add(memberTable, 0, 0);

		return baylandtagPane;
		
	}
	
	public TableView<Member> getMemberTable() {
		return memberTable;
	}


}
