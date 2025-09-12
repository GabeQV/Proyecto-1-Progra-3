package hospital.presentation.prescripcion;

import javax.swing.*;
import java.awt.*;

public class AgregarMedicamentoDialog extends JDialog {
    public AgregarMedicamentoDialog(JFrame parent) {
        super(parent, "Medicamentos", true);
        setLayout(new BorderLayout());

        JPanel filterPanel = new JPanel();
        filterPanel.add(new JLabel("Filtrar por:"));
        filterPanel.add(new JComboBox<>(new String[]{"nombre", "codigo"}));
        filterPanel.add(new JTextField(10));
        add(filterPanel, BorderLayout.NORTH);

        // Tabla de ejemplo sigmas
        JTable table = new JTable(
                new Object[][]{{"ACE-100", "Acetaminofen", "100 mg"}},
                new String[]{"Codigo", "Nombre", "Presentacion"}
        );
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("OK"));
        buttonPanel.add(new JButton("Cancel"));
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 200);
        setLocationRelativeTo(parent);
    }
}