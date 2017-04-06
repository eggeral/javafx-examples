package baylandtag.an_fxml;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;

public class BrowseUi {

	private TreeView<Object> browseTreeView = new TreeView<Object>();
	private MemberController memberController;
	private final Parent memberUi;
	
	public BrowseUi() throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(Utils.getResourceInCurrentPackage("MemberUi.fxml"));
		memberUi = fxmlLoader.load();
		memberController  = (MemberController) fxmlLoader.getController();
			
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
