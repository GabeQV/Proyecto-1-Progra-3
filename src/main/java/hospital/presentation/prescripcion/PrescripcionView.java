package hospital.presentation.prescripcion;

import com.github.lgooddatepicker.components.DatePicker;
import hospital.Main;
import hospital.logic.Paciente;
import hospital.logic.Receta;

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
                if (model.getCurrentReceta().getMedicamento() == null) {
                    JOptionPane.showMessageDialog(PrescripcionPanel, "No hay medicamento asignado a la receta.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else {
                    ModificarDetalle dialog = new ModificarDetalle(
                            (JFrame) SwingUtilities.getWindowAncestor(PrescripcionPanel), model);
                    dialog.setVisible(true);
                }
            }
        });
        descartarMedicamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getCurrentReceta().getMedicamento() == null) {
                    JOptionPane.showMessageDialog(PrescripcionPanel, "No hay medicamento asignado a la receta.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                controller.descartarMedicamento();
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.clear();
                mostrarPacienteAcaLabel.setText("Nombre");
                FechaDeRetiro.clear();
            }
        });
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Receta n = model.getCurrentReceta();
                    try {
                        controller.guardarRecetaActual(n);
                        JOptionPane.showMessageDialog(PrescripcionPanel, "REGISTRO APLICADO", "", JOptionPane.INFORMATION_MESSAGE);
                        FechaDeRetiro.clear();
                        mostrarPacienteAcaLabel.setText("Nombre");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(PrescripcionPanel, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(PrescripcionPanel, "Complete todos los campos requeridos.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.CURRENT_RECETA:
                int[] cols = {RecetaTableModel.MEDICAMENTO, RecetaTableModel.PRESENTACION, RecetaTableModel.CANTIDAD, RecetaTableModel.INDICACIONES, RecetaTableModel.DURACION};
                RecetaTable.setModel(new RecetaTableModel(cols, model.getRecetaActualComoLista()));
                break;
            case Model.CURRENT_PACIENTE:
                int[] cols2 = {RecetaTableModel.MEDICAMENTO, RecetaTableModel.PRESENTACION, RecetaTableModel.CANTIDAD, RecetaTableModel.INDICACIONES, RecetaTableModel.DURACION};
                RecetaTable.setModel(new RecetaTableModel(cols2, model.getRecetaActualComoLista()));
                if (model.getCurrentPaciente() != null)
                    mostrarPacienteAcaLabel.setText(model.getCurrentPaciente().getNombre());
                break;
        }
        RecetaTable.revalidate();
    }

    private boolean validate() {
        boolean valid = true;
        if (model.getCurrentReceta().getPaciente() == null) {
            valid = false;
        }

        if (model.getCurrentReceta().getMedicamento() == null) {
            valid = false;
        }

        if (FechaDeRetiro.getDate() == null) {
            valid = false;
            FechaDeRetiro.getComponentDateTextField().setBackground(Main.BACKGROUND_ERROR);
            FechaDeRetiro.getComponentDateTextField().setToolTipText("Fecha de retiro requerida");
        } else {
            FechaDeRetiro.getComponentDateTextField().setBackground(null);
            FechaDeRetiro.getComponentDateTextField().setToolTipText(null);
        }

        if (model.getCurrentReceta().getCantidad() == null || model.getCurrentReceta().getDuracion() == null || model.getCurrentReceta().getIndicaciones() == null) {
            valid = false;
        }
        return valid;
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
