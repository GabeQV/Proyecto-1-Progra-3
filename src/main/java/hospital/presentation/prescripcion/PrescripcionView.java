package hospital.presentation.prescripcion;

import javax.swing.*;

public class PrescripcionView {
    private JPanel PrescripcionPanel;
    private JButton buscarPacienteButton;
    private JButton agregarMedicamentoButton;
    private JPanel RecetaMedicaPanel;
    private JPanel ControlPanel;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JButton descartarMedicamentoButton;
    private JButton detallesButton;
    private JTextField fechaDeRetiroTextField;
    private JTextField mostrarPacienteAcaTextField;

    public JPanel getDashboardPanel() {return PrescripcionPanel;}
}
