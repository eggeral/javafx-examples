package baylandtag.au_display_image;

import java.io.IOException;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;

public class MembersController {

	private TableView<Member> membersTable;
	private TextField filterTextField;
	private Button addButton;

	private TableColumn<Member, String> titleCol;
	private TableColumn<Member, String> surnameCol;
	private TableColumn<Member, String> forenameCol;
	private TableColumn<Member, Confession> confessionCol;
	private TableColumn<Member, MaritalStatus> maritalStatusCol;

	private ObservableList<Member> members;
	private ObservableList<MaritalStatus> maritalStatus;
	private FilteredList<Member> filteredMembers; // we can not add values to a
													// filtered List
	private Database database;

	private Parent memberUi;
	private MemberController memberController = new MemberController();

	public MembersController(MembersUi ui) {
		membersTable = ui.getMembersTable();
		filterTextField = ui.getFilterTextField();
		titleCol = ui.getTitleCol();
		surnameCol = ui.getSurnameCol();
		forenameCol = ui.getForenameCol();
		confessionCol = ui.getConfessionCol();
		maritalStatusCol = ui.getMaritalStatusCol();
		addButton = ui.getAddButton();
	}

	public void initialize() throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(Utils.getResourceInCurrentPackage("MemberUi.fxml"));
		fxmlLoader.setController(memberController);
		memberUi = fxmlLoader.load();

		filterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredMembers.setPredicate(member -> {
				if (newValue == null || newValue.isEmpty())
					return true;
				if (member.getSurname().toLowerCase().startsWith(newValue.toLowerCase()))
					return true;
				return false;
			});
		});

		membersTable.setEditable(true);

		titleCol.setCellFactory(TextFieldTableCell.forTableColumn());
		surnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		forenameCol.setCellFactory(TextFieldTableCell.forTableColumn());

		titleCol.setOnEditCommit(event -> {
			Member member = event.getRowValue();
			member.setTitle(event.getNewValue());
			database.updateMember(member);
		});

		surnameCol.setOnEditCommit(event -> {
			Member member = event.getRowValue();
			member.setSurname(event.getNewValue());
			database.updateMember(member);
		});

		forenameCol.setOnEditCommit(event -> {
			Member member = event.getRowValue();
			member.setForename(event.getNewValue());
			database.updateMember(member);
		});

		confessionCol.setOnEditCommit(event -> {
			Member member = event.getRowValue();
			member.setConfession(event.getNewValue());
			database.updateMember(member);
		});

		maritalStatusCol.setOnEditCommit(event -> {
			Member member = event.getRowValue();
			member.setMaritalStatus(event.getNewValue());
			database.updateMember(member);
		});

		ContextMenu contextMenu = new ContextMenu();
		MenuItem delete = new MenuItem();
		MenuItem edit = new MenuItem();

		contextMenu.getItems().add(edit);
		contextMenu.getItems().add(delete);

		contextMenu.setOnShowing(event -> {
			Member selected = membersTable.getSelectionModel().getSelectedItem();
			if (selected != null) {
				edit.setText(selected.getSurname() + " " + selected.getForename() + " bearbeiten");
				delete.setText(selected.getSurname() + " " + selected.getForename() + " löschen");
			}
		});

		edit.setOnAction(event -> {
			editSelectedMember();
		});

		delete.setOnAction(event -> {
			deleteSelectedMember();
		});

		membersTable.setContextMenu(contextMenu);

	}

	public void setDatabase(Database database) {

		if (this.database != null)
			throw new IllegalStateException("Database already set for LoginController.");

		this.database = database;

		database.connectedProperty().addListener((observable, oldValue, newValue) -> {

			if (newValue == true) {
				members = FXCollections.observableList(database.getAllMembers());

				ObservableList<Confession> confessions = FXCollections
						.observableArrayList(database.getAllConfessions());
				confessionCol.setCellFactory(ChoiceBoxTableCell.forTableColumn(new StringConverter<Confession>() {

					@Override
					public String toString(Confession confession) {
						if (confession == null)
							return "";
						return confession.getName();
					}

					@Override
					public Confession fromString(String name) {
						return new Confession(-1, name);
					}

				}, confessions));

				memberController.setConfessions(confessions);

				maritalStatus = FXCollections.observableArrayList(database.getAllMaritalStatus());

				memberController.setMaritalStatus(maritalStatus);
				maritalStatusCol.setCellFactory(ComboBoxTableCell.forTableColumn(new StringConverter<MaritalStatus>() {

					@Override
					public String toString(MaritalStatus confession) {
						if (confession == null)
							return "";
						return confession.getName();
					}

					@Override
					public MaritalStatus fromString(String name) {
						return new MaritalStatus(-1, name);
					}

				}, maritalStatus));

				addButton.setOnAction(event -> {
					Member member = new Member(-1, "", "", "", confessions.get(0), maritalStatus.get(0));
					database.addMember(member);
					members.add(member);
					membersTable.getSelectionModel().select(member);
					membersTable.scrollTo(member);
					membersTable.requestFocus();
				});

				membersTable.setRowFactory(tableView -> new TableRow<Member>() {

					@Override
					public void updateItem(Member member, boolean empty) {
						super.updateItem(member, empty);
						if (empty)
							return;

						Image image = database.getImage(member.getId());
						if (image == null)
							return;

						Tooltip tooltip = new Tooltip();
						ImageView imageView = new ImageView(image);
						tooltip.setGraphic(imageView);
						setTooltip(tooltip);
					}

				});

			} else {
				members = FXCollections.observableArrayList();
			}

			filteredMembers = new FilteredList<>(members);
			SortedList<Member> sortedMembers = new SortedList<>(filteredMembers);

			sortedMembers.comparatorProperty().bind(membersTable.comparatorProperty());
			membersTable.setItems(sortedMembers);

		});

	}

	public void addMember() {

		Dialog<Member> dialog = new Dialog<>();
		memberController.setMember(null);

		dialog.getDialogPane().setContent(memberUi);
		dialog.setResultConverter(dialogButton -> {

			if (dialogButton == ButtonType.OK)
				return memberController.getMember();

			return null;

		});

		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		dialog.showAndWait().ifPresent(member -> {
			try {
				if (member.getMaritalStatus().getId() < 0) {
					if (!addNewMaritalStatus(member.getMaritalStatus()))
						return;
				}
				database.addMember(member);
				members.add(member);
				membersTable.getSelectionModel().select(member);
				membersTable.scrollTo(member);
			} catch (DatabaseException ex) {
				Utils.showException(ex);
			}
		});

	}

	public void deleteSelectedMember() {
		Member member = membersTable.getSelectionModel().getSelectedItem();

		if (member == null) {
			Alert alert = new Alert(Alert.AlertType.WARNING,
					"Löschen kann nicht ausgeführt werden weil kein Abgeordneter ausgewählt wurde.");
			alert.setTitle("Achtung");
			alert.setHeaderText("Kein Abgeordneter ausgewählt");
			alert.show();
			return;
		}

		try {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Wollen sie den Abgeordneten " + member.getSurname()
					+ " " + member.getForename() + " wirklich löschen?");
			alert.setTitle("Bitte bestätigen");
			alert.setHeaderText("Abgeordneten löschen");
			alert.showAndWait().ifPresent(buttonType -> {
				if (buttonType == ButtonType.OK) {
					database.deleteMember(member);

					// workaround for a JavaFX bug. SortedList gets out of sync
					// when removing items.
					ObservableList<Member> tmp = FXCollections.observableArrayList(members);
					members.clear();
					members.addAll(tmp);
					members.remove(member);
				}
			});

		} catch (DatabaseException ex) {
			Utils.showException(ex);
		}
	}

	public void editSelectedMember() {
		Member member = membersTable.getSelectionModel().getSelectedItem();

		if (member == null) {
			Alert alert = new Alert(Alert.AlertType.WARNING,
					"Bearbeiten kann nicht ausgeführt werden weil kein Abgeordneter ausgewählt wurde.");
			alert.setTitle("Achtung");
			alert.setHeaderText("Kein Abgeordneter ausgewählt");
			alert.show();
			return;
		}

		member.setImage(database.getImage(member.getId()));
		memberController.setMember(member);

		Dialog<Member> dialog = new Dialog<>();
		dialog.getDialogPane().setContent(memberUi);
		dialog.setResultConverter(dialogButton -> {

			if (dialogButton == ButtonType.OK)
				return memberController.getMember();

			return null;

		});

		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		dialog.showAndWait().ifPresent(editedMember -> {
			try {

				if (editedMember.getMaritalStatus().getId() < 0) {
					if (!addNewMaritalStatus(editedMember.getMaritalStatus()))
						return;
				}

				database.updateMember(editedMember);

				member.setId(editedMember.getId());
				member.setSurname(editedMember.getSurname());
				member.setForename(editedMember.getForename());
				member.setTitle(editedMember.getTitle());
				member.setConfession(editedMember.getConfession());
				member.setMaritalStatus(editedMember.getMaritalStatus());
				member.setImage(editedMember.getImage());

				membersTable.refresh();
			} catch (DatabaseException ex) {
				Utils.showException(ex);
			}
		});
	}

	private boolean addNewMaritalStatus(MaritalStatus status) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Neuen Familienstand " + status.getName() + " anlegen?");
		alert.setTitle("Achtung");
		alert.setHeaderText("Neuer Familienstand");
		Optional<ButtonType> confirmed = alert.showAndWait();
		if (confirmed.isPresent()) {
			database.addMaritalStatus(status);
			maritalStatus.add(status);
			return true;
		} else {
			return false;
		}
	}
}
