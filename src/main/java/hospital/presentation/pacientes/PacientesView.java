package hospital.presentation.pacientes;


import com.github.lgooddatepicker.components.DatePicker;
import hospital.Main;
import hospital.logic.Paciente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PacientesView implements PropertyChangeListener {
    private JPanel PacientesPanel;
    private JPanel Paciente;
    private JLabel idLabel;
    private JTextField idField;
    private JTextField nombreField;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JButton borrarButton;
    private JTextField telefonoField;
    private JTextField busquedaField;
    private JButton buscarButton;
    private JTable pacientesTable;
    private DatePicker fechaNacPicker;

    Controller controller;
    Model model;

    public PacientesView() {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Paciente n = take();
                    try {
                        controller.create(n);
                        JOptionPane.showMessageDialog(PacientesPanel, "REGISTRO APLICADO", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(PacientesPanel, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.clear();
            }
        });

        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paciente n =  model.getCurrent();
                if (n.getId() == null || n.getId().isEmpty()) {
                    JOptionPane.showMessageDialog(PacientesPanel, "Seleccione un paciente para borrar", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(PacientesPanel, "¿Está seguro de borrar este paciente?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        controller.delete(n);
                        JOptionPane.showMessageDialog(PacientesPanel, "Paciente eliminado", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(PacientesPanel, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.read(busquedaField.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(PacientesPanel, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        pacientesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && pacientesTable.getSelectedRow() != -1) {
                    int row = pacientesTable.getSelectedRow();
                    Paciente seleccionado = ((TableModel) pacientesTable.getModel()).getRowAt(row);
                    controller.model.setCurrent(seleccionado);
                }
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.LIST:
                int[] cols = {TableModel.ID, TableModel.NOMBRE, TableModel.FECHA_NACIMIENTO,TableModel.TELEFONO};
                pacientesTable.setModel(new TableModel(cols, model.getList()));
                break;
            case Model.CURRENT:
                Paciente m = model.getCurrent();
                idField.setText(m.getId());
                nombreField.setText(m.getNombre());
                fechaNacPicker.setDate(m.getFechaNacimiento());
                telefonoField.setText(m.getTelefono());
                break;
        }
        PacientesPanel.revalidate();
    }

    public Paciente take() {
        Paciente e = new Paciente();
        e.setId(idField.getText());
        e.setNombre(nombreField.getText());
        e.setFechaNacimiento(fechaNacPicker.getDate());
        e.setTelefono(telefonoField.getText());
        return e;
    }

    private boolean validate() {
        boolean valid = true;
        if (idField.getText().isEmpty()) {
            valid = false;
            idField.setBackground(Main.BACKGROUND_ERROR);
            idField.setToolTipText("Id requerido");
        } else {
            idField.setBackground(null);
            idField.setToolTipText(null);
        }

        if (nombreField.getText().isEmpty()) {
            valid = false;
            nombreField.setBackground(Main.BACKGROUND_ERROR);
            nombreField.setToolTipText("Nombre requerido");
        } else {
            nombreField.setBackground(null);
            nombreField.setToolTipText(null);
        }

        if (fechaNacPicker.getDate() == null) {
            valid = false;
            fechaNacPicker.getComponentDateTextField().setBackground(Main.BACKGROUND_ERROR);
            fechaNacPicker.getComponentDateTextField().setToolTipText("Fecha de nacimiento requerida");
        } else {
            fechaNacPicker.getComponentDateTextField().setBackground(null);
            fechaNacPicker.getComponentDateTextField().setToolTipText(null);
        }

        if (telefonoField.getText().isEmpty()) {
            valid = false;
            telefonoField.setBackground(Main.BACKGROUND_ERROR);
            telefonoField.setToolTipText("Telefono requerido");
        } else {
            telefonoField.setBackground(null);
            telefonoField.setToolTipText(null);
        }
        return valid;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public JPanel getPacientesPanel() {return PacientesPanel;}
}
