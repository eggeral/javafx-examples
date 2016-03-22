package ad_controls;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


class ad_line_chart {

    public static class LineChartExample extends Application {

        @Override
        public void start(Stage primaryStage) {
            BorderPane root = new BorderPane();

            TableView<XYChart.Data<Number, Number>> dataTable = new TableView<>();

            TableColumn<XYChart.Data<Number, Number>, Number> xCol = new TableColumn<>("X Value");
            xCol.setCellValueFactory(new PropertyValueFactory<>("xValue"));
            dataTable.getColumns().add(xCol);

            TableColumn<XYChart.Data<Number, Number>, Number> yCol = new TableColumn<>("Y Value");
            yCol.setCellValueFactory(new PropertyValueFactory<>("yValue"));
            dataTable.getColumns().add(yCol);

            ObservableList<XYChart.Data<Number, Number>> data = FXCollections.observableArrayList();
            data.add(new XYChart.Data<>(10, 2.0));
            data.add(new XYChart.Data<>(20, 4.0));
            data.add(new XYChart.Data<>(30, 3.5));
            data.add(new XYChart.Data<>(40, 4.5));
            dataTable.setItems(data);

            final NumberAxis xAxis = new NumberAxis();
            final NumberAxis yAxis = new NumberAxis();

            xAxis.setLabel("Time Stamp");

            LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

            lineChart.setTitle("Data");
            XYChart.Series<Number, Number> series = new XYChart.Series<>("Data", data);

            lineChart.getData().add(series);
            root.setCenter(new HBox(dataTable, lineChart));
            Scene mainScene = new Scene(root);
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);
            primaryStage.setScene(mainScene);
            primaryStage.show();

            // -- step 1 add remove values
            TextField xValue = new TextField();
            TextField yValue = new TextField();

            Button addButton = new Button("Add");
            addButton.setOnAction(event -> {
                double x = Double.parseDouble(xValue.getText());
                double y = Double.parseDouble(yValue.getText());
                data.add(new XYChart.Data<>(x, y));
            });

            Button removeButton = new Button("Remove");
            removeButton.setOnAction(event -> {
                XYChart.Data<Number, Number> selected = dataTable.getSelectionModel().getSelectedItem();
                data.remove(selected);
            });

            ToolBar toolbar = new ToolBar(xValue, yValue, addButton, removeButton);

            root.setTop(toolbar);

        }
    }
}
