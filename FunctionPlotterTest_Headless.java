import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.ComboBoxMatchers;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class FunctionPlotterTest_Headless {
	
	static {
		if(System.getProperty("os.name").toLowerCase().startsWith("win")) {
			System.loadLibrary("WindowsCodecs");
		}
	}
	
	@BeforeAll
	public static void setupHeadlessMode() {
		if(Boolean.getBoolean("headless")) {
			System.setProperty("testfx.robot", "glass");
			System.setProperty("testfx.headless", "true");
			System.setProperty("prism.order", "sw");
			System.setProperty("prism.text", "t2k");
			System.setProperty("java.awt.headless", "true");
		}
	}

	@Start
	public void onStart(Stage stage) {
		new FunctionPlotterApp().start(stage);
	}
	
	@Test
	public void testComboBoxElements() {
		// Verifica che le funzioni all'interno della ComboBox siano 4 e siano giuste
		FxAssert.verifyThat("#functionComboBox", ComboBoxMatchers.hasItems(4));
		FxAssert.verifyThat("#functionComboBox", ComboBoxMatchers.containsExactlyItems("y = x^2", "y = sin(x)", "y = cos(x)", "y = e^x"));
	}
	
	@Test
	public void testFunction1(FxRobot robot) {
		robot.clickOn("#functionComboBox");
		robot.clickOn("y = x^2");
		robot.sleep(1000);
		LineChart<Number, Number> lineChart = robot.lookup("#lineChart").query();
		XYChart.Series<Number, Number> series = lineChart.getData().get(0);
		
		// Verifica che sia stata tracciata una funzione
		assertThat(series.getData()).isNotEmpty();
		
		// Verifica che la funzione tracciata sia y = x^2
		double x, y;
		for(XYChart.Data<Number, Number> data : series.getData()) {
			x = data.getXValue().doubleValue();
			y = data.getYValue().doubleValue();
			assertThat(y).isEqualTo(x * x, Offset.offset(0.1));
		}
	}
	
	@Test
	public void testFunction2(FxRobot robot) {
		robot.clickOn("#functionComboBox");
		robot.clickOn("y = sin(x)");
		robot.sleep(1000);
		LineChart<Number, Number> lineChart = robot.lookup("#lineChart").query();
		XYChart.Series<Number, Number> series = lineChart.getData().get(0);
		
		// Verifica che sia stata tracciata una funzione
		assertThat(series.getData()).isNotEmpty();
		
		// Verifica che la funzione tracciata sia y = sin(x)
		double x, y;
		for(XYChart.Data<Number, Number> data : series.getData()) {
			x = data.getXValue().doubleValue();
			y = data.getYValue().doubleValue();
			assertThat(y).isEqualTo(Math.sin(x), Offset.offset(0.1));
		}
	}
	
	@Test
	public void testFunction3(FxRobot robot) {
		robot.clickOn("#functionComboBox");
		robot.clickOn("y = cos(x)");
		robot.sleep(1000);
		LineChart<Number, Number> lineChart = robot.lookup("#lineChart").query();
		XYChart.Series<Number, Number> series = lineChart.getData().get(0);
		
		// Verifica che sia stata tracciata una funzione
		assertThat(series.getData()).isNotEmpty();
		
		// Verifica che la funzione tracciata sia y = cos(x)
		double x, y;
		for(XYChart.Data<Number, Number> data : series.getData()) {
			x = data.getXValue().doubleValue();
			y = data.getYValue().doubleValue();
			assertThat(y).isEqualTo(Math.cos(x), Offset.offset(0.1));
		}
	}
	
	@Test
	public void testFunction4(FxRobot robot) {
		robot.clickOn("#functionComboBox");
		robot.clickOn("y = e^x");
		robot.sleep(1000);
		LineChart<Number, Number> lineChart = robot.lookup("#lineChart").query();
		XYChart.Series<Number, Number> series = lineChart.getData().get(0);
		
		// Verifica che sia stata tracciata una funzione
		assertThat(series.getData()).isNotEmpty();
		
		// Verifica che la funzione tracciata sia y = cos(x)
		double x, y;
		for(XYChart.Data<Number, Number> data : series.getData()) {
			x = data.getXValue().doubleValue();
			y = data.getYValue().doubleValue();
			assertThat(y).isEqualTo(Math.exp(x), Offset.offset(0.1));
		}
	}
	
}
