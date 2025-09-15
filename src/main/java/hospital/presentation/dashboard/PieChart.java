package hospital.presentation.dashboard;

import hospital.logic.Receta;
import hospital.logic.Service;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PieChart extends JPanel {
    private DefaultPieDataset pieDataset;
    private ChartPanel chartPanel;

    public PieChart() {
        pieDataset = new DefaultPieDataset();
        JFreeChart chart = ChartFactory.createPieChart(
                "Estados de las Recetas", pieDataset, true, true, false
        );
        chartPanel = new ChartPanel(chart);
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);
    }

    /**
     * Updates the pie chart with the given list of recetas.
     * Groups by estado and counts.
     */
    public void setData(List<Receta> recetas) {
        Map<String, Integer> estadoCounts = new HashMap<>();
        for (Receta r : recetas) {
            String estado = r.getEstado();
            if (estado == null) estado = "SIN ESTADO";
            estadoCounts.put(estado, estadoCounts.getOrDefault(estado, 0) + 1);
        }

        pieDataset.clear();
        for (Map.Entry<String, Integer> entry : estadoCounts.entrySet()) {
            pieDataset.setValue(entry.getKey(), entry.getValue());
        }
    }
}