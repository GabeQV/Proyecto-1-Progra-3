package hospital.presentation.login;

import hospital.logic.Sesion;

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

    public JPanel getLoginPanel() {return LoginPanel;}


}