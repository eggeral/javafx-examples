package ad_controls;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

class ac_dialogs {

    public static class DialogsExample extends Application {

        @Override
        public void start(Stage primaryStage) {
            HBox root = new HBox();
            root.setSpacing(10);

            Button alertButton = new Button("Alert");
            alertButton.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Alert!!");
                alert.setTitle("Title");
                alert.setHeaderText("Header");
                alert.show();
            });

            root.getChildren().add(alertButton);

            Scene mainScene = new Scene(root);
            primaryStage.setWidth(800);
            primaryStage.setScene(mainScene);
            primaryStage.show();

            // - step 1 -- confirmation dialog
            Button confirmationButton = new Button("Confirm");
            confirmationButton.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.showAndWait().ifPresent(buttonType -> {
                            if (buttonType == ButtonType.CANCEL)
                                System.out.println("cancel");
                            else if (buttonType == ButtonType.OK)
                                System.out.println("ok");

                        }
                );
            });

            root.getChildren().add(confirmationButton);

            // - step 2 -- custom dialog
            Button customButton = new Button("Custom");
            customButton.setOnAction(event -> {
                Dialog<Flight> dialog = new Dialog();
                TextField number = new TextField();
                dialog.getDialogPane().setContent(new HBox(
                        new Label("Flight number"),
                        number
                ));
                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == ButtonType.OK) {
                        return new Flight(number.getText());
                    }
                    return null;
                });
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

                dialog.showAndWait().ifPresent(flight -> {
                            System.out.println("Flight: " + flight.number);
                        }
                );
            });

            root.getChildren().add(customButton);


        }


    }

    private static class Flight {
        public String number;

        public Flight(String number) {
            this.number = number;
        }
    }
}
