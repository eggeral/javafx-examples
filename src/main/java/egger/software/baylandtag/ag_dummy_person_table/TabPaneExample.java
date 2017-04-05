package egger.software.baylandtag.ag_dummy_person_table;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class TabPaneExample extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		TabPane tabPane = new TabPane();
		Tab tab1 = new Tab();
		tab1.setText("Tab 1");
		tab1.setContent(new Label("TAB 1"));

		Tab tab2 = new Tab();
		tab2.setText("Tab 2");
		tab2.setContent(new Label("TAB 2"));
		tab2.setClosable(false);

		Tab tab3 = new Tab();
		tab3.setText("Tab 3");
		tab3.setContent(new Label("TAB 3"));
		tab3.setDisable(true);
				
		tabPane.getTabs().addAll(tab1, tab2, tab3);

		// -- step 2 select specific tab
		tabPane.getSelectionModel().select(tab2);

		Scene mainScene = new Scene(tabPane);
		primaryStage.setScene(mainScene);
		primaryStage.show();

	}

}
