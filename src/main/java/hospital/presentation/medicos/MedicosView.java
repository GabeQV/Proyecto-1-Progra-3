package hospital.presentation.medicos;

import javax.swing.*;

public class MedicosView {
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
    private JButton reporteButton;
    private JTable medicosTable;

    Controller controller;
    Model model;

    public JPanel getMedicosPanel() {return MedicosPanel;}

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        //model.addPropertyChangeListener(this);
    }
}
