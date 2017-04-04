package egger.software.baylandtag.am_tree_view_browser;

import javafx.scene.Parent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;

public class BrowseUi {

	TreeView<Object> browseTreeView = new TreeView<Object>();
	MemberUi memberUi = new MemberUi();
	
	public Parent create() {

		HBox browsePane = new HBox();

		TreeItem<Object> browseTreeRoot = new TreeItem<Object>("Bayerischer Landtag");
		browseTreeView.setRoot(browseTreeRoot);

		browsePane.getChildren().add(browseTreeView);
		browsePane.getChildren().add(memberUi.create());

		return browsePane;

	}

	public TreeView<Object> getBrowseTreeView() {
		return browseTreeView;
	}

	public MemberUi getMemberUi() {
		return memberUi;
	}
}
