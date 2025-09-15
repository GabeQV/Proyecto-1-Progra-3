package hospital.presentation.dashboard;

import com.github.lgooddatepicker.components.DatePicker;
import hospital.logic.Medicamento;
import hospital.logic.Receta;
import hospital.logic.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class DashboardView extends JPanel implements PropertyChangeListener  {

    private JPanel DashboardPanel;
    private JComboBox medicamentosComboBox;
    private JButton filtrarButton1;
    private JButton addButton;
    private JTable medicamentosTable;
    private JPanel panel2;
    private DatePicker hastaDatePicker;
    private DatePicker desdeDatePicker;
    private JPanel lineChartPanel;
    private JPanel pieChartPanel;
    LineChart lineChart;
    PieChart pieChart;

    Controller controller;
    Model model;

    // NUEVO: lista de medicamentos seleccionados para mostrar
    private List<String> medicamentosSeleccionados = new ArrayList<>();

    public JPanel getPrescripcionPanel() {return DashboardPanel;}

    public DashboardView() {
        lineChart = new LineChart();
        pieChart = new PieChart();

        lineChartPanel.setLayout(new BorderLayout());
        lineChartPanel.add(lineChart, BorderLayout.CENTER);

        pieChartPanel.setLayout(new BorderLayout());
        pieChartPanel.add(pieChart, BorderLayout.CENTER);
        List<Medicamento> medicamentos = Service.instance().findAllMedicamentos();
        String[] medicamentosStr = new String[medicamentos.size()];
        int i = 0;
        while (i < medicamentos.size()) {
            medicamentosStr[i] = medicamentos.get(i).getNombre();
            i+=1;
        }
        medicamentosComboBox.setModel(new javax.swing.DefaultComboBoxModel(medicamentosStr));

        // Listener para botón ADD
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccionado = (String) medicamentosComboBox.getSelectedItem();
                if (seleccionado != null && !medicamentosSeleccionados.contains(seleccionado)) {
                    medicamentosSeleccionados.add(seleccionado);
                }
                actualizarVista();
            }
        });

        filtrarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarVista();
            }
        });
    }

    private void actualizarVista() {
        LocalDate desde = desdeDatePicker.getDate();
        LocalDate hasta = hastaDatePicker.getDate();
        List<Receta> filtradas = controller.findRecetasMultiple(desde, hasta, medicamentosSeleccionados);
        model.setList(filtradas);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.LIST:
                int[] cols = {TableModel.MEDICAMENTO, TableModel.MES, TableModel.CANTIDAD};
                medicamentosTable.setModel(new TableModel(cols, model.getList()));
                lineChart.setData(model.getList());
                pieChart.setData(model.getList());
                break;
            case Model.CURRENT:

                break;
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }
}