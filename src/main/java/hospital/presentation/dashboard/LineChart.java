package hospital.presentation.dashboard;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class LineChart extends JFrame {
    public LineChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // Tambien esto es de ejemplo, toy probando
        dataset.addValue(45, "Acetaminofen", "2025-8");
        dataset.addValue(40, "Acetaminofen", "2025-9");
        dataset.addValue(45, "Acetaminofen", "2025-10");
        dataset.addValue(23, "Amoxicilina", "2025-8");
        dataset.addValue(30, "Amoxicilina", "2025-9");
        dataset.addValue(25, "Amoxicilina", "2025-10");

        JFreeChart chart = ChartFactory.createLineChart(
                "Medicamentos", "Mes", "Cantidad", dataset
        );
        CategoryPlot plot = chart.getCategoryPlot();
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setDefaultShapesVisible(true);

        ChartPanel chartPanel = new ChartPanel(chart);
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);

        setTitle("Medicamentos Line Chart Example");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
