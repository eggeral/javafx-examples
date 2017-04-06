package baylandtag.aw_set_member_view_not_editable;

import javafx.beans.property.ReadOnlyObjectWrapper;
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
	private TableColumn<Member, Confession> confessionCol = new TableColumn<>("Konfession");
	private TableColumn<Member, MaritalStatus> maritalStatusCol = new TableColumn<>("Familienstand");

	public Parent create() {

		BorderPane membersPane = new BorderPane();

		surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
		forenameCol.setCellValueFactory(new PropertyValueFactory<>("forename"));
		titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		confessionCol.setCellValueFactory((cellDataFeatures) -> {
			return new ReadOnlyObjectWrapper<Confession>(cellDataFeatures.getValue().getConfession());
		});
		maritalStatusCol.setCellValueFactory((cellDataFeatures) -> {
			return new ReadOnlyObjectWrapper<MaritalStatus>(cellDataFeatures.getValue().getMaritalStatus());
		});

		membersTable.getColumns().add(surnameCol);
		membersTable.getColumns().add(forenameCol);
		membersTable.getColumns().add(titleCol);
		membersTable.getColumns().add(confessionCol);
		membersTable.getColumns().add(maritalStatusCol);

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

	public TableColumn<Member, Confession> getConfessionCol() {
		return confessionCol;
	}

	public TableColumn<Member, MaritalStatus> getMaritalStatusCol() {
		return maritalStatusCol;
	}

}
