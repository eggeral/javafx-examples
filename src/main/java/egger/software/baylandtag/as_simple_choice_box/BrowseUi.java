package egger.software.baylandtag.as_simple_choice_box;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;

public class BrowseUi {

	private TreeView<Object> browseTreeView = new TreeView<Object>();
	private MemberController memberController = new MemberController();
	private final Parent memberUi;
	
	public BrowseUi() throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(Utils.getResourceInCurrentPackage("MemberUi.fxml"));
		fxmlLoader.setController(memberController);
		memberUi = fxmlLoader.load();
			
	}
	
	public Parent create() {

		HBox browsePane = new HBox();

		TreeItem<Object> browseTreeRoot = new TreeItem<Object>("Bayerischer Landtag");
		browseTreeView.setRoot(browseTreeRoot);

		browsePane.getChildren().add(browseTreeView);
		browsePane.getChildren().add(memberUi);

		return browsePane;

	}

	public TreeView<Object> getBrowseTreeView() {
		return browseTreeView;
	}

	public MemberController getMemberController() {
		return memberController;
	}
}
