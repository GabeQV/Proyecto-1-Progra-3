package hospital;

import hospital.presentation.prescripcion.PrescripcionView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
        catch (Exception ex) {};

        PrescripcionView view = new PrescripcionView();

        JFrame window = new JFrame();
        window.setSize(600,400);
        window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("Hospital");
        window.setContentPane(view.getPrescripcionPanel());

        window.setLocationRelativeTo(null);

        window.setVisible(true);
    }
}