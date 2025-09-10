package hospital.presentation.despacho;

import hospital.logic.Receta;
import hospital.logic.Service;

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
}