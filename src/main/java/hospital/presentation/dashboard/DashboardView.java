package hospital.presentation.dashboard;

import com.github.lgooddatepicker.components.DatePicker;
import hospital.logic.Medicamento;
import hospital.logic.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

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
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            medicamentosTable.

            }
        });
        filtrarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.recetasOrdenadas(
                        desdeDatePicker.getDate(),hastaDatePicker.getDate(),(String) medicamentosComboBox.getSelectedItem()
                );
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.LIST:
                int[] cols = {TableModel.MEDICAMENTO, TableModel.MES, TableModel.CANTIDAD};
                medicamentosTable.setModel(new TableModel(cols, model.getList()));

            case Model.CURRENT:
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
