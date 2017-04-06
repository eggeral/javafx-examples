package baylandtag.ai_style_and_refactor_the_app;

import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class BaylandtagUi {

	private TableView<Member> memberTable = new TableView<>(); 
	
	public Parent create() {

		BorderPane baylandtagPane = new BorderPane();
		
		TableColumn<Member, String> surnameCol = new TableColumn<>("Name");
		surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
		surnameCol.getStyleClass().add("surname");
		
		TableColumn<Member, String> forenameCol = new TableColumn<>("Vorname");
		forenameCol.setCellValueFactory(new PropertyValueFactory<>("forename"));
		forenameCol.getStyleClass().add("forename");
		
		TableColumn<Member, String> titleCol = new TableColumn<>("Titel");
		titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		
		memberTable.getColumns().add(surnameCol);
		memberTable.getColumns().add(forenameCol);
		memberTable.getColumns().add(titleCol);
		
		baylandtagPane.setCenter(memberTable);

		return baylandtagPane;
		
	}
	
	public TableView<Member> getMemberTable() {
		return memberTable;
	}


}
