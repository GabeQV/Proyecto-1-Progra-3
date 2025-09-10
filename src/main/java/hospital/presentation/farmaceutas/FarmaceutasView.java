package hospital.presentation.farmaceutas;

import hospital.Main;
import hospital.logic.Farmaceuta;
import hospital.presentation.farmaceutas.Controller;
import hospital.presentation.farmaceutas.Model;
import hospital.presentation.farmaceutas.TableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FarmaceutasView implements PropertyChangeListener {
    private JPanel Farmaceuta;
    private JLabel idLabel;
    private JTextField idField;
    private JTextField nombreField;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JButton borrarButton;
    private JTextField busquedaField;
    private JButton buscarButton;
    private JTable farmaceutasTable;
    private JPanel FarmaceutasPanel;

    Controller controller;
    Model model;

    public FarmaceutasView() {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Farmaceuta n = take();
                    try {
                        controller.create(n);
                        JOptionPane.showMessageDialog(FarmaceutasPanel, "REGISTRO APLICADO", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(FarmaceutasPanel, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
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
                Farmaceuta n =  model.getCurrent();
                if (n.getId() == null || n.getId().isEmpty()) {
                    JOptionPane.showMessageDialog(FarmaceutasPanel, "Seleccione un farmaceuta para borrar", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(FarmaceutasPanel, "¿Está seguro de borrar este farmaceuta?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        controller.delete(n);
                        JOptionPane.showMessageDialog(FarmaceutasPanel, "Farmaceuta eliminado", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(FarmaceutasPanel, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(FarmaceutasPanel, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        farmaceutasTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && farmaceutasTable.getSelectedRow() != -1) {
                    int row = farmaceutasTable.getSelectedRow();
                    Farmaceuta seleccionado = ((TableModel) farmaceutasTable.getModel()).getRowAt(row);
                    controller.model.setCurrent(seleccionado);
                }
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.LIST:
                int[] cols = {TableModel.ID, TableModel.NOMBRE};
                farmaceutasTable.setModel(new TableModel(cols, model.getList()));
                break;
            case Model.CURRENT:
                Farmaceuta m = model.getCurrent();
                idField.setText(m.getId());
                nombreField.setText(m.getNombre());
                break;
        }
        FarmaceutasPanel.revalidate();
    }

    public Farmaceuta take() {
        Farmaceuta e = new Farmaceuta();
        e.setId(idField.getText());
        e.setClave(idField.getText());
        e.setNombre(nombreField.getText());
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
        return valid;
    }

    public JPanel getFarmaceutasPanel() {return FarmaceutasPanel;}

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }
}
