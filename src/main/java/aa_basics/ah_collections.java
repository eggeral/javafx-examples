package aa_basics;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.stage.Stage;

class ah_collections {

    public static class Collections extends Application {

        @Override
        public void start(Stage primaryStage) {

            // maps and sets are also available!
            ObservableList<String> shoppingListItems = FXCollections.observableArrayList();
            shoppingListItems.addListener((InvalidationListener) listener -> {
                System.out.println(listener);
            });

            // -- step 1
            shoppingListItems.add("item 1");

            // -- step 2
            // Using the invalidation listener is not very useful
            System.out.println("====");
            shoppingListItems = FXCollections.observableArrayList();
            shoppingListItems.addListener((ListChangeListener<String>) change -> {
                while (change.next()) {
                    if (change.wasPermutated()) {
                        for (int i = change.getFrom(); i < change.getTo(); ++i) {
                            System.out.println("Item permutated at index: " + i + " old index was: " + change.getPermutation(i));
                        }
                    } else if (change.wasUpdated()) {
                        for (int i = change.getFrom(); i < change.getTo(); ++i) {
                            System.out.println("Item changed at index: " + i + ", value: " + change.getList().get(i));
                        }
                    } else {
                        for (String removedItem : change.getRemoved()) {
                            System.out.println("Item removed: " + removedItem);
                        }

                        for (String addedItem : change.getAddedSubList()) {
                            System.out.println("Item added: " + addedItem);
                        }
                    }
                }
            });

            shoppingListItems.addAll("item 1", "item 2", "item 3", "item 4", "item 5");
            System.out.println("---");
            shoppingListItems.removeAll("item 2", "item 3");
            System.out.println("---");
            shoppingListItems.set(0, "item new"); // update is optional can also be remove/add
            System.out.println("---");
            SortedList<String> sortedShoppingListItems = new SortedList<String>(shoppingListItems);
            sortedShoppingListItems.addListener((ListChangeListener<String>) change -> {
                change.next();
                for (int i = change.getFrom(); i < change.getTo(); ++i) {
                    System.out.println("Sorted list item permutated at index: " + i + " old index was: " + change.getPermutation(i));
                }
            });
            sortedShoppingListItems.comparatorProperty().setValue((lhs, rhs) -> lhs.compareTo(rhs));

            Platform.exit();

        }


    }
}
