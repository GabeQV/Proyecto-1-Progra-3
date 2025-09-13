package hospital.presentation.prescripcion;

import javax.swing.*;
import java.awt.*;
import hospital.presentation.prescripcion.Controller;

public class BuscarPacienteDialog extends JDialog {
    private Model model;
    public BuscarPacienteDialog(JFrame parent, Model model) {
        this.model = model;
        super(parent, "Pacientes", true);
        setLayout(new BorderLayout());


        JPanel filterPanel = new JPanel();
        filterPanel.add(new JLabel("Filtrar por:"));
        filterPanel.add(new JComboBox<>(new String[]{"nombre", "id"}));
        filterPanel.add(new JTextField(10));
        add(filterPanel, BorderLayout.NORTH);

        // Tabla de ejemplo otra vez
        JTable table = new JTable();
        table.setModel(model.getTableModel());
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("OK"));
        buttonPanel.add(new JButton("Cancel"));
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 250);
        setLocationRelativeTo(parent);
    }
}