package hospital;

import hospital.logic.Service;
import hospital.presentation.farmaceutas.Controller;
import hospital.presentation.farmaceutas.FarmaceutasView;
import hospital.presentation.farmaceutas.Model;

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

        FarmaceutasView view = new FarmaceutasView();
        Model model = new Model();
        Controller controller = new Controller(view, model);


        JFrame window = new JFrame();
        window.setSize(700,500);
        window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("Hospital");
        window.setContentPane(view.getFarmaceutasPanel());

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