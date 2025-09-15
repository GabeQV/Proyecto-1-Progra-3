package hospital.presentation.dashboard;

import hospital.logic.Receta;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class LineChart extends JPanel {
    private DefaultCategoryDataset dataset;
    private ChartPanel chartPanel;

    public LineChart() {
        dataset = new DefaultCategoryDataset();
        JFreeChart chart = ChartFactory.createLineChart(
                "Recetas por Medicamento y Mes", "Mes", "Cantidad", dataset
        );
        CategoryPlot plot = chart.getCategoryPlot();
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setDefaultShapesVisible(true);

        chartPanel = new ChartPanel(chart);
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);
    }


    public void setData(List<Receta> recetas) {
        dataset.clear();

        Map<String, Map<YearMonth, Integer>> counts = new HashMap<>();
        for (Receta r : recetas) {
            String med = r.getMedicamento() != null ? r.getMedicamento().getNombre() : "SIN MEDICAMENTO";
            LocalDate fecha = r.getFecha();
            if (fecha == null) continue;
            YearMonth ym = YearMonth.from(fecha);

            counts.computeIfAbsent(med, k -> new HashMap<>());
            Map<YearMonth, Integer> medMap = counts.get(med);
            medMap.put(ym, medMap.getOrDefault(ym, 0) + 1);
        }


        for (Map.Entry<String, Map<YearMonth, Integer>> entry : counts.entrySet()) {
            String med = entry.getKey();
            for (Map.Entry<YearMonth, Integer> ymEntry : entry.getValue().entrySet()) {
                String mes = ymEntry.getKey().toString();
                dataset.addValue(ymEntry.getValue(), med, mes);
            }
        }
    }
}