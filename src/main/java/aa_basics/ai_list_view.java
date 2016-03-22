package aa_basics;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

class ai_list_view {
    public static class ListViewDemo extends Application {

        @Override
        public void start(Stage primaryStage) {

            BorderPane root = new BorderPane();

            ObservableList<String> shoppingListItems = FXCollections.observableArrayList();
            shoppingListItems.add("Milk");
            shoppingListItems.add("Sugar");
            shoppingListItems.add("Bread");

            SortedList<String> sortedShoppingListItems = new SortedList<>(shoppingListItems);
            shoppingListItems.add("Eggs");

            ListView<String> listView = new ListView<>(sortedShoppingListItems);
            root.setCenter(listView);

            Scene mainScene = new Scene(root);
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);
            primaryStage.setScene(mainScene);
            primaryStage.show();

            // -- step 1 sort buttons
            Button ascendingButton = new Button("Sort Ascending");
            ascendingButton.setOnAction(e -> sortedShoppingListItems.setComparator((lhs, rhs) -> lhs.compareTo(rhs)));

            Button descendingButton = new Button("Sort Descending");
            descendingButton.setOnAction(e -> sortedShoppingListItems.setComparator((lhs, rhs) -> rhs.compareTo(lhs)));

            root.setBottom(new HBox(ascendingButton, descendingButton));

            // -- step 2 add entry
            TextField newItemTextField = new TextField();
            Button addButton = new Button("Add");
            addButton.setOnAction(e -> {
                shoppingListItems.add(newItemTextField.getText());
                newItemTextField.clear();
            });

            HBox topBar = new HBox(newItemTextField, addButton);
            root.setTop(topBar);

            // -- step 3 remove entry
            Button removeButton = new Button("Remove");
            removeButton.setOnAction(e -> {
                shoppingListItems.removeAll(listView.getSelectionModel().getSelectedItems());
            });

            topBar.getChildren().add(removeButton);

            // -- step 4 multi select
            listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        }

    }
}
