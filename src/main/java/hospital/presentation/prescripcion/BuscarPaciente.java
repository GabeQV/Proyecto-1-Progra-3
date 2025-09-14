package hospital.presentation.prescripcion;

import hospital.logic.Paciente;
import hospital.logic.Service;
import hospital.presentation.pacientes.TableModel;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BuscarPaciente extends JDialog implements PropertyChangeListener {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JTable pacientesTable;
    hospital.presentation.prescripcion.Controller controllerPrescripcion;
    Model model;

    public BuscarPaciente(JFrame parent, Model model) {
        super(parent, "Buscar Paciente", true);
        setContentPane(contentPane);
        setSize(500, 300);
        setLocationRelativeTo(parent);
        parent.setVisible(true);
        parent.setLocationRelativeTo(null);

        this.model = model;

        int[] cols = {TableModel.ID, TableModel.NOMBRE, TableModel.FECHA_NACIMIENTO, TableModel.TELEFONO};
        pacientesTable.setModel(new TableModel(cols, Service.instance().findAllPacientes()));

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
        pacientesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) {
                    model.setCurrentPaciente((Paciente) pacientesTable.getValueAt(pacientesTable.getSelectedRow(),pacientesTable.getSelectedColumn()));
                }
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.LIST:
                int[] cols = {TableModel.ID, TableModel.NOMBRE, TableModel.FECHA_NACIMIENTO, TableModel.TELEFONO};
                pacientesTable.setModel(new TableModel(cols, Service.instance().findAllPacientes()));
                break;
            case Model.CURRENTPACIENTE:
               model.getCurrentReceta().setPaciente(model.getCurrentPaciente());

        }
    }
}
