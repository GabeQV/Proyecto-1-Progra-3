package hospital;

import hospital.presentation.login.LoginView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
        catch (Exception ex) {};

        LoginView view = new LoginView();

        JFrame window = new JFrame();
        window.setSize(350,200);
        window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("Hospital");
        window.setContentPane(view.getLoginPanel());

        window.setLocationRelativeTo(null);

        window.setVisible(true);
    }
}