package ae_packager;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PieChartExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        TableView<PieChart.Data> dataTable = new TableView<>();

        TableColumn<PieChart.Data, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        dataTable.getColumns().add(name);

        TableColumn<PieChart.Data, Integer> pieValue = new TableColumn<>("Value");
        pieValue.setCellValueFactory(new PropertyValueFactory<>("pieValue"));
        dataTable.getColumns().add(pieValue);

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        data.add(new PieChart.Data("a", 10));
        data.add(new PieChart.Data("b", 5));
        data.add(new PieChart.Data("c", 20));
        data.add(new PieChart.Data("d", 50));
        dataTable.setItems(data);

        PieChart pieChart = new PieChart(data);
        root.setCenter(new HBox(dataTable, pieChart));
        Scene mainScene = new Scene(root);
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setScene(mainScene);
        primaryStage.show();

        // -- step 1 add remove values
        TextField nameField = new TextField();
        TextField valueField = new TextField();

        Button addButton = new Button("Add");
        addButton.setOnAction(event -> {
            int value = Integer.parseInt(valueField.getText());
            data.add(new PieChart.Data(nameField.getText(), value));
        });

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(event -> {
            PieChart.Data selected = dataTable.getSelectionModel().getSelectedItem();
            data.remove(selected);
        });

        ToolBar toolbar = new ToolBar(nameField, valueField, addButton, removeButton);

        root.setTop(toolbar);

    }
}
