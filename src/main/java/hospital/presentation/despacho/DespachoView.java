package hospital.presentation.despacho;

import hospital.logic.Receta;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class DespachoView implements PropertyChangeListener {
    private JTextField idField;
    private JTable recetasTable;
    private JButton procesarButton;
    private JButton listoButton;
    private JButton entregarButton;
    private JButton button4;
    private JButton buscarButton;
    private JPanel despachoView;


    Controller controller;
    Model model;

    public DespachoView() {
        // Aquí puedes agregar listeners a los botones si lo deseas
        // Ejemplo:
        // buscarButton.addActionListener(e -> controller.read(idField.getText()));
    }

    public JPanel getDespachoView() {
        return despachoView;
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
            case Model.LIST:
                int[] cols = {
                        TableModel.MEDICAMENTO,
                        TableModel.PRESENTACION,
                        TableModel.CANTIDAD,
                        TableModel.INDICACIONES,
                        TableModel.DURACION
                };
                recetasTable.setModel(new TableModel(cols, model.getList()));
                break;
            case Model.CURRENT:
                Receta r = model.getCurrent();
                if (r != null) {
                    idField.setText(r.getId() != null ? r.getId() : "");
                }
                break;
        }
        despachoView.revalidate();
    }
}

