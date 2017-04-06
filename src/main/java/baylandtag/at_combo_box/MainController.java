package baylandtag.at_combo_box;

import javafx.beans.binding.Bindings;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;

public class MainController {

	private Tab loginTab;
	private Tab membersTab;
	private Tab browseTab;
	private MenuItem addMemberMenuItem;
	private MenuItem deleteMemberMenuItem;
	private MenuItem editMemberMenuItem;

	private Database database;

	public MainController(MainUi ui) {

		loginTab = ui.getLoginTab();
		membersTab = ui.getMembersTab();
		browseTab = ui.getBrowseTab();
		
		addMemberMenuItem = ui.getAddMemberMenuItem();
		deleteMemberMenuItem = ui.getDeleteMemberMenuItem();
		editMemberMenuItem = ui.getEditMemberMenuItem();

	}

	public void initialize() {

		addMemberMenuItem.disableProperty().bind(Bindings.not(membersTab.selectedProperty()));
		deleteMemberMenuItem.disableProperty().bind(Bindings.not(membersTab.selectedProperty()));
		editMemberMenuItem.disableProperty().bind(Bindings.not(membersTab.selectedProperty()));

	}

	public void setLoginTabContent(Parent content) {
		loginTab.setContent(content);
	}

	public void setMembersTabContent(Parent content) {
		membersTab.setContent(content);
	}

	public void setBrowseTabContent(Parent content) {
		browseTab.setContent(content);
	}

	public void setDatabase(Database database) {

		if (this.database != null)
			throw new IllegalStateException("Database already set for MainController.");

		this.database = database;

		membersTab.disableProperty().bind(Bindings.not(database.connectedProperty()));
		browseTab.disableProperty().bind(membersTab.disabledProperty());
	}

	public void setAddMemberAction(Runnable action) {
		addMemberMenuItem.setOnAction(event -> {
			action.run();
		});
	}

	public void setDeleteMemberAction(Runnable action) {
		deleteMemberMenuItem.setOnAction(event -> {
			action.run();
		});		
	}

	public void setEditMemberAction(Runnable action) {
		editMemberMenuItem.setOnAction(event -> {
			action.run();
		});		
	}

}
