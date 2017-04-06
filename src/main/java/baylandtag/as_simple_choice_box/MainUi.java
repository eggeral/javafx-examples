package baylandtag.as_simple_choice_box;

import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class MainUi {
	
	private Tab loginTab = new Tab();
	private Tab membersTab = new Tab();
	private Tab browseTab = new Tab();
	
	private MenuItem addMemberMenuItem = new MenuItem("Abgeordneten hinzufügen");
	private MenuItem deleteMemberMenuItem = new MenuItem("Abgeordneten löschen");
	private MenuItem editMemberMenuItem = new MenuItem("Abgeordneten bearbeiten");

	public Parent create() {
		
		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("Datei");
		Menu editMenu = new Menu("Bearbeiten");
		Menu helpMenu = new Menu("Hilfe");
		
		editMenu.getItems().add(addMemberMenuItem);
		editMenu.getItems().add(deleteMemberMenuItem);
		editMenu.getItems().add(editMemberMenuItem);
		menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
		
		loginTab.setText("Login");
		loginTab.setClosable(false);

		membersTab.setText("Abgeordnete");
		membersTab.setClosable(false);

		browseTab.setText("Browse");
		browseTab.setClosable(false);

		TabPane tabPane = new TabPane(loginTab, membersTab, browseTab);
		
		return new VBox(menuBar, tabPane);
		
	}

	public Tab getLoginTab() {
		return loginTab;
	}

	public Tab getMembersTab() {
		return membersTab;
	}
	
	public Tab getBrowseTab() {
		return browseTab;
	}
	
	public MenuItem getAddMemberMenuItem() {
		return addMemberMenuItem;
	}
	
	public MenuItem getDeleteMemberMenuItem() {
		return deleteMemberMenuItem;
	}

	public MenuItem getEditMemberMenuItem() {
		return editMemberMenuItem;
	}

}
