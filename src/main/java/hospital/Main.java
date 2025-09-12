package hospital;

import hospital.logic.Paciente;
import hospital.logic.Service;
import hospital.presentation.despacho.DespachoView;
import hospital.presentation.login.LoginView;
//import hospital.presentation.lista_pacientes.ListaPacientesView;
import hospital.logic.Paciente.*;
import hospital.presentation.despacho.Controller;
import hospital.presentation.medicos.MedicosView;
import hospital.presentation.despacho.Model;

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

        DespachoView view = new DespachoView();
        Model model = new Model();
        Controller controller = new Controller(view, model);



        JFrame window = new JFrame();
        window.setSize(500,400);
        window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("Hospital");
        window.setContentPane(view.getDespachoView());

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