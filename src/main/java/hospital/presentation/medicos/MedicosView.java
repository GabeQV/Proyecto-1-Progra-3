package hospital.presentation.medicos;

import hospital.Main;
import hospital.logic.Medico;
import hospital.logic.Service;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MedicosView implements PropertyChangeListener {
    private JPanel MedicosPanel;
    private JPanel Medico;
    private JLabel idLabel;
    private JTextField especialidadField;
    private JTextField idField;
    private JTextField nombreField;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JButton borrarButton;
    private JTextField busquedaField;
    private JButton buscarButton;
    private JTable medicosTable;

    Controller controller;
    Model model;


    public MedicosView() {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Medico n = take();
                    try {
                        controller.create(n);
                        JOptionPane.showMessageDialog(MedicosPanel, "REGISTRO APLICADO", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(MedicosPanel, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
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
                Medico n =  model.getCurrent();
                if (n.getId() == null || n.getId().isEmpty()) {
                    JOptionPane.showMessageDialog(MedicosPanel, "Seleccione un médico para borrar", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(MedicosPanel, "¿Está seguro de borrar este médico?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        controller.delete(n);
                        JOptionPane.showMessageDialog(MedicosPanel, "Médico eliminado", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(MedicosPanel, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(MedicosPanel, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        medicosTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && medicosTable.getSelectedRow() != -1) {
                    int row = medicosTable.getSelectedRow();
                    Medico seleccionado = ((TableModel) medicosTable.getModel()).getRowAt(row);
                    controller.model.setCurrent(seleccionado);
                }
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.LIST:
                int[] cols = {TableModel.ID, TableModel.NOMBRE, TableModel.ESPECIALIDAD};
                medicosTable.setModel(new TableModel(cols, model.getList()));
                break;
            case Model.CURRENT:
                Medico m = model.getCurrent();
                idField.setText(m.getId());
                nombreField.setText(m.getNombre());
                especialidadField.setText(m.getEspecialidad());
                break;
        }
        MedicosPanel.revalidate();
    }


    public Medico take() {
        Medico e = new Medico();
        e.setId(idField.getText());
        e.setClave(idField.getText());
        e.setNombre(nombreField.getText());
        e.setEspecialidad(especialidadField.getText());
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

        if (especialidadField.getText().isEmpty()) {
            valid = false;
            especialidadField.setBackground(Main.BACKGROUND_ERROR);
            especialidadField.setToolTipText("Departamento requerido");
        } else {
            especialidadField.setBackground(null);
            especialidadField.setToolTipText(null);
        }
        return valid;
    }

    public JPanel getMedicosPanel() {return MedicosPanel;}

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }
}
