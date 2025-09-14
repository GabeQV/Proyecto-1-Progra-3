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

public class PieChart extends JFrame {
    public PieChart() {
        DefaultPieDataset pieDataset = new DefaultPieDataset();

        List<Receta> recetas = Service.instance().findAllRecetas();
        Map<String, Integer> estadoCounts = new HashMap<>();
        for (Receta r : recetas) {
            String estado = r.getEstado();
            if (estado == null) estado = "SIN ESTADO";
            estadoCounts.put(estado, estadoCounts.getOrDefault(estado, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : estadoCounts.entrySet()) {
            pieDataset.setValue(entry.getKey(), entry.getValue());
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Estados de las Recetas", pieDataset, true, true, false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);

        setTitle("Estados de las Recetas");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
