package hospital.presentation.despacho;

import hospital.logic.Medico;
import hospital.logic.Service;
import hospital.presentation.despacho.DespachoView;
import hospital.presentation.despacho.Model;

public class Controller {
    DespachoView view;
    hospital.presentation.despacho.Model model;

    public Controller(DespachoView view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
        model.setList(Service.instance().findAllMedicos());
    }

    public void create(Medico e) throws  Exception{
        Service.instance().createMedico(e);
        if (e.getId() == null || e.getId().trim().isEmpty() || e.getId().contains(" ")) {
            throw new Exception("El ID no puede estar vacío, repetido o contener espacios");
        }

        System.out.println("Guardando médico: " + e.getId() + " - " + e.getNombre() + " - " + e.getEspecialidad() + " - " + e.getClave());
        model.setCurrent(new Medico());
        model.setList(Service.instance().findAllMedicos());
    }

    public void read(String id) throws Exception {
        Medico e = new Medico();
        e.setId(id);
        try {
            model.setCurrent(Service.instance().readMedico(e));
        } catch (Exception ex) {
            Medico b = new Medico();
            b.setId(id);
            model.setCurrent(b);
            throw ex;
        }
    }

    public void clear() {
        model.setCurrent(new Medico());
    }
}
