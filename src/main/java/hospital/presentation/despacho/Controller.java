package hospital.presentation.despacho;

import hospital.logic.Receta;
import hospital.logic.Service;

import java.util.List;

public class Controller {
    DespachoView view;
    Model model;

    public Controller(DespachoView view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
        model.setList(Service.instance().findAllRecetas());
    }

    public void create(Receta e) throws Exception {
        Service.instance().createReceta(e);
        model.setCurrent(new Receta());
        model.setList(Service.instance().findAllRecetas());
    }

    public void read(String id) throws Exception {
        Receta r = new Receta();
        r.setId(id);
        try {
            model.setCurrent(Service.instance().readReceta(r));
        } catch (Exception ex) {
            Receta empty = new Receta();
            empty.setId(id);
            model.setCurrent(empty);
            throw ex;
        }
    }

    public void clear() {
        model.setCurrent(new Receta());
    }

    public void searchByPacienteId(String pacienteId) {
        List<Receta> list = Service.instance().findRecetasByPacienteId(pacienteId);
        model.setList(list);
        if (list.isEmpty()) {
            model.setCurrent(new Receta());
        }
    }

    public void procesar() throws Exception {
        Receta r = model.getCurrent();
        r.setEstado("En proceso");
        Service.instance().updateReceta(r);
        model.setList(Service.instance().findAllRecetas());
        model.setCurrent(r);
    }

    public void listo() throws Exception {
        Receta r = model.getCurrent();
        r.setEstado("Lista");
        Service.instance().updateReceta(r);
        model.setList(Service.instance().findAllRecetas());
        model.setCurrent(r);
    }

    public void entregar() throws Exception {
        Receta r = model.getCurrent();
        r.setEstado("Entregada");
        Service.instance().updateReceta(r);
        model.setList(Service.instance().findAllRecetas());
        model.setCurrent(r);
    }

    public void revertir() throws Exception {
        Receta r = model.getCurrent();
        String estado = r.getEstado();

        if ("En proceso".equalsIgnoreCase(estado)) {
            r.setEstado("Confeccionado");
        } else if ("Lista".equalsIgnoreCase(estado)) {
            r.setEstado("En proceso");
        } else if ("Entregada".equalsIgnoreCase(estado)) {
            r.setEstado("Lista");
        } else {
            throw new Exception("No hay estado anterior para revertir");
        }
        Service.instance().updateReceta(r);
        model.setList(Service.instance().findAllRecetas());
        model.setCurrent(r);
    }
}