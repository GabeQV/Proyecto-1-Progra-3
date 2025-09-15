package hospital.presentation.prescripcion;

import hospital.logic.Receta;

import javax.swing.*;
import java.awt.event.*;

public class ModificarDetalle extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel duracionLabel;
    private JLabel cantidadLabel;
    private JLabel indicacionesLabel;
    private JTextField indicacionesTextField;
    private JSpinner spinnerCantidad;
    private JSpinner spinnerDias;

    public ModificarDetalle(JFrame parent, Model model) {
        super(parent, "Modificar detalle", true);
        setContentPane(contentPane);
        setSize(500, 300);
        setLocationRelativeTo(parent);
        parent.setVisible(true);
        parent.setLocationRelativeTo(null);



        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cantidad = spinnerCantidad.getValue().toString();
                String duracion = spinnerDias.getValue().toString();
                String indicaciones = indicacionesTextField.getText();

                model.getCurrentReceta().setCantidad(cantidad);
                model.getCurrentReceta().setDuracion(duracion);
                model.getCurrentReceta().setIndicaciones(indicaciones);

                model.setCurrentReceta(model.getCurrentReceta());

                dispose();
            }
        });
    }

    private void onCancel() {
        dispose();
    }
}
