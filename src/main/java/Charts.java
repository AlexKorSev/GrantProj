import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;

import java.awt.*;
import java.util.Map;

public class Charts {

    public static void showResults(Map<Integer, Double> data) {
        XYDataset dataset = createDataset(data);
        JFreeChart chart = createChart(dataset);
        displayChart(chart);
    }

    public static XYDataset createDataset(Map<Integer, Double> data) {
        TimeSeries series = new TimeSeries("Среднее количество рабочих мест");

        for (int year : data.keySet()) {
            series.add(new Year(year), data.get(year));
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    public static JFreeChart createChart(XYDataset dataSet) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Среднее количество рабочих мест по годам",
                "Год", "Среднее количество рабочих мест",
                dataSet,  true, false, false);

        // Настройка внешнего вида
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.GRAY);
        plot.setRangeGridlinePaint(Color.GRAY);

        // Настройка линии графика
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesShapesVisible(0, true);
        plot.setRenderer(renderer);

        return chart;
    }

    public static void displayChart(JFreeChart chart) {
        ApplicationFrame frame = new ApplicationFrame("Статистика рабочих мест");
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

}
