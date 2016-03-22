package aa_basics;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

class af_properties {

    // Properties in JavaFx do not follow the Java Beans pattern.

    public static class ItemToBuy {
        StringProperty itemName;

        public final StringProperty itemNameProperty() {
            if (itemName == null) {
                itemName = new SimpleStringProperty();
            }
            return itemName;
        }

        public void setItemName(String itemName) {
            itemNameProperty().setValue(itemName);
        }

        public String getItemName() {
            return itemNameProperty().getValue();
        }

    }

    public static class Properties extends Application {

        @Override
        public void start(Stage primaryStage) {

            ItemToBuy itemToBuy = new ItemToBuy();
            itemToBuy.itemNameProperty().addListener((observable) -> {
                System.out.println("Observable invalidated: " + observable.toString());
                System.out.println("New value is: " + itemToBuy.getItemName());
            });

            itemToBuy.setItemName("Milk");

            //------

            BorderPane root = new BorderPane();
            TextField itemNameTextField = new TextField();

            itemToBuy.itemNameProperty().bind(itemNameTextField.textProperty());
            root.setCenter(itemNameTextField);

            Scene mainScene = new Scene(root);
            primaryStage.setScene(mainScene);
            primaryStage.show();

        }

    }
}
