package hospital.presentation.prescripcion;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PrescripcionView implements PropertyChangeListener {

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
    private JTable RecetaTable;
    private JPanel controlRecetasPanel;
    private JPanel recetasPanel;
    private JPanel ajustesPanel;
    private JLabel fechaDeRetiroLabel;
    private DatePicker FechaDeRetiro;

    Controller controller;
    Model model;

    public PrescripcionView() {
        buscarPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuscarPaciente dialog = new BuscarPaciente(
                        (JFrame) SwingUtilities.getWindowAncestor(PrescripcionPanel), model);
                dialog.setVisible(true);
            }
        });

        agregarMedicamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarMedicamento dialog = new AgregarMedicamento(
                        (JFrame) SwingUtilities.getWindowAncestor(PrescripcionPanel), model);
                dialog.setVisible(true);
            }
        });
        detallesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModificarDetalle dialog = new ModificarDetalle(
                        (JFrame) SwingUtilities.getWindowAncestor(PrescripcionPanel), model);
                dialog.setVisible(true);
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.CURRENT_RECETA:
            case Model.CURRENT_PACIENTE:
                int[] cols = {RecetaTableModel.MEDICAMENTO, RecetaTableModel.PRESENTACION, RecetaTableModel.CANTIDAD, RecetaTableModel.INDICACIONES, RecetaTableModel.DURACION};
                RecetaTable.setModel(new RecetaTableModel(cols, model.getRecetaActualComoLista()));
                if (model.getCurrentPaciente() != null)
                    mostrarPacienteAcaLabel.setText(model.getCurrentPaciente().getNombre());
                break;
        }
        RecetaTable.revalidate();
    }

    public void setController(hospital.presentation.prescripcion.Controller controller) {
        this.controller = controller;
    }

    public void setModel(hospital.presentation.prescripcion.Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public JPanel getPrescripcionPanel() {
        return PrescripcionPanel;
    }


};
