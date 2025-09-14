package hospital.presentation.prescripcion;

import com.github.lgooddatepicker.components.DatePicker;
import hospital.presentation.despacho.TableModel;

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
            BuscarPaciente dialog = new BuscarPaciente(parent, model);
            dialog.setVisible(true);
        });

        agregarMedicamentoButton.addActionListener(e -> {
            JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(PrescripcionPanel);
            AgregarMedicamento dialog = new AgregarMedicamento(parent, model);
            dialog.setVisible(true);
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
    }

    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case hospital.presentation.despacho.Model.LIST:
                int[] cols = {
                        TableModel.MEDICAMENTO,
                        TableModel.PRESENTACION,
                        TableModel.CANTIDAD,
                        TableModel.INDICACIONES,
                        TableModel.DURACION,
                        TableModel.ESTADO
                };
                MedicamentosTable.setModel(new TableModel(cols, model.getList()));
                break;
            case Model.CURRENTPACIENTE:
                // TODO: NO SE AAAHHHH
                break;
            case Model.CURRENTRECETA:
                // TODO: NO SE AHHHHH
                break;
        }
    }
};
