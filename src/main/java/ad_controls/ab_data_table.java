package ad_controls;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

class ab_data_table {

    public static class Flight {
        private String number;

        public Flight(String number) {
            this.number = number;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }

    public static class DataTableExample extends Application {

        @Override
        public void start(Stage primaryStage) {
            BorderPane root = new BorderPane();

            TableView<Flight> flightsTable = new TableView<>();
            TableColumn<Flight, String> flightNumberCol = new TableColumn<>("Flight Number");
            flightsTable.getColumns().add(flightNumberCol);

            root.setCenter(flightsTable);

            Scene mainScene = new Scene(root);
            primaryStage.setScene(mainScene);
            primaryStage.show();

            // -- step 1 add flights to the table
            ObservableList<Flight> flights = FXCollections.observableArrayList();
            flights.add(new Flight("OS201"));
            flights.add(new Flight("LH2123"));
            flightsTable.setItems(flights);

            // -- step 2 show flight number
            flightNumberCol.setCellValueFactory(new PropertyValueFactory<>("number"));

            // -- step 3 add / remove entry
            Button removeButton = new Button("Remove Flight");
            TextField number = new TextField();
            Button addButton = new Button("Add Flight");
            ToolBar toolbar = new ToolBar(removeButton, number, addButton);
            root.setTop(toolbar);

            removeButton.setOnMouseClicked(event -> {
                Flight selectedFlight = flightsTable.getSelectionModel().getSelectedItem();
                if (selectedFlight != null) {
                    flights.remove(selectedFlight);
                }
            });

            addButton.setOnMouseClicked(event -> {
                Flight newFlight = new Flight(number.getText());
                flights.add(newFlight);
            });

            // -- step 4 observe selection changes
            flightsTable.getSelectionModel().selectedItemProperty().addListener((src, oldFlight, newFlight) -> {
                System.out.println("change");
                if (oldFlight != null)
                    System.out.println("old: " + oldFlight.getNumber());
                if (newFlight != null)
                    System.out.println("new: " + newFlight.getNumber());
            });

            // TODO show editing!

            // -- step 6 show / hide columns
            flightsTable.setTableMenuButtonVisible(true);
        }


    }
}
