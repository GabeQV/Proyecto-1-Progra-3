package hospital.presentation.login;

import hospital.logic.Sesion;
import hospital.presentation.login.cambio_clave.CambioClaveView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

public class LoginView  {
    private JPanel LoginPanel;
    private JTextField idField;
    private JTextField claveField;
    private JLabel idLabel;
    private JLabel claveLabel;
    private JButton loginButton;
    private JButton cancelarButton;
    private JButton cambiarClaveButton;


    public LoginView() {
        cambiarClaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = idField.getText();
                if (userId.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginPanel, "Debe ingresar su ID primero", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                CambioClaveView dialog = new CambioClaveView((JFrame) SwingUtilities.getWindowAncestor(LoginPanel), userId);
                dialog.setVisible(true);
            }
        });
    }

    public JPanel getLoginPanel() {return LoginPanel;}


}