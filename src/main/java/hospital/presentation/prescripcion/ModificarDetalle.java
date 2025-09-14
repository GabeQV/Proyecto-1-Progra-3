package hospital.presentation.prescripcion;

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

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
