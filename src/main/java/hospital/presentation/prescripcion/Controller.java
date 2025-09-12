package hospital.presentation.prescripcion;

import hospital.logic.Receta;
import hospital.logic.Service;

public class Controller {
    PrescripcionView view;
    Model model;

    public Controller(PrescripcionView view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void agregarReceta(Receta r) {
        model.addReceta(r);
        model.setCurrent(new Receta()); // clear current
    }


    public void borrarReceta(int index) {
        model.removeReceta(index);
    }

    public void guardarRecetas(String text) throws Exception {
        for (Receta r : model.getList()) {
            Service.instance().createReceta(r);
        }
        model.setList(new java.util.ArrayList<>()); // clear list after save
    }

    public void clear() {
        model.setCurrent(new Receta());
        model.setList(new java.util.ArrayList<>());
    }

    public boolean buscarPaciente(String pacienteId) {
        if (pacienteId == null) { return false; }
        for (Receta r : model.getList()) {
           if (r.getId().equals(pacienteId)) {return true;}
        }
        return false;
    }

    public Receta mostrarDetalles(int index) {
        return model.getList().get(index);
    }
}
