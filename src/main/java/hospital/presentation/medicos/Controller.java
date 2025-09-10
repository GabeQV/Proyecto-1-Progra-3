package hospital.presentation.medicos;
import hospital.logic.Medico;
import hospital.logic.Service;
import hospital.presentation.medicos.MedicosView;

public class Controller {
    MedicosView view;
    Model model;

    public Controller(MedicosView view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);

    }
}
