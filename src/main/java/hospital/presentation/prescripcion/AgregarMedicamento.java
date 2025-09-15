package hospital.presentation.prescripcion;

import hospital.logic.Medicamento;
import hospital.logic.Paciente;
import hospital.logic.Service;
import hospital.presentation.pacientes.TableModel;


import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AgregarMedicamento extends JDialog implements PropertyChangeListener {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTable medicamentosTable;

    Model model;
    Medicamento medicamento;

    public AgregarMedicamento(JFrame parent, Model model) {
        super(parent, "Agregar Medicamento", true);
        setContentPane(contentPane);
        setSize(500, 300);
        setLocationRelativeTo(parent);

        parent.setVisible(true);
        parent.setLocationRelativeTo(null);
        this.model = model;
        medicamento = new Medicamento();

        int[] cols = {MedicamentosTableModel.ID, MedicamentosTableModel.PRESENTACION, MedicamentosTableModel.NOMBRE};
        medicamentosTable.setModel(new MedicamentosTableModel(cols, Service.instance().findAllMedicamentos()));

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

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        medicamentosTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) {
                    medicamento = ((MedicamentosTableModel)medicamentosTable.getModel()).getRowAt(medicamentosTable.getSelectedRow());
                    model.getCurrentReceta().setMedicamento(medicamento);
                    model.setCurrentReceta(model.getCurrentReceta());
                }
            }
        });
    }

    private void onOK() {
        int row = medicamentosTable.getSelectedRow();
        if (row != -1) {
            Medicamento seleccionado = ((MedicamentosTableModel) medicamentosTable.getModel()).getRowAt(row);
            model.getCurrentReceta().setMedicamento(seleccionado);
            model.setCurrentReceta(model.getCurrentReceta());
        }
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.LIST:
                int[] cols = {MedicamentosTableModel.ID, MedicamentosTableModel.PRESENTACION, MedicamentosTableModel.NOMBRE};
                medicamentosTable.setModel(new MedicamentosTableModel(cols, Service.instance().findAllMedicamentos()));
                break;
            case Model.CURRENT_RECETA:
                model.getCurrentReceta().setMedicamento(medicamento);
                break;

        }
    }
}
