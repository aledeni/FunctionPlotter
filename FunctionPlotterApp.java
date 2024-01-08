import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FunctionPlotterApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		stage.setTitle("Function Plotter");
		
		// Assi x e y
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("x");
		yAxis.setLabel("y");
		
		// Grafico a linee
		LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
		lineChart.setId("lineChart");
		lineChart.setTitle("Function Plotter");
		
		// ComboBox per le funzioni
		ComboBox<String> functionComboBox = new ComboBox<>();
		functionComboBox.setId("functionComboBox");
		functionComboBox.getItems().addAll("y = x^2", "y = sin(x)", "y = cos(x)", "y = e^x");
		functionComboBox.setValue("y = x^2");
		functionComboBox.setPrefSize(180, 50);
		functionComboBox.setStyle("-fx-font: 20px \"Serif\";");
		functionComboBox.setOnAction(e -> plotFunction(lineChart, functionComboBox.getValue()));
		
		// Layout
		BorderPane root = new BorderPane();
		root.setTop(functionComboBox);
		root.setCenter(lineChart);
		Scene scene = new Scene(root, 800, 600);
		stage.setScene(scene);
		stage.show();
		
		// Disegna la funzione iniziale
		plotFunction(lineChart, functionComboBox.getValue());
	}

	private void plotFunction(LineChart<Number, Number> chart, String function) {
		XYChart.Series<Number, Number> series = new XYChart.Series<>();
		series.setName(function);

		for (double x = -10; x <= 10; x += 0.1) {
			double y = calculateY(x, function);
			series.getData().add(new XYChart.Data<>(x, y));
		}

		chart.getData().clear();
		chart.getData().add(series);
	}

	private double calculateY(double x, String function) {
		switch (function) {
			case "y = x^2":
				return x * x;
			case "y = sin(x)":
				return Math.sin(x);
			case "y = cos(x)":
				return Math.cos(x);
			case "y = e^x":
				return Math.exp(x);
			default:
				return 0.0;
		}
	}
}