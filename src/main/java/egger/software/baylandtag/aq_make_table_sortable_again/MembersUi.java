package egger.software.baylandtag.aq_make_table_sortable_again;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MembersUi {

	private TableView<Member> membersTable = new TableView<>(); 
	private TextField filterTextField = new TextField();
	private Button addButton = new Button("Add");
	
	private TableColumn<Member, String> titleCol = new TableColumn<>("Titel");
	private TableColumn<Member, String> surnameCol = new TableColumn<>("Name");
	private TableColumn<Member, String> forenameCol = new TableColumn<>("Vorname");
	
	public Parent create() {

		BorderPane membersPane = new BorderPane();
		
		surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
		forenameCol.setCellValueFactory(new PropertyValueFactory<>("forename"));	
		titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		
		membersTable.getColumns().add(surnameCol);
		membersTable.getColumns().add(forenameCol);
		membersTable.getColumns().add(titleCol);
		
		surnameCol.setSortType(TableColumn.SortType.ASCENDING);
		membersTable.getSortOrder().add(surnameCol);
		
		membersPane.setCenter(membersTable);

		filterTextField.setPromptText("Filter");
		membersPane.setTop(new HBox(filterTextField, addButton));
		
		return membersPane;
		
	}
	
	public TableView<Member> getMembersTable() {
		return membersTable;
	}
	
	public TextField getFilterTextField() {
		return filterTextField;
	}

	public Button getAddButton() {
		return addButton;
	}

	public TableColumn<Member, String> getTitleCol() {
		return titleCol;
	}

	public TableColumn<Member, String> getSurnameCol() {
		return surnameCol;
	}

	public TableColumn<Member, String> getForenameCol() {
		return forenameCol;
	}

}
