package hospital.presentation.prescripcion;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

public class PrescripcionView{
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
    private JPanel controlRecetasPanel;
    private JPanel recetasPanel;
    private JPanel ajustesPanel;
    private JLabel nombrePacienteLabel;
    private JLabel fechaDeRetiroLabel;

    public JPanel getPrescripcionPanel() {
        return PrescripcionPanel;
    }

    public void setController(Controller controller) {
        this.controller = controller;
        buscarPacienteButton.addActionListener(e -> {
            String pacienteId = mostrarPacienteAcaTextField.getText();
            controller.buscarPaciente(pacienteId);
        });

        agregarMedicamentoButton.addActionListener(e -> controller.agregarReceta(null));
        descartarMedicamentoButton.addActionListener(e -> controller.borrarReceta(MedicamentosTable.getSelectedRow()));
        guardarButton.addActionListener(e -> {
            try {
                controller.guardarRecetas(fechaDeRetiroTextField.getText());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        limpiarButton.addActionListener(e -> controller.clear());
        detallesButton.addActionListener(e -> controller.mostrarDetalles(MedicamentosTable.getSelectedRow()));

    }

    public void setModel(Model model) {
        this.model = model;
        MedicamentosTable.setModel(model.getTableModel());
    }

    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.LIST:
                MedicamentosTable.setModel(new RecetaTableModel(
                        new int[]{RecetaTableModel.ID},
                        model.getList()
                ));
                break;
            case Model.CURRENT:
                // Nose chaval
                break;
        }
    }
};
