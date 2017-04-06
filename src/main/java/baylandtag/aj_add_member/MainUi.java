package baylandtag.aj_add_member;

import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class MainUi {
	
	private Tab loginTab = new Tab();
	private Tab baylandtagTab = new Tab();
	private MenuItem addMemberMenuItem = new MenuItem("Abgeordneten hinzufügen");

	public Parent create() {
		
		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("Datei");
		Menu editMenu = new Menu("Bearbeiten");
		Menu helpMenu = new Menu("Hilfe");
		
		editMenu.getItems().add(addMemberMenuItem);
		menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
		
		loginTab.setText("Login");
		loginTab.setClosable(false);

		baylandtagTab.setText("Landtag");
		baylandtagTab.setClosable(false);

		TabPane tabPane = new TabPane(loginTab, baylandtagTab);
		
		return new VBox(menuBar, tabPane);
		
	}

	public Tab getLoginTab() {
		return loginTab;
	}

	public Tab getBaylandtagTab() {
		return baylandtagTab;
	}
	
	public MenuItem getAddMemberMenuItem() {
		return addMemberMenuItem;
	}
	
}
