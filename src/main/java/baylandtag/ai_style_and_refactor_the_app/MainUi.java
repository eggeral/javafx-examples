package baylandtag.ai_style_and_refactor_the_app;

import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainUi {
	
	private TabPane tabPane = new TabPane();
	private Tab loginTab = new Tab();
	private Tab baylandtagTab = new Tab();
	
	public Parent create() {
		
		loginTab.setText("Login");
		loginTab.setClosable(false);

		baylandtagTab.setText("Landtag");
		baylandtagTab.setClosable(false);

		tabPane.getTabs().addAll(loginTab, baylandtagTab);
		
		return tabPane;
		
	}

	public Tab getLoginTab() {
		return loginTab;
	}

	public Tab getBaylandtagTab() {
		return baylandtagTab;
	}
	
}
