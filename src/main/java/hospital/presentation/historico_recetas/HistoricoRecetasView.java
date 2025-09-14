package hospital.presentation.historico_recetas;

import hospital.presentation.historico_recetas.Controller;
import hospital.presentation.historico_recetas.Model;
import hospital.presentation.historico_recetas.TableModel;

import javax.swing.*;
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

    Controller controller;
    Model model;
    public JPanel getHistoricoRecetasView() {return historicoRecetasPanel;}

    public JPanel getHitoricoRecetasPanel() {
        return historicoRecetasPanel;
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
                        hospital.presentation.historico_recetas.TableModel.ID,
                        hospital.presentation.historico_recetas.TableModel.MEDICAMENTO,
                        hospital.presentation.historico_recetas.TableModel.PRESENTACION,
                        hospital.presentation.historico_recetas.TableModel.CANTIDAD,
                        hospital.presentation.historico_recetas.TableModel.INDICACIONES,
                        hospital.presentation.historico_recetas.TableModel.DURACION,
                        hospital.presentation.historico_recetas.TableModel.ESTADO
                };
                recetasTable.setModel(new TableModel(cols, model.getList()));
                break;

        }
        historicoRecetasPanel.revalidate();
    }


}
