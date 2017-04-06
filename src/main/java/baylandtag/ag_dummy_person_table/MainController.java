package baylandtag.ag_dummy_person_table;

import javafx.beans.binding.Bindings;
import javafx.scene.Parent;
import javafx.scene.control.Tab;

public class MainController {

	private Tab loginTab;
	private Tab baylandtagTab;

	private Database database;

	public MainController(MainUi ui) {
		
		loginTab = ui.getLoginTab();
		baylandtagTab = ui.getBaylandtagTab();
		
	}

	public void initialize() {
		// placeholder
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

}
