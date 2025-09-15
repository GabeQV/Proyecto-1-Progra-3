package hospital.presentation.catalogo;

import hospital.logic.Medicamento;
import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CatologoView implements PropertyChangeListener {
    private JTextField idField;
    private JTextField nombreField;
    private JTextField presentacionField;
    private JTable medicamentosTable;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JButton borrarButton;
    private JPanel catalogoPanel;
    private JComboBox comboBox1;
    private JTextField busquedaField;
    private JButton buscarButton;
    Controller controller;
    Model model;


    public JPanel getCatalogoPanel() {
        return catalogoPanel;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case hospital.presentation.catalogo.Model.LIST:
                int[] cols = {
                        TableModel.ID,
                        TableModel.NOMBRE,
                        TableModel.PRESENTACION
                };
                medicamentosTable.setModel(new TableModel(cols, model.getList()));
                break;
            case hospital.presentation.catalogo.Model.CURRENT:

                break;
        }
        catalogoPanel.revalidate();
    }

    public CatologoView() {
        guardarButton.addActionListener(e -> guardarMedicamento());
        limpiarButton.addActionListener(e -> limpiarCampos());
        borrarButton.addActionListener(e -> borrarMedicamento());
        buscarButton.addActionListener(e -> buscarMedicamentos());
        medicamentosTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = medicamentosTable.getSelectedRow();
                if (row >= 0 && model.getList().size() > row) {
                    Medicamento med = model.getList().get(row);
                    idField.setText(med.getId());
                    nombreField.setText(med.getNombre());
                    presentacionField.setText(med.getPresentacion());
                    model.setCurrent(med);
                }
            }
        });
    }

    private void guardarMedicamento() {
        try {
            Medicamento med = new Medicamento();
            med.setId(idField.getText());
            med.setNombre(nombreField.getText());
            med.setPresentacion(presentacionField.getText());

            boolean existe = model.getList().stream().anyMatch(m -> m.getId().equals(med.getId()));
            if (existe) {
                controller.update(med);
                JOptionPane.showMessageDialog(catalogoPanel, "Medicamento actualizado correctamente.");
            } else {
                controller.create(med);
                JOptionPane.showMessageDialog(catalogoPanel, "Medicamento guardado correctamente.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(catalogoPanel, "Error al guardar: " + ex.getMessage());
        }
    }

    private void limpiarCampos() {
        controller.clear();
        idField.setText("");
        nombreField.setText("");
        presentacionField.setText("");
    }

    private void borrarMedicamento() {
        try {
            String id = idField.getText();
            controller.delete(id);
            JOptionPane.showMessageDialog(catalogoPanel, "Medicamento borrado correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(catalogoPanel, "Error al borrar: " + ex.getMessage());
        }
    }

    private void buscarMedicamentos() {
        String criterio = busquedaField.getText();
        String tipoBusqueda = (String) comboBox1.getSelectedItem();
        try {
            if (tipoBusqueda.equals("Nombre")) {
                model.setList(controller.buscarPorNombre(criterio));
            } else if (tipoBusqueda.equals("Codigo")) {
                model.setList(controller.buscarPorCodigo(criterio));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(catalogoPanel, "Error en búsqueda: " + ex.getMessage());
        }
    }


}
