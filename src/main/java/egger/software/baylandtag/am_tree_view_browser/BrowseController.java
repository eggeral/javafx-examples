package egger.software.baylandtag.am_tree_view_browser;

import java.util.List;

import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class BrowseController {

	private TreeView<Object> browseTreeView;
	private MemberUi memberUi;
	private Database database;

	public BrowseController(BrowseUi ui) {
		browseTreeView = ui.getBrowseTreeView();
		memberUi = ui.getMemberUi();
	}

	public void initialize() {

		class BrowseTreeCell extends TreeCell<Object> {
			@Override
			public void updateItem(Object item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setText(null);
					setGraphic(null);
					return;
				}
				if (item instanceof String) {
					setText(item.toString());
				} else if (item instanceof Party) {
					Party party = (Party) item;
					setText(party.getName());
				} else if (item instanceof Member) {
					Member member = (Member) item;
					setText(member.getSurname() + " " + member.getForename());
					setOnMouseClicked(event -> {
						setMemberUi(member);
					});
				}

			}

		}

		browseTreeView.setCellFactory((view) -> {
			return new BrowseTreeCell();
		});

	}

	public void setDatabase(Database database) {

		if (this.database != null)
			throw new IllegalStateException("Database already set for LoginController.");

		this.database = database;

		database.connectedProperty().addListener((observable, oldValue, newValue) -> {

			if (!newValue)
				return;

			browseTreeView.getRoot().getChildren().clear();
			List<Party> parties = database.getAllParties();
			for (Party party : parties) {
				TreeItem<Object> partyItem = new TreeItem<Object>(party);
				browseTreeView.getRoot().getChildren().add(partyItem);

				List<Member> members = database.getMembersOfParty(party);
				for (Member member : members) {
					partyItem.getChildren().add(new TreeItem<>(member));
				}
			}
		});

	}

	private void setMemberUi(Member member) {
		memberUi.getTitleTextField().setText(member.getTitle());
		memberUi.getSurnameTextField().setText(member.getSurname());
		memberUi.getForenameTextField().setText(member.getForename());
	}
}
