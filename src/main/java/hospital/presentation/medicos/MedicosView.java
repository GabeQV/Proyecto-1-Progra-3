package hospital.presentation.medicos;

import javax.swing.*;
import java.beans.PropertyChangeListener;

public class MedicosView {
    private JPanel MedicosPanel;

    Controller controller;
    Model model;

    public JPanel getMedicosPanel() {return MedicosPanel;}

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        //model.addPropertyChangeListener(this);
    }
}
