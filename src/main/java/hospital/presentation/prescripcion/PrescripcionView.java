package hospital.presentation.prescripcion;

import com.github.lgooddatepicker.components.DatePicker;

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
    private JLabel mostrarPacienteAcaLabel;
    private JTable MedicamentosTable;
    private JPanel controlRecetasPanel;
    private JPanel recetasPanel;
    private JPanel ajustesPanel;
    private JLabel fechaDeRetiroLabel;
    private DatePicker FechaDeRetiro;

    public JPanel getPrescripcionPanel() {
        return PrescripcionPanel;
    }
    public PrescripcionView() {
    }
    public void setController(Controller controller) {
        this.controller = controller;
        buscarPacienteButton.addActionListener(e -> {
            JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(PrescripcionPanel);
            BuscarPacienteDialog dialog = new BuscarPacienteDialog(parent);
            dialog.setVisible(true);
        });

        agregarMedicamentoButton.addActionListener(e -> {
            JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(PrescripcionPanel);
            AgregarMedicamentoDialog dialog = new AgregarMedicamentoDialog(parent);
            dialog.setVisible(true);
            // Optionally: get selected medicine from dialog here
        });

        descartarMedicamentoButton.addActionListener(e -> controller.borrarReceta(MedicamentosTable.getSelectedRow()));
        guardarButton.addActionListener(e -> {
            try {
                controller.guardarRecetas(FechaDeRetiro.getText());
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
                // TODO: NO SE AAAHHHH
                break;
        }
    }
};
