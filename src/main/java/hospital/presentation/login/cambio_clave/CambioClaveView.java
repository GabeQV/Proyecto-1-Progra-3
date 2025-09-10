package hospital.presentation.login.cambio_clave;

import hospital.logic.Service;
import hospital.presentation.login.cambio_clave.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class CambioClaveView extends JDialog {
    private JPanel CambiarClavePanel;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private JTextField claveActualField;
    private JTextField claveNuevaField;
    private JTextField claveConfirmacionField;
    private final String userId;

    Controller controller;


    public CambioClaveView(JFrame parent, String userId) {
        super(parent, "Cambio de Clave", true);
        this.userId = userId;
        setContentPane(CambiarClavePanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(parent);

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String claveActual = new String(claveActualField.getText());
                String nuevaClave = new String(claveNuevaField.getText());
                String confirmarClave = new String(claveConfirmacionField.getText());

                try {
                    Service.instance().cambiarClave(userId, claveActual, nuevaClave, confirmarClave);
                    JOptionPane.showMessageDialog(
                            CambioClaveView.this,
                            "Clave cambiada exitosamente.",
                            "Información",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            CambioClaveView.this,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
