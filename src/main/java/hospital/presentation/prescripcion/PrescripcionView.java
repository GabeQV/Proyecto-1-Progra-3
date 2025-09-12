package hospital.presentation.prescripcion;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

public class PrescripcionView {
    Controller controller;
    Model model;

    private JPanel PrescripcionPanel;
    private JButton buscarPacienteButton;
    private JButton agregarMedicamentoButton;
    private JPanel RecetaMedicaPanel;
    private JPanel ControlPanel;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JButton descartarMedicamentoButton;
    private JButton detallesButton;
    private JTextField fechaDeRetiroTextField;
    private JTextField mostrarPacienteAcaTextField;
    private JTable MedicamentosTable;

    public JPanel getDashboardPanel() {
        return PrescripcionPanel;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
    }
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.LIST:
                MedicamentosTable.setModel(new TableModel(
                        new int[]{TableModel.ID, TableModel.NOMBRE, TableModel.PRESENTACION},
                        model.getList()
                ));
                break;
            case Model.CURRENT:
                // update text fields, etc.
                break;
        }
    }
};
