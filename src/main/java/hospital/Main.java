package hospital;

import hospital.logic.Service;
import hospital.presentation.pacientes.Controller;
import hospital.presentation.pacientes.PacientesView;
import hospital.presentation.pacientes.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
        catch (Exception ex) {};

        PacientesView   view = new PacientesView();
        Model model = new Model();
        Controller controller = new Controller(view, model);


        JFrame window = new JFrame();
        window.setSize(750,500);
        window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("Hospital");
        window.setContentPane(view.getPacientesPanel());

        window.setLocationRelativeTo(null);

        window.setVisible(true);
        window.setLocationRelativeTo(null);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Service.instance().stop();
            }
        });
        window.setVisible(true);


    }
    public static final Color BACKGROUND_ERROR = new Color(255, 102, 102);
}