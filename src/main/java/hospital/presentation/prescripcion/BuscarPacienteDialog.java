package hospital.presentation.prescripcion;

import javax.swing.*;
import java.awt.*;

public class BuscarPacienteDialog extends JDialog {
    public BuscarPacienteDialog(JFrame parent) {
        super(parent, "Pacientes", true);
        setLayout(new BorderLayout());

        JPanel filterPanel = new JPanel();
        filterPanel.add(new JLabel("Filtrar por:"));
        filterPanel.add(new JComboBox<>(new String[]{"nombre", "id"}));
        filterPanel.add(new JTextField(10));
        add(filterPanel, BorderLayout.NORTH);

        // Tabla de ejemplo otra vez
        JTable table = new JTable(
                new Object[][]{{"PAC-222", "Kate Castillo", "43334455", "2010-07-13"}},
                new String[]{"Id", "Nombre", "Teléfono", "Fec. Nac."}
        );
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("OK"));
        buttonPanel.add(new JButton("Cancel"));
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 250);
        setLocationRelativeTo(parent);
    }
}