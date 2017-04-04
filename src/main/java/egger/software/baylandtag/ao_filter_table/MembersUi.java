package egger.software.baylandtag.ao_filter_table;

import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class MembersUi {

	private TableView<Member> memberTable = new TableView<>(); 
	private TextField filterTextField = new TextField();
	public Parent create() {

		BorderPane membersPane = new BorderPane();
		
		TableColumn<Member, String> surnameCol = new TableColumn<>("Name");
		surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));

		TableColumn<Member, String> forenameCol = new TableColumn<>("Vorname");
		forenameCol.setCellValueFactory(new PropertyValueFactory<>("forename"));
		
		TableColumn<Member, String> titleCol = new TableColumn<>("Titel");
		titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		
		memberTable.getColumns().add(surnameCol);
		memberTable.getColumns().add(forenameCol);
		memberTable.getColumns().add(titleCol);
		
		surnameCol.setSortType(TableColumn.SortType.ASCENDING);
		memberTable.getSortOrder().add(surnameCol);
		
		membersPane.setCenter(memberTable);

		filterTextField.setPromptText("Filter");
		membersPane.setTop(filterTextField);
		
		return membersPane;
		
	}
	
	public TableView<Member> getMemberTable() {
		return memberTable;
	}
	
	public TextField getFilterTextField() {
		return filterTextField;
	}


}
