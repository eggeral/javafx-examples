package baylandtag.al_edit_member;

import javafx.beans.binding.Bindings;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;

public class MainController {

	private Tab loginTab;
	private Tab baylandtagTab;
	private MenuItem addMemberMenuItem;
	private MenuItem deleteMemberMenuItem;
	private MenuItem editMemberMenuItem;

	private Database database;

	public MainController(MainUi ui) {

		loginTab = ui.getLoginTab();
		baylandtagTab = ui.getBaylandtagTab();
		addMemberMenuItem = ui.getAddMemberMenuItem();
		deleteMemberMenuItem = ui.getDeleteMemberMenuItem();
		editMemberMenuItem = ui.getEditMemberMenuItem();

	}

	public void initialize() {

		addMemberMenuItem.disableProperty().bind(Bindings.not(baylandtagTab.selectedProperty()));
		deleteMemberMenuItem.disableProperty().bind(Bindings.not(baylandtagTab.selectedProperty()));
		editMemberMenuItem.disableProperty().bind(Bindings.not(baylandtagTab.selectedProperty()));

	}

	public void setLoginTabContent(Parent content) {
		loginTab.setContent(content);
	}

	public void setBaylandtagTabContent(Parent content) {
		baylandtagTab.setContent(content);
	}

	public void setDatabase(Database database) {

		if (this.database != null)
			throw new IllegalStateException("Database already set for MainController.");

		this.database = database;

		baylandtagTab.disableProperty().bind(Bindings.not(database.connectedProperty()));
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
