package hospital.presentation.historico_recetas;

import hospital.logic.Receta;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HistoricoRecetasView implements PropertyChangeListener {
    private JTextField idPacienteTextField;
    private JTextField idRecetaTextField;
    private JButton buscarIdPacienteButton;
    private JButton buscarIdRecetaButton;
    private JTable recetasTable;
    private JPanel historicoRecetasPanel;
    private JPanel Busqueda;
    private JLabel nombrePacienteLabel;

    Controller controller;
    Model model;

    public JPanel getHitoricoRecetasPanel() { return historicoRecetasPanel; }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public HistoricoRecetasView() {
        buscarIdPacienteButton.addActionListener(e -> {
            String pacienteId = idPacienteTextField.getText();
            if (pacienteId == null || pacienteId.isEmpty()) {
                JOptionPane.showMessageDialog(historicoRecetasPanel, "Ingrese ID de paciente para buscar", "Información", JOptionPane.INFORMATION_MESSAGE);
                nombrePacienteLabel.setText("PACIENTE");
                return;
            }
            controller.searchByPacienteId(pacienteId);
            if (!model.getList().isEmpty() && model.getList().get(0).getPaciente() != null) {
                nombrePacienteLabel.setText(model.getList().get(0).getPaciente().getNombre());
            } else {
                nombrePacienteLabel.setText("PACIENTE");
                JOptionPane.showMessageDialog(historicoRecetasPanel, "No se encontraron recetas para ese ID", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        buscarIdRecetaButton.addActionListener(e -> {
            String recetaId = idRecetaTextField.getText();
            if (recetaId == null || recetaId.isEmpty()) {
                nombrePacienteLabel.setText("PACIENTE");
                return;
            }
            controller.searchByRecetaId(recetaId);
            if (!model.getList().isEmpty() && model.getList().get(0).getPaciente() != null) {
                nombrePacienteLabel.setText(model.getList().get(0).getPaciente().getNombre());
            } else {
                nombrePacienteLabel.setText("PACIENTE");
                JOptionPane.showMessageDialog(historicoRecetasPanel, "No se encontró receta con ese ID", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        recetasTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (recetasTable.getSelectedRow() != -1) {
                    int row = recetasTable.getSelectedRow();
                    Receta seleccionado = ((TableModel) recetasTable.getModel()).getRowAt(row);
                    model.setCurrent(seleccionado);
                    if (seleccionado != null && seleccionado.getPaciente() != null) {
                        nombrePacienteLabel.setText(seleccionado.getPaciente().getNombre());
                    } else {
                        nombrePacienteLabel.setText("PACIENTE");
                    }
                }
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.LIST:
                int[] cols = {
                        hospital.presentation.historico_recetas.TableModel.ID,
                        hospital.presentation.historico_recetas.TableModel.MEDICAMENTO,
                        hospital.presentation.historico_recetas.TableModel.PRESENTACION,
                        hospital.presentation.historico_recetas.TableModel.CANTIDAD,
                        hospital.presentation.historico_recetas.TableModel.INDICACIONES,
                        hospital.presentation.historico_recetas.TableModel.DURACION,
                        hospital.presentation.historico_recetas.TableModel.ESTADO
                };
                recetasTable.setModel(new TableModel(cols, model.getList()));
                if (!model.getList().isEmpty() && model.getList().get(0).getPaciente() != null) {
                    nombrePacienteLabel.setText(model.getList().get(0).getPaciente().getNombre());
                } else {
                    nombrePacienteLabel.setText("PACIENTE");
                }
                break;
            case Model.CURRENT:
                Receta r = model.getCurrent();
                if (r != null && r.getPaciente() != null) {
                    nombrePacienteLabel.setText(r.getPaciente().getNombre());
                } else {
                    nombrePacienteLabel.setText("PACIENTE");
                }
                break;
        }
        historicoRecetasPanel.revalidate();
    }
}