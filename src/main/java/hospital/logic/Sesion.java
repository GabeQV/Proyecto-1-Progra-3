package hospital.logic;

import hospital.presentation.dashboard.DashboardView;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Sesion {
    private static Sesion theInstance;
    private Usuario usuarioActual;

    public static Sesion instance() {
        if (theInstance == null) theInstance = new Sesion();
        return theInstance;
    }

    private Sesion() {}

    public void setUsuarioActual(Usuario user) {
        this.usuarioActual = user;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void abrirVentanaPrincipal() {
        Usuario user = getUsuarioActual();
        if (user == null) return;

        JFrame mainWindow = new JFrame();
        mainWindow.setSize(750,500);
        mainWindow.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setTitle("Hospital" + " " + user.getNombre() + " " + user.getId());

        JTabbedPane tabbedPane = new JTabbedPane();

        switch(user.getTipo()) {
            case "ADMINISTRADOR":

                //MVC de Medicos
                hospital.presentation.medicos.MedicosView medicosView = new hospital.presentation.medicos.MedicosView();
                hospital.presentation.medicos.Model medicosModel = new hospital.presentation.medicos.Model();
                hospital.presentation.medicos.Controller medicosController = new hospital.presentation.medicos.Controller(medicosView, medicosModel);
                tabbedPane.addTab("Medicos", medicosView.getMedicosPanel());

                //MVC de Farmaceutas
                hospital.presentation.farmaceutas.FarmaceutasView farmaceutasView = new hospital.presentation.farmaceutas.FarmaceutasView();
                hospital.presentation.farmaceutas.Model farmaceutasModel = new hospital.presentation.farmaceutas.Model();
                hospital.presentation.farmaceutas.Controller farmaceutasController = new hospital.presentation.farmaceutas.Controller(farmaceutasView, farmaceutasModel);
                tabbedPane.addTab("Farmaceutas", farmaceutasView.getFarmaceutasPanel());

                //MVC de Pacientes
                hospital.presentation.pacientes.PacientesView pacientesView = new hospital.presentation.pacientes.PacientesView();
                hospital.presentation.pacientes.Model pacientesModel = new hospital.presentation.pacientes.Model();
                hospital.presentation.pacientes.Controller pacientesController = new hospital.presentation.pacientes.Controller(pacientesView, pacientesModel);
                tabbedPane.addTab("Pacientes", pacientesView.getPacientesPanel());

        }

        mainWindow.setContentPane(tabbedPane);
        mainWindow.setLocationRelativeTo(null);

        mainWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Service.instance().stop();
            }
        });
        mainWindow.setVisible(true);

    }

    public void cerrarSesion() {
        this.usuarioActual = null;
    }
}
