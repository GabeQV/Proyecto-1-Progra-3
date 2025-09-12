package hospital.presentation.dashboard;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class PieChart extends JFrame {
    public PieChart() {
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        // Estoy probando a ver como sirve esto
        pieDataset.setValue("PROCESO", 4);
        pieDataset.setValue("LISTA", 4);
        pieDataset.setValue("ENTREGADA", 3);
        pieDataset.setValue("CONFECCIONADA", 3);

        JFreeChart chart = ChartFactory.createPieChart(
                "Recetas", pieDataset, true, true, false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);

        setTitle("Recetas Pie Chart Example");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
