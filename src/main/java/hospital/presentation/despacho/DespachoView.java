package hospital.presentation.despacho;

import hospital.logic.Receta;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class DespachoView implements PropertyChangeListener {
    private JTextField idField;
    private JTable recetasTable;
    private JButton procesarButton;
    private JButton listoButton;
    private JButton entregarButton;
    private JButton button4; // volver
    private JButton buscarButton;
    private JPanel despachoView;


    Controller controller;
    Model model;

    public DespachoView() {
        buscarButton.addActionListener(e -> {
            String pacienteId = idField.getText();
            if (pacienteId == null || pacienteId.isEmpty()) {
                JOptionPane.showMessageDialog(despachoView, "Ingrese ID de paciente para buscar", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            controller.searchByPacienteId(pacienteId);
            if (model.getList().isEmpty()) {
                JOptionPane.showMessageDialog(despachoView, "No se encontraron recetas para ese ID", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        procesarButton.addActionListener(e -> {
            try {
                controller.procesar();
            } catch (Exception ex) {
            }
            updateButtons(model.getCurrent(), model.getList());
        });

        listoButton.addActionListener(e -> {
            try {
                controller.listo();
            } catch (Exception ex) {
            }
            updateButtons(model.getCurrent(), model.getList());
        });

        entregarButton.addActionListener(e -> {
            try {
                controller.entregar();
            } catch (Exception ex) {
            }
            updateButtons(model.getCurrent(), model.getList());
        });

        button4.addActionListener(e -> {
            try {
                controller.revertir();
            } catch (Exception ex) {
            }
            updateButtons(model.getCurrent(), model.getList());
        });

        recetasTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && recetasTable.getSelectedRow() != -1) {
                    int row = recetasTable.getSelectedRow();
                    Receta seleccionado = ((TableModel) recetasTable.getModel()).getRowAt(row);
                    controller.model.setCurrent(seleccionado);
                }
            }
        });
        recetasTable.addMouseListener(new MouseAdapter() {
        });
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
                        TableModel.DURACION,
                        TableModel.ESTADO
                };
                recetasTable.setModel(new TableModel(cols, model.getList()));
                break;
            case Model.CURRENT:
                Receta r = model.getCurrent();
                if (r != null) {
                    idField.setText(r.getPaciente() != null ? r.getPaciente().getId() : "");
                } else {
                    idField.setText("");
                }
                updateButtons(model.getCurrent(), model.getList());
                break;
        }
        despachoView.revalidate();
    }

    private void updateButtons(Receta current, List<Receta> list) {
        String estado = current != null ? current.getEstado() : null;
        if (estado == null || estado.isEmpty()) {
            procesarButton.setEnabled(false);
            listoButton.setEnabled(false);
            entregarButton.setEnabled(false);
            button4.setEnabled(false);
            return;
        }

        procesarButton.setEnabled("Confeccionado".equalsIgnoreCase(estado));
        listoButton.setEnabled("En proceso".equalsIgnoreCase(estado));
        entregarButton.setEnabled("Lista".equalsIgnoreCase(estado));
        button4.setEnabled(estado.equalsIgnoreCase("En proceso") || estado.equalsIgnoreCase("Lista") || estado.equalsIgnoreCase("Entregada"));
    }
}