package hospital.presentation.dashboard;

import com.github.lgooddatepicker.components.DatePicker;
import hospital.logic.Medicamento;
import hospital.logic.Service;

import javax.swing.*;
import java.util.List;

public class DashboardView {

    private JPanel DashboardPanel;
    private JComboBox medicamentosComboBox;
    private JButton dobleCheckButton;
    private JButton checkButton;
    private JTable medicamentosTable;
    private JButton estoNoSeButton;
    private JButton estoMenosButton;
    private JPanel desdeDatePicker;
    private DatePicker hastaDatePicker;

    public JPanel getPrescripcionPanel() {return DashboardPanel;}
    DashboardView() {
        List<Medicamento> medicamentos = Service.instance().findAllMedicamentos();
        String[] medicamentosStr = new String[medicamentos.size()];
        int i = 0;
        while (i < medicamentos.size()) {
            medicamentosStr[i] = medicamentos.get(i).getNombre();
            i+=1;
        }
       medicamentosComboBox.setModel(new javax.swing.DefaultComboBoxModel(medicamentosStr));
    }
}
